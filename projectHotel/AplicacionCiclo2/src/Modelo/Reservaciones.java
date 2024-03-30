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

public class Reservaciones {

    private String numeroHabitacion;
    private String fechaInicio;
    private String fechaSalida;
    private String Cedula;
    private String tipoTemporada;
    private String precioTotal;

    public String getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(String numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public String getTipoTemporada() {
        return tipoTemporada;
    }

    public void setTipoTemporada(String tipoTemporada) {
        this.tipoTemporada = tipoTemporada;
    }

    public String getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(String precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Reservaciones() {
    }

    public Reservaciones(String numeroHabitacion, String fechaInicio, String fechaSalida, String Cedula, String tipoTemporada, String precioTotal) {
        this.numeroHabitacion = numeroHabitacion;
        this.fechaInicio = fechaInicio;
        this.fechaSalida = fechaSalida;
        this.Cedula = Cedula;
        this.tipoTemporada = tipoTemporada;
        this.precioTotal = precioTotal;
    }

    public boolean fun_insertarPersona() {
        try {
            String cadenaSQL = " ";
            cadenaSQL = cadenaSQL + " insert into Reservaciones (NumeroHabitacion, FechaInicio, FechaSalida, CedulaRUC, TipoTemporada, PrecioTotal) ";
            cadenaSQL = cadenaSQL + " values ( ";
            cadenaSQL = cadenaSQL + " " + this.getNumeroHabitacion() + ",";
            cadenaSQL = cadenaSQL + " '" + this.getFechaInicio() + "',";
            cadenaSQL = cadenaSQL + " '" + this.getFechaSalida() + "',";
            cadenaSQL = cadenaSQL + " '" + this.getCedula() + "',";
            cadenaSQL = cadenaSQL + " '" + this.getTipoTemporada() + "',";
            cadenaSQL = cadenaSQL + " " + this.getPrecioTotal() + ")";
            //1: conectar con la BD
            Modelo.GeneralBD objCon = new Modelo.GeneralBD();
            objCon.conectarBD();
            //2 ejucutar las sentencias sql
            int filas = objCon.ejecutarSQL(cadenaSQL, true);
            if (filas > 0) {
                Modelo.GeneralBD.fun_mensajeInformacion("Se hizo la reserva");
                return true;
            } else {
                Modelo.GeneralBD.fun_mensajeInformacion("No se hizo la reserva");
                return false;
            }

        } catch (Exception e) {
            return false;
        }

    }

    public ObservableList<Reservaciones> getReservacionesPorFechaBD(String auxFechaInicio, String auxFechaSalida) {
        ObservableList<Reservaciones> auxObsList = FXCollections.observableArrayList();
        try {
            String cadenaSQL = " ";
            cadenaSQL = cadenaSQL + " SELECT        dbo.Reservaciones.NumeroHabitacion, dbo.Reservaciones.FechaInicio, dbo.Reservaciones.FechaSalida, dbo.Clientes.NombreCompleto, dbo.Reservaciones.TipoTemporada, dbo.Reservaciones.PrecioTotal ";
            cadenaSQL = cadenaSQL + " FROM            dbo.Clientes INNER JOIN dbo.Reservaciones ON dbo.Clientes.CedulaRUC = dbo.Reservaciones.CedulaRUC ";
            cadenaSQL = cadenaSQL + " WHERE FechaInicio >= ";
            cadenaSQL = cadenaSQL + " '" + auxFechaInicio + "' ";;
            cadenaSQL = cadenaSQL + " AND FechaSalida < ";
            cadenaSQL = cadenaSQL + " '" + auxFechaSalida + "';";;

            //1: conectar con la BD
            Modelo.GeneralBD objCon = new Modelo.GeneralBD();
            objCon.conectarBD();
            //2: realizar la consulta
            objCon.ejecutarConsultasSQL(cadenaSQL);
            //3: recuperar la información
            ResultSet rs = objCon.getResulSet();
            while (rs.next()) {

                String _NumeroHabitacion = rs.getString("NumeroHabitacion");
                String _FechaInicio = rs.getString("FechaInicio");
                String _FechaSalida = rs.getString("FechaSalida");
                String _CedulaRUC = rs.getString("NombreCompleto");
                String _TipoTemporada = rs.getString("TipoTemporada");
                String _PrecioTotal = rs.getString("PrecioTotal");
                Reservaciones objReservaciones = new Reservaciones(_NumeroHabitacion, _FechaInicio, _FechaSalida, _CedulaRUC, _TipoTemporada, _PrecioTotal);
                auxObsList.add(objReservaciones);
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
