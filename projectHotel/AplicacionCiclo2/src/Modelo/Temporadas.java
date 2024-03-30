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

public class Temporadas {

    private String TipoTemporada;
    private float Tasa;

    public String getTipoTemporada() {
        return TipoTemporada;
    }

    public void setTipoTemporada(String TipoTemporada) {
        this.TipoTemporada = TipoTemporada;
    }

    public float getTasa() {
        return Tasa;
    }

    public void setTasa(int Tasa) {
        this.Tasa = Tasa;
    }

    public Temporadas() {
    }

    public Temporadas(String TipoTemporada, float Tasa) {
        this.TipoTemporada = TipoTemporada;
        this.Tasa = Tasa;
    }

    public ObservableList<Temporadas> getTipoTemporadaBD() {
        ObservableList<Temporadas> auxObsList = FXCollections.observableArrayList();
        try {
            String cadenaSQL = " ";
            cadenaSQL = cadenaSQL + " select TipoTemporada, Tasa ";
            cadenaSQL = cadenaSQL + " from Temporadas ";

            //1: conectar con la BD
            Modelo.GeneralBD objCon = new Modelo.GeneralBD();
            objCon.conectarBD();
            //2: realizar la consulta
            objCon.ejecutarConsultasSQL(cadenaSQL);
            //3: recuperar la información
            ResultSet rs = objCon.getResulSet();
            while (rs.next()) {
                //String _id = rs.getInt("id");
                String _TipoTemporada = rs.getString("TipoTemporada");
                float _Tasa = rs.getFloat("Tasa");

                Temporadas objTemporadas = new Temporadas(_TipoTemporada, _Tasa);;
                auxObsList.add(objTemporadas);
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
