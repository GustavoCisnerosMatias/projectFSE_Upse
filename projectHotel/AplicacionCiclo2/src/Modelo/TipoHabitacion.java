/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class TipoHabitacion {
    private String TipoID;
    private String TipoNombre;

    public String getTipoID() {
        return TipoID;
    }

    public void setTipoID(String TipoID) {
        this.TipoID = TipoID;
    }

    public String getTipoNombre() {
        return TipoNombre;
    }

    public void setTipoNombre(String TipoNombre) {
        this.TipoNombre = TipoNombre;
    }

    public TipoHabitacion() {
    }

    public TipoHabitacion(String TipoID, String TipoNombre) {
        this.TipoID = TipoID;
        this.TipoNombre = TipoNombre;
    }

    public ObservableList<TipoHabitacion> getTipoHabitacionBD() {
        ObservableList<TipoHabitacion> auxObsList = FXCollections.observableArrayList();
        try {
            String cadenaSQL = " ";
            cadenaSQL = cadenaSQL + " select TipoID, TipoNombre ";
            cadenaSQL = cadenaSQL + " from TiposHabitacion ";


            //1: conectar con la BD
            Modelo.GeneralBD objCon = new Modelo.GeneralBD();
            objCon.conectarBD();
            //2: realizar la consulta
            objCon.ejecutarConsultasSQL(cadenaSQL);
            //3: recuperar la información
            ResultSet rs = objCon.getResulSet();
            while (rs.next()) {
                //String _id = rs.getInt("id");
                String _TipoID = rs.getString("TipoID");
                String _TipoNombre = rs.getString("TipoNombre");

                TipoHabitacion objTipoHabitacion = new TipoHabitacion(_TipoID, _TipoNombre);;
                auxObsList.add(objTipoHabitacion);
            }
            //4 cerrar las conexiones
            rs.close();
            objCon.cerrarConexionBD();

        } catch (Exception e) {
            Logger.getLogger(GeneralBD.class.getName()).log(Level.WARNING, null, e);
        }

        return auxObsList;
    }
}
