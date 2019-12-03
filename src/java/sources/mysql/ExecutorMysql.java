/*
* MySQL executor
 */
package sources.mysql;

import controllers.security.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Luis Diego Jiménez Delgado
 */
public class ExecutorMysql {

    private final String DRIVERCLASSNAME = "com.mysql.jdbc.Driver";
    private final String BASEURL = "jdbc:mysql://$url$:$port$/dbName?allowPublicKeyRetrieval=true&useSSL=false&useServerPrepStmts=true";
    private Connection connection = null;
    private final boolean debug = true;

    private final String USER;
    private final String PASSWORD;
    private final String DBNAME;
    private final String URL;
    private final String PORT;

    private final Logger LOGGER = new Logger();

    public ExecutorMysql(String USER, String PASSWORD, String DBNAME, String URL, String PORT) {
        this.USER = USER;
        this.PASSWORD = PASSWORD;
        this.DBNAME = DBNAME;
        this.URL = URL;
        this.PORT = PORT;
        if (debug) {
            connection = conectar(USER, PASSWORD, DBNAME, URL, PORT);
            kill();
        } else {
        }
        connection = conectar(USER, PASSWORD, DBNAME, URL, PORT);

    }

    private void kill() {
        try {
            connection.abort((Runnable command) -> {
                try (Statement st = (Statement) connection.createStatement()) {
                    ResultSet res = st.executeQuery("select * from information_schema.processlist where DB =  '" + DBNAME + "' order by id desc;");
                    ArrayList<Integer> id = new ArrayList<>();
                    while (res.next()) {
                        id.add(res.getInt("id"));
                    }
                    for (int i = id.size() - 1; i > 0; --i) {
                        st.executeUpdate("KILL " + id.get(i) + ";");
                    }
                } catch (SQLException ex) {
                    LOGGER.errorBd(ex);
                }
            });
            connection.close();
        } catch (SQLException ex) {
            LOGGER.errorBd(ex);
        }
    }

    protected void closeConnection() throws SQLException {
        if (!connection.isClosed()) {
            connection.close();
        }
    }

    private Connection conectar(String user, String password, String dbName, String url, String port) {
        try {
            String urlBD = BASEURL;
            urlBD = urlBD.replace("dbName", dbName).replace("$url$", url).replace("$port$", port);
            Class.forName(this.DRIVERCLASSNAME).newInstance();
            return DriverManager.getConnection(urlBD, user, password);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            LOGGER.errorBd(ex);
        }
        return null;
    }

    private boolean verifyConnection() {
        if (this.connection == null) {
            this.connection = conectar(USER, PASSWORD, DBNAME, URL, PORT);
        }
        return connection != null;
    }

    private ResultSet executeQuery(String query, Object[] parameters) throws SQLException {
        if (verifyConnection()) {
            PreparedStatement state = this.connection.prepareStatement(query);
            if (parameters != null) {
                for (int i = 0; i < parameters.length; ++i) {
                    state.setObject(i + 1, parameters[i]);
                }
            }
            return state.executeQuery();
        }
        return null;
    }

    private int executeUpdate(String query, Object[] parameters) throws SQLException {
        if (verifyConnection()) {
            PreparedStatement state;
            if (query.toLowerCase().contains("insert")) {
                state = this.connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            } else {
                state = this.connection.prepareStatement(query);
            }
            if (parameters != null) {
                for (int i = 0; i < parameters.length; ++i) {
                    state.setObject(i + 1, parameters[i]);
                }
            }
            state.execute();
            if (query.toLowerCase().contains("insert")) {
                ResultSet res = state.getGeneratedKeys();
                return res.next() ? res.getInt(1) : 0;
            }

        }
        return 0;
    }

    protected ResultSet save(String tableName, Map<String, Object> parameters) {
        tableName = tableName.toLowerCase();
        String query = "INSERT INTO " + tableName + " ";
        if (parameters != null) {
            String keys = "(";
            String valores = "(";
            for (Map.Entry<String, Object> param : parameters.entrySet()) {
                keys += "" + param.getKey() + ", ";
                valores += "?,";
            }
            valores = valores.substring(0, valores.length() - 1) + ")";
            keys = keys.substring(0, keys.length() - 2) + ")";
            query += keys + " VALUES " + valores + ";";
        }
        int id;
        try {
            id = this.executeUpdate(query, mapToArray(parameters));
            HashMap<String, Object> param = new HashMap<>();
            param.put(tableName + "_id", id);
            return this.get("SELECT * FROM " + tableName + " ", param);
        } catch (SQLException ex) {
            LOGGER.errorBd(ex);
        }
        return null;
    }

    protected ResultSet get(String query, Map<String, Object> parameters) {
        return getList(query, parameters);
    }

    protected ResultSet getList(String query, Map<String, Object> parameters) {
        if (parameters != null && parameters.size() > 0) {
            String valores = " WHERE ";
            valores = parameters.entrySet().stream().map((param) -> param.getKey() + " = ? AND ").reduce(valores, String::concat);
            valores = valores.substring(0, valores.length() - 5);
            query += valores + ";";
        }
        try {
            ResultSet r = executeQuery(query, mapToArray(parameters));
            return r;
        } catch (SQLException ex) {
            LOGGER.errorBd(ex);
        }
        return null;
    }

    protected ResultSet set(String query, Map<String, Object> parameters, Map<String, Object> conditions) {
        if (parameters != null && parameters.size() > 0) {
            String valores = " SET ";
            valores = parameters.entrySet().stream().map((param) -> param.getKey() + " = ?, ").reduce(valores, String::concat);
            valores = valores.substring(0, valores.length() - 3);
            query += valores + " ";
        }
        if (conditions != null && conditions.size() > 0) {
            String valores = " WHERE ";
            valores = conditions.entrySet().stream().map((param) -> param.getKey() + " = ? AND ").reduce(valores, String::concat);
            valores = valores.substring(0, valores.length() - 5);
            query += valores + ";";
        }
        try {
            ResultSet r = executeQuery(query, mapToArray(parameters));
            return r;
        } catch (SQLException ex) {
            LOGGER.errorBd(ex);
        }
        return null;
    }

    protected Long delete(String tableName, Map<String, Object> parameters) {
        String query = "DELETE FROM " + tableName + " ";
        if (parameters != null && parameters.size() > 0) {
            String valores = "WHERE ";
            valores = parameters.entrySet().stream().map((param) -> param.getKey() + " = ? AND ").reduce(valores, String::concat);
            valores = valores.substring(0, valores.length() - 5);
            query += valores + ";";
        }
        try {
            this.executeUpdate(query, mapToArray(parameters));
            return 0L;
        } catch (SQLException ex) {
            LOGGER.errorBd(ex);
        }
        return null;
    }

    public Object[] mapToArray(Map<String, Object> parameters) {
        ArrayList<Object> helper = new ArrayList<>();
        parameters.entrySet().forEach((r) -> {
            helper.add(r.getValue());
        });
        return helper.toArray(new Object[helper.size()]);
    }

}
