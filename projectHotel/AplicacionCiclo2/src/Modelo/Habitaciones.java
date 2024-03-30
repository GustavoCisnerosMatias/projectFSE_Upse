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

public class Habitaciones {

    private String numero;
    private String descripcion;
    private int preciobase;
    private int vistaMar;
    private int cantidadCamas;
    private String tipoHabitacionID;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPreciobase() {
        return preciobase;
    }

    public void setPreciobase(int preciobase) {
        this.preciobase = preciobase;
    }

    public int getVistaMar() {
        return vistaMar;
    }

    public void setVistaMar(int vistaMar) {
        this.vistaMar = vistaMar;
    }
    
    public int getCantidadCamas() {
        return cantidadCamas;
    }

    public void setCantidadCamas(int cantidadCamas) {
        this.cantidadCamas = cantidadCamas;
    }

    public String getTipoHabitacionID() {
        return tipoHabitacionID;
    }

    public void setTipoHabitacionID(String tipoHabitacionID) {
        this.tipoHabitacionID = tipoHabitacionID;
    }

    public Habitaciones() {
    }

    public Habitaciones(String numero, String descripcion, int preciobase, int vistaMar, int cantidadCamas, String tipoHabitacionID) {
        this.numero = numero;
        this.descripcion = descripcion;
        this.preciobase = preciobase;
        this.vistaMar = vistaMar;
        this.cantidadCamas = cantidadCamas;
        this.tipoHabitacionID = tipoHabitacionID;
    }

    public ObservableList<Habitaciones> getHabitacionesBD() {
        ObservableList<Habitaciones> auxObsList = FXCollections.observableArrayList();
        try {
            String cadenaSQL = " ";
            cadenaSQL = cadenaSQL + " select numero, descripcion , PrecioBase , VistaMar , CantidadCamas , TipoHabitacionID ";
            cadenaSQL = cadenaSQL + " from Habitaciones ";

            //1: conectar con la BD
            Modelo.GeneralBD objCon = new Modelo.GeneralBD();
            objCon.conectarBD();
            //2: realizar la consulta
            objCon.ejecutarConsultasSQL(cadenaSQL);
            //3: recuperar la información
            ResultSet rs = objCon.getResulSet();
            while (rs.next()) {

                //String _id = rs.getInt("id");
                String _numero = rs.getString("numero");
                String _descripcion = rs.getString("descripcion");
                int _preciobase = rs.getInt("PrecioBase");
                int _vistaMar = rs.getInt("VistaMar");
                int _cantidadCamas = rs.getInt("CantidadCamas");
                String _tipoHabitacionIDr = rs.getString("TipoHabitacionID");

                Habitaciones objHabitaciones = new Habitaciones(_numero, _descripcion, _preciobase, _vistaMar, _cantidadCamas, _tipoHabitacionIDr);;
                auxObsList.add(objHabitaciones);
            }
            //4 cerrar las conexiones
            rs.close();
            objCon.cerrarConexionBD();

        } catch (Exception e) {
            Logger.getLogger(GeneralBD.class.getName()).log(Level.WARNING, null, e);
        }

        return auxObsList;
    }

    public boolean fun_insertarHabitacion() {
        try {
            String cadenaSQL = " ";
            cadenaSQL = cadenaSQL + " insert into Habitaciones (Numero, Descripcion, PrecioBase, VistaMar, CantidadCamas, TipoHabitacionID) ";
            cadenaSQL = cadenaSQL + " values ( ";
            cadenaSQL = cadenaSQL + " '" + this.getNumero() + "',";
            cadenaSQL = cadenaSQL + " '" + this.getDescripcion() + "',";
            cadenaSQL = cadenaSQL + " '" + this.getPreciobase() + "',";
            cadenaSQL = cadenaSQL + " '" + this.getCantidadCamas() + "',";
            cadenaSQL = cadenaSQL + " '" + this.getVistaMar() + "',";
            cadenaSQL = cadenaSQL + " '" + this.getTipoHabitacionID() + "')";
            //1: conectar con la BD
            Modelo.GeneralBD objCon = new Modelo.GeneralBD();
            objCon.conectarBD();
            //2 ejucutar las sentencias sql
            int filas = objCon.ejecutarSQL(cadenaSQL, true);
            if (filas > 0) {
                Modelo.GeneralBD.fun_mensajeInformacion("Se inserto correctamente la habitacion");
                return true;
            } else {
                Modelo.GeneralBD.fun_mensajeInformacion("No se inserto la habitacion");
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }

    public boolean fun_eliminarHabitacion() {
        try {
            String cadenaSQL = " ";
            cadenaSQL = cadenaSQL + " DELETE FROM Habitaciones ";
            cadenaSQL = cadenaSQL + " WHERE Numero = ";
            cadenaSQL = cadenaSQL + " '" + this.getNumero() + "'";
            //1: conectar con la BD
            Modelo.GeneralBD objCon = new Modelo.GeneralBD();
            objCon.conectarBD();

            //2 ejucutar las sentencias sql
            int filas = objCon.ejecutarSQL(cadenaSQL, true);
            if (filas > 0) {
                Modelo.GeneralBD.fun_mensajeInformacion("Se elimino correctamente la habitacion ");
                return true;
            } else {
                Modelo.GeneralBD.fun_mensajeInformacion("No se elimino la habitacion revise los campos, verifique que exista");
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean fun_modificarHabitaciones() {
        try {
            String cadenaSQL = " ";
            cadenaSQL = cadenaSQL + " UPDATE Habitaciones ";
            cadenaSQL = cadenaSQL + " SET Descripcion = ";
            cadenaSQL = cadenaSQL + " '" + this.getDescripcion() + "',";
            cadenaSQL = cadenaSQL + " PrecioBase = ";
            cadenaSQL = cadenaSQL + " '" + this.getPreciobase() + "',";
            cadenaSQL = cadenaSQL + " VistaMar = ";
            cadenaSQL = cadenaSQL + " '" + this.getVistaMar() + "' , ";
            cadenaSQL = cadenaSQL + " CantidadCamas = ";
            cadenaSQL = cadenaSQL + " '" + this.getCantidadCamas() + "' , ";
            cadenaSQL = cadenaSQL + " TipoHabitacionID = ";
            cadenaSQL = cadenaSQL + " '" + this.getTipoHabitacionID() + "'";
            cadenaSQL = cadenaSQL + " WHERE Numero = ";
            cadenaSQL = cadenaSQL + " '" + this.getNumero() + "'";
            //1: conectar con la BD
            Modelo.GeneralBD objCon = new Modelo.GeneralBD();
            objCon.conectarBD();
            //2 ejucutar las sentencias sql
            int filas = objCon.ejecutarSQL(cadenaSQL, true);
            if (filas > 0) {
                Modelo.GeneralBD.fun_mensajeInformacion("Se modifico correctamente la habitacion");
                return true;
            } else {
                Modelo.GeneralBD.fun_mensajeInformacion("No se modifico la habitacion ");
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }


}
