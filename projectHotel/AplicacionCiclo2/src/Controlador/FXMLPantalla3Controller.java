/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Habitaciones;
import Modelo.Persona;
import Modelo.Reservaciones;
import Modelo.Temporadas;
import Modelo.TemporadasConverte;
import Modelo.TipoHabitacion;
import Modelo.TipoHabitacionConverte;
import static java.lang.Float.valueOf;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Jaime
 */
public class FXMLPantalla3Controller implements Initializable {

    @FXML
    private Button btn_consultarCliente;
    @FXML
    private TextField txt_cedula;
    @FXML
    private TextField txt_nombre;
    @FXML
    private TextField txt_numero;

    @FXML
    private TextField txt_precioBase;

    @FXML
    private TextField txt_fechaEntrada;
    @FXML
    private TextField txt_fechaSalida;
    @FXML
    private TextField txt_cantidadDias;
    @FXML
    private TextField txt_totalPagar;
    @FXML
    private ComboBox<Temporadas> combox_temporada;
    @FXML
    private Button btn_calcular;
    @FXML
    private Button btn_pagar;
    TipoHabitacion auxTipoHabitacion = new TipoHabitacion();
    ObservableList<TipoHabitacion> listaTipoHabitacion = auxTipoHabitacion.getTipoHabitacionBD();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Temporadas t = new Temporadas();
        ObservableList<Temporadas> listaTemporadas = t.getTipoTemporadaBD();
        combox_temporada.getItems().addAll(listaTemporadas);
        combox_temporada.setConverter(new TemporadasConverte());
    }


    @FXML
    private void calcularPago(ActionEvent event) {
        float subtotalPagarPorDias;
        float subtotalTemporada;
        float subtotalIVA;
        float totalPagar;
        if (this.combox_temporada.getValue() != null && this.txt_cantidadDias.getText() != null && this.txt_precioBase.getText() != null) {
            float dias = Integer.parseInt(this.txt_cantidadDias.getText());
            float precioBase = Integer.parseInt(this.txt_precioBase.getText());
            float temporada = this.combox_temporada.getValue().getTasa();
            subtotalPagarPorDias = dias * precioBase;
            subtotalTemporada = subtotalPagarPorDias * temporada;
            subtotalIVA = (float) ((subtotalPagarPorDias + subtotalTemporada) * 0.14);
            totalPagar = subtotalPagarPorDias + subtotalTemporada + subtotalIVA;
            this.txt_totalPagar.setText(String.valueOf(totalPagar));
        } else {
            Modelo.GeneralBD.fun_mensajeInformacion("LLene todos los campos");
        }

    }

    @FXML
    private void pagar(ActionEvent event) {
        String Numero = this.txt_numero.getText();
        String fechaInicio = this.txt_fechaEntrada.getText();
        String fechaSalida = this.txt_fechaSalida.getText();
        String Cedula = this.txt_cedula.getText();
        String tipoTemporada = this.combox_temporada.getValue().getTipoTemporada();
        String precioTotal = this.txt_totalPagar.getText();
        Reservaciones r = new Reservaciones(Numero, fechaInicio, fechaSalida, Cedula, tipoTemporada, precioTotal);
        r.fun_insertarPersona();
    }

    public Habitaciones cargarHabitacion(String Numero) {
        Habitaciones objHabitacion = new Habitaciones();
        ObservableList<Habitaciones> listaHabitaciones = objHabitacion.getHabitacionesBD();
        for (Habitaciones auxHabitaciones : listaHabitaciones) {
            if (auxHabitaciones.getNumero().equals(Numero)) {
                Modelo.GeneralBD.fun_mensajeInformacion("Se encontro  la habitacion ");
                return auxHabitaciones;
            }
        }
        return null;
    }

    public Persona cargarClientes(String IDPERFIL) {
        Persona objPersona = new Persona();
        ObservableList<Persona> listaPersona = objPersona.getPersonasBD();
        for (Persona auxPersona : listaPersona) {
            if (auxPersona.getCedula().equals(IDPERFIL)) {
                Modelo.GeneralBD.fun_mensajeInformacion("Se encontro el cliente ");
                return auxPersona;
            }
        }
        return null;
    }

    @FXML
    private void acc_consultar(ActionEvent event) {
        Persona objPersona = cargarClientes(this.txt_cedula.getText());
        if (objPersona != null) {
            this.txt_nombre.setText(objPersona.getNombres());

        } else {
            Modelo.GeneralBD.fun_mensajeInformacion("Cliente no registrado en la Base de datos");
        }
        Habitaciones objHabitaciones = cargarHabitacion(this.txt_numero.getText());
        if (objHabitaciones != null) {
            this.txt_precioBase.setText(String.valueOf(objHabitaciones.getPreciobase()));

        } else {
            Modelo.GeneralBD.fun_mensajeInformacion("Habitacion no registrada en la Base de datos");
        }
    }
}
