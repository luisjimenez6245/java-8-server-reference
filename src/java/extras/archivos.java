/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extras;

import controladores.controladorBD;
import controladores.seguridad.logger;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.http.Part;

/**
 *
 * @author luis
 */
public class archivos {

    private final controladorBD BASE = new controladorBD();
    public String contentType = "";

    public archivos() {
    }

    public String subir(String nombre, Part archivo, String direccion) {
        String tipoArchivo = archivo.getContentType();
        tipoArchivo = "." + tipoArchivo.substring(tipoArchivo.lastIndexOf("/") + 1);
        nombre = ((nombre.equals("") ? new Date() : nombre) + tipoArchivo);
        return (guardaArchivoBase(nombre, archivo, direccion + nombre) && guardaArchivoDirectorio(archivo, direccion, nombre)) ? "exito" : "error ve el log";
    }

    private boolean guardaArchivoDirectorio(Part archivo, String direccion, String nombre) {
        try {
            InputStream initialStream = archivo.getInputStream();
            byte[] buffer = new byte[initialStream.available()];
            initialStream.read(buffer);
            if (!new File(direccion).exists()) {
                System.out.println("creating directory: " + direccion);
                try {
                    new File(direccion).mkdir();
                } catch (SecurityException se) {
                    new logger().error(se);
                }
            }
            OutputStream outStream = new FileOutputStream(new File(direccion + nombre));
            outStream.write(buffer);
        } catch (IOException ex) {
            new logger().error(ex);
            return false;
        }
        return true;
    }

    private boolean guardaArchivoBase(String nombre, Part archivo, String direccion) {
        try {
            BASE.conectar();
            String tipoArchivo = archivo.getContentType();
            tipoArchivo = "." + tipoArchivo.substring(tipoArchivo.lastIndexOf("/") + 1);
            PreparedStatement ps = BASE.getCall("insert into tbl_archivos (nombre, archivo, tipo, extension, direccion) values (?, ?, ?, ?, ?);");
            ps.setString(1, nombre.equals("") ? archivo.getName() : nombre);
            ps.setBlob(2, archivo.getInputStream());
            ps.setString(3, archivo.getContentType());
            ps.setString(4, tipoArchivo);
            ps.setString(5, direccion);
            ps.execute();
            BASE.cierraConexion();
        } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            new logger().error(ex);
            return false;
        }
        return true;
    }

    public String direccionArchivo(String id) {
        String path = "";
        try {
            BASE.conectar();
            String[] req = new String[1];
            req[0] = id.equals("") ? "(select last_insert_id())" : id;
            ResultSet rs = BASE.preparedS("select direccion from tbl_archivos where id = ?;", req);
            if (rs.next()) {
                path = rs.getString("direccion");
            }
            BASE.cierraConexion();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            new logger().error(ex);
        }
        return path;
    }

    public byte[] verDeBase(String id) {
        try {
            BASE.conectar();
            String[] req = new String[1];
            req[0] = id;
            Blob blob = null;
            ResultSet rs = BASE.preparedS("select archivo, tipo from tbl_archivos where id = ?;", req);
            if (rs.next()) {
                blob = rs.getBlob("archivo");
                contentType = rs.getString("tipo");
            }
            BASE.cierraConexion();
            return blob == null ? null : blob.getBytes(1, (int) blob.length());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            new logger().error(ex);
            return null;
        }
    }

    public byte[] verArchivo(String id, String direccion) {
        try {
            BASE.conectar();
            InputStream archivo = null;
            String[] req = new String[1];
            req[0] = id;
            ResultSet rs = BASE.preparedS("select * from tbl_archivos where id = ?;", req);
            if (rs.next()) {
                archivo = new FileInputStream(new File(direccion + rs.getString("nombre") + rs.getString("extension")));
            }
            BASE.cierraConexion();
            return archivo == null ? null : new byte[archivo.available()];
        } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            new logger().error(ex);
            return null;
        }
    }

    public byte[] getBytesFromFile(File arch) {
        if (arch == null) {
            return null;
        }
        try {
            FileInputStream stream = new FileInputStream(arch);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = stream.read(b)) != -1) {
                out.write(b, 0, n);
            }
            stream.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {

        }
        return null;
    }
}
