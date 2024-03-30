/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jaime
 */
public class Persona {


    private String cedula;
    private String nombres;
    private String telefono;
    private String email;

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Persona(String cedula, String nombres, String telefono, String email) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.telefono = telefono;
        this.email = email;
    }

    public Persona() {
    }

    public ObservableList<Persona> getPersonasBD() {
        ObservableList<Persona> auxObsList = FXCollections.observableArrayList();
        try {
            String cadenaSQL = " ";
            cadenaSQL = cadenaSQL + " select CedulaRUC, NombreCompleto, Telefono, Email";
            cadenaSQL = cadenaSQL + " from Clientes ";

            //cadenaSQL = cadenaSQL + " where estado like 'A' ";
            //1: conectar con la BD
            Modelo.GeneralBD objCon = new Modelo.GeneralBD();
            objCon.conectarBD();
            //2: realizar la consulta
            objCon.ejecutarConsultasSQL(cadenaSQL);
            //3: recuperar la información
            ResultSet rs = objCon.getResulSet();
            while (rs.next()) {
                //String _id = rs.getInt("id");
                String _cedula = rs.getString("CedulaRUC");
                String _nombres = rs.getString("NombreCompleto");
                String _direccion = rs.getString("Telefono");
                String _estado = rs.getString("Email");
                Persona objPersona = new Persona(_cedula, _nombres, _direccion, _estado);;
                auxObsList.add(objPersona);
            }
            //4 cerrar las conexiones
            rs.close();
            objCon.cerrarConexionBD();

        } catch (Exception e) {
            Logger.getLogger(GeneralBD.class.getName()).log(Level.WARNING, null, e);
        }

        return auxObsList;
    }

    public boolean fun_insertarPersona() {
        try {
            String cadenaSQL = " ";
            cadenaSQL = cadenaSQL + " insert into Clientes (CedulaRUC, NombreCompleto , Telefono, Email) ";
            cadenaSQL = cadenaSQL + " values ( ";
            cadenaSQL = cadenaSQL + " '" + this.getCedula() + "',";
            cadenaSQL = cadenaSQL + " '" + this.getNombres() + "',";
            cadenaSQL = cadenaSQL + " '" + this.getTelefono() + "',";
            cadenaSQL = cadenaSQL + " '" + this.getEmail() + "')";
            //1: conectar con la BD
            Modelo.GeneralBD objCon = new Modelo.GeneralBD();
            objCon.conectarBD();
            //2 ejucutar las sentencias sql
            int filas = objCon.ejecutarSQL(cadenaSQL, true);
            if (filas > 0) {
                Modelo.GeneralBD.fun_mensajeInformacion("Se inserto correctamente el Cliente");
                return true;
            } else {
                Modelo.GeneralBD.fun_mensajeInformacion("No se inserto el cliente");
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }

    public boolean fun_eliminarPersona() {
        try {
            String cadenaSQL = " ";
            cadenaSQL = cadenaSQL + " DELETE FROM Clientes ";
            cadenaSQL = cadenaSQL + " WHERE CedulaRUC = ";
            cadenaSQL = cadenaSQL + " '" + this.getCedula() + "'";
            //1: conectar con la BD
            Modelo.GeneralBD objCon = new Modelo.GeneralBD();
            objCon.conectarBD();

            //2 ejucutar las sentencias sql
            int filas = objCon.ejecutarSQL(cadenaSQL, true);
            if (filas > 0) {
                Modelo.GeneralBD.fun_mensajeInformacion("Se elimino correctamente el Cliente");
                return true;
            } else {
                Modelo.GeneralBD.fun_mensajeInformacion("No se elimino el cliente revise los campos, verifique que exista");
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean fun_modificarPersona() {
        try {
            String cadenaSQL = " ";
            cadenaSQL = cadenaSQL + " UPDATE Clientes ";
            cadenaSQL = cadenaSQL + " SET NombreCompleto = ";
            cadenaSQL = cadenaSQL + " '" + this.getNombres() + "',";
            cadenaSQL = cadenaSQL + " Telefono = ";
            cadenaSQL = cadenaSQL + " '" + this.getTelefono() + "',";
            cadenaSQL = cadenaSQL + " Email = ";
            cadenaSQL = cadenaSQL + " '" + this.getEmail() + "'";
            cadenaSQL = cadenaSQL + " WHERE CedulaRUC = ";
            cadenaSQL = cadenaSQL + " '" + this.getCedula() + "'";
            //1: conectar con la BD
            Modelo.GeneralBD objCon = new Modelo.GeneralBD();
            objCon.conectarBD();
            //2 ejucutar las sentencias sql
            int filas = objCon.ejecutarSQL(cadenaSQL, true);
            if (filas > 0) {
                Modelo.GeneralBD.fun_mensajeInformacion("Se modifico correctamente el Cliente");
                return true;
            } else {
                Modelo.GeneralBD.fun_mensajeInformacion("No se modifico el cliente ");
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
