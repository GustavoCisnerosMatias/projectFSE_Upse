/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import javafx.scene.control.Alert;

/**
 *
 * @author Jaime
 */
public class GeneralBD {

    private Connection conexion;
    private Statement sentenciaSQL;
    private ResultSet resulSet;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public Statement getSentenciaSQL() {
        return sentenciaSQL;
    }

    public void setSentenciaSQL(Statement sentenciaSQL) {
        this.sentenciaSQL = sentenciaSQL;
    }

    public ResultSet getResulSet() {
        return resulSet;
    }

    public void setResulSet(ResultSet resulSet) {
        this.resulSet = resulSet;
    }

    public boolean conectarBD() {
        String classNombre = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String servidor = "DESKTOP-IJL02JU";
        //String usuario = "usr_ingles";
        String usuario = "usr2023";
        String clave = "usr2023";
        String basedatos = "tuDescanso";

        String cadenaConexion = "jdbc:sqlserver://" + servidor + ":1433; "
                + "database=" + basedatos + ";"
                + "user=" + usuario + ";"
                + "password=" + clave + ";"
                + " encrypt=false; loginTimeout=30;";
        try {
            Class.forName(classNombre);
            Connection conexion = DriverManager.getConnection(cadenaConexion);
            conexion.setAutoCommit(false);
            this.setConexion(conexion);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public void cerrarConexionBD() {
        try {
            this.conexion.close();
        } catch (Exception e) {
            Logger.getLogger(GeneralBD.class.getName()).log(Level.WARNING, null, e);
        }
    }

    public void commit() {
        try {
            this.conexion.commit();
        } catch (Exception e) {
            Logger.getLogger(GeneralBD.class.getName()).log(Level.WARNING, null, e);
        }
    }

    public void roollback() {
        try {
            this.conexion.rollback();
        } catch (Exception e) {
            Logger.getLogger(GeneralBD.class.getName()).log(Level.WARNING, null, e);
        }
    }

    public void ejecutarConsultasSQL(String cadenasSQL) {
        //función para ejecutar consultas de tipo select
        try {
            this.sentenciaSQL = this.conexion.createStatement();
            this.resulSet = this.sentenciaSQL.executeQuery(cadenasSQL);
        } catch (Exception e) {
            Logger.getLogger(GeneralBD.class.getName()).log(Level.WARNING, null, e);
        }

    }

    public int ejecutarSQL(String cadenaSQL, boolean commit) {
        //insert /update / delete
        int filas = 0;
        try {
            this.sentenciaSQL = this.conexion.createStatement();
            filas = this.sentenciaSQL.executeUpdate(cadenaSQL);
            if (commit) {
                commit();
            }
        } catch (Exception e) {
            Logger.getLogger(GeneralBD.class.getName()).log(Level.WARNING, null, e);
        }
        return filas;
    }

    public static void fun_mensajeInformacion(String miMensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Mensaje del Sistema");
        alert.setContentText(miMensaje);
        alert.showAndWait();
    }
}//fin clase
