/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extras;

import controladores.controladorBD;
import controladores.seguridad.logger;
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
import javax.servlet.http.Part;

/**
 *
 * @author luis
 */
public class archivos {

    private final controladorBD BASE = new controladorBD();

    public String subir(String nombre, Part archivo, String direccion) {
       return (guardaArchivoBase(nombre, archivo) && guardaArchivoDirectorio(nombre, archivo, direccion)) ? "exito" : "error ve el log";
    }

    private boolean guardaArchivoDirectorio(String nombre, Part archivo, String direccion) {
        try {
            String tipoArchivo = archivo.getContentType();
            tipoArchivo = "." + tipoArchivo.substring(tipoArchivo.lastIndexOf("/") + 1);
            InputStream initialStream = archivo.getInputStream();
            byte[] buffer = new byte[initialStream.available()];
            initialStream.read(buffer);
            File targetFile = new File(direccion + (nombre.equals("") ? archivo.getName() : nombre + tipoArchivo));
            OutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(buffer);
        } catch (IOException ex) {
            new logger().error(ex);
            return false;
        }
        return true;
    }

    private boolean guardaArchivoBase(String nombre, Part archivo) {
        try {
            BASE.conectar();
            String tipoArchivo = archivo.getContentType();
            tipoArchivo = "." + tipoArchivo.substring(tipoArchivo.lastIndexOf("/") + 1);
            //siempre se debe de guardar, modificar el archivo es con un PreparedStatement 
            PreparedStatement ps = BASE.getCall("insert into tbl_archivos (nombre, archivo, tipo) values (?, ?, ?);");
            ps.setString(1, nombre.equals("") ? archivo.getName() : nombre);
            ps.setBlob(2, archivo.getInputStream());
            ps.setString(3, tipoArchivo);
            ps.execute();
            BASE.cierraConexion();
        } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            new logger().error(ex);
            return false;
        }
        return true;
    }

    public byte[] verDeBase(String id) {
        byte[] byteArray = null;
        try {
            BASE.conectar();
            String[] req = new String[1];
            req[0] = id;
            ResultSet rs = BASE.preparedS("select archivo from tbl_archivos where id = ?;", req);
            if (rs.next()) {
                Blob blob = rs.getBlob("archivo");
                byteArray = blob.getBytes(1, (int) blob.length());
            }
            BASE.cierraConexion();
            return byteArray;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            new logger().error(ex);
            return null;
        }
    }

    public byte[] verArchivo(String id, String direccion) {
        try {
            BASE.conectar();
            String[] req = new String[1];
            req[0] = id;
            ResultSet rs = BASE.preparedS("select * from tbl_archivos where id = ?;", req);
            if (rs.next()) {
                id = rs.getString("nombre") + rs.getString("tipo");
            }
            BASE.cierraConexion();
            InputStream archivo = new FileInputStream(new File(direccion + id));
            return new byte[archivo.available()];
        } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            new logger().error(ex);
            return null;
        }

    }
}
