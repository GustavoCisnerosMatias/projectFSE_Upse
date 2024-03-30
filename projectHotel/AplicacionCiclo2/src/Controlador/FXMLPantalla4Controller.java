/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Persona;
import Modelo.Reservaciones;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class FXMLPantalla4Controller implements Initializable {

    @FXML
    private Button btn_consultar;
    @FXML
    private TableView<Reservaciones> tv_reservas;
    @FXML
    private TableColumn<?, ?> col_numero;
    @FXML
    private TableColumn<?, ?> col_entrada;
    @FXML
    private TableColumn<?, ?> col_salida;
    @FXML
    private TableColumn<?, ?> col_cedula;
    @FXML
    private TableColumn<?, ?> col_temporada;
    @FXML
    private TableColumn<?, ?> col_pagar;
    @FXML
    private TextField txt_fecha;
    @FXML
    private TextField txt_ganacias;
    @FXML
    private TextField txt_fechafin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.col_numero.setCellValueFactory(new PropertyValueFactory("numeroHabitacion"));
        this.col_entrada.setCellValueFactory(new PropertyValueFactory("fechaInicio"));
        this.col_salida.setCellValueFactory(new PropertyValueFactory("fechaSalida"));
        this.col_cedula.setCellValueFactory(new PropertyValueFactory("Cedula"));
        this.col_temporada.setCellValueFactory(new PropertyValueFactory("tipoTemporada"));
        this.col_pagar.setCellValueFactory(new PropertyValueFactory("precioTotal"));

    }

    @FXML
    private void acc_consultar(ActionEvent event) {
        this.cargarReservaciones();

    }

    public void cargarReservaciones() {
        try {
            double totalPrecios = 0.0;
            Reservaciones r = new Reservaciones();
            this.tv_reservas.setItems(r.getReservacionesPorFechaBD(this.txt_fecha.getText(), this.txt_fechafin.getText()));

            for (Reservaciones reserva : r.getReservacionesPorFechaBD(this.txt_fecha.getText(), this.txt_fechafin.getText())) {
                // Obtener el precio total de la reserva como un Double
                double precioReserva = Double.parseDouble(reserva.getPrecioTotal());

                // Sumar el precio total al acumulador
                totalPrecios += precioReserva;
            }
            this.txt_ganacias.setText(String.valueOf(totalPrecios));
        } catch (Exception e) {
        }
    }

}
