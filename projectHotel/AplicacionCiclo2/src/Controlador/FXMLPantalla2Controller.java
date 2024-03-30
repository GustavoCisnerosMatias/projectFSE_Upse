/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Habitaciones;
import Modelo.Persona;
import Modelo.TipoHabitacion;
import Modelo.TipoHabitacionConverte;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Jaime
 */
public class FXMLPantalla2Controller implements Initializable {

    @FXML
    private Button btn_consultar;
    @FXML
    private Button btn_eliminar;
    @FXML
    private Button btn_modificar;
    @FXML
    private Button btn_insertar;
    @FXML
    private TextField txt_numero;
    @FXML
    private TextField txt_descripcion;
    @FXML
    private TextField txt_precioBase;
    @FXML
    private TextField txt_cantidad;
    @FXML
    private ComboBox<TipoHabitacion> combox_tipoHabitacion;
    @FXML
    private CheckBox chk_vistaMar;

    TipoHabitacion auxTipoHabitacion = new TipoHabitacion();
    ObservableList<TipoHabitacion> listaTipoHabitacion = auxTipoHabitacion.getTipoHabitacionBD();
    @FXML
    private TableView<Habitaciones> tv_habitacion;
    @FXML
    private TableColumn<?, ?> col_numero;
    @FXML
    private TableColumn<?, ?> col_precioBase;
    @FXML
    private TableColumn<?, ?> col_cantidadCamas;

    /**
     * Initializes the controller class.
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.col_numero.setCellValueFactory(new PropertyValueFactory("numero"));
        this.col_precioBase.setCellValueFactory(new PropertyValueFactory("preciobase"));
        this.col_cantidadCamas.setCellValueFactory(new PropertyValueFactory("cantidadCamas"));
        combox_tipoHabitacion.getItems().addAll(listaTipoHabitacion);
        combox_tipoHabitacion.setConverter(new TipoHabitacionConverte());
        cargarHabitaciones();
    }

    @FXML
    private void acc_consultar(ActionEvent event) {
        Habitaciones objHabitaciones = cargarHabitacion(this.txt_numero.getText());
        if (objHabitaciones != null) {
            this.txt_descripcion.setText(objHabitaciones.getDescripcion());
            this.txt_precioBase.setText(String.valueOf(objHabitaciones.getPreciobase()));

            if (objHabitaciones.getVistaMar() == 1) {
                this.chk_vistaMar.setSelected(true);
            } else {
                this.chk_vistaMar.setSelected(false);
            }

            if (objHabitaciones.getCantidadCamas() == 0) {
                this.txt_cantidad.setText("1");
            } else {
                this.txt_cantidad.setText(String.valueOf(objHabitaciones.getCantidadCamas()));

            }

            if (!listaTipoHabitacion.isEmpty()) {
                switch (objHabitaciones.getTipoHabitacionID()) {
                    case "1":
                        combox_tipoHabitacion.setValue(listaTipoHabitacion.get(2));

                        break;
                    case "2":
                        combox_tipoHabitacion.setValue(listaTipoHabitacion.get(0));

                        break;
                    case "3":
                        combox_tipoHabitacion.setValue(listaTipoHabitacion.get(1));

                        break;
                }

            }
        } else {
            Modelo.GeneralBD.fun_mensajeInformacion("Habitacion no registrada en la Base de datos");
        }
    }

    @FXML
    private void acc_eliminar(ActionEvent event) {
        String Numero = this.txt_numero.getText();
        String Descripcion = this.txt_descripcion.getText();
        int PrecioBase = Integer.parseInt(this.txt_precioBase.getText());
        int CantidadCamas = Integer.parseInt(this.txt_cantidad.getText());
        String idTipoHabitacion = this.combox_tipoHabitacion.getValue().getTipoID();
        int vistaMar;
        if (this.chk_vistaMar.isSelected()) {
            vistaMar = 1;
        } else {
            vistaMar = 0;
        }
        Habitaciones h = new Habitaciones(Numero, Descripcion, PrecioBase, vistaMar, CantidadCamas, idTipoHabitacion);
        h.fun_eliminarHabitacion();
        cargarHabitaciones();
    }

    @FXML
    private void acc_modificar(ActionEvent event) {
        String Numero = this.txt_numero.getText();
        String Descripcion = this.txt_descripcion.getText();
        int PrecioBase = Integer.parseInt(this.txt_precioBase.getText());
        int CantidadCamas = Integer.parseInt(this.txt_cantidad.getText());
        String idTipoHabitacion = this.combox_tipoHabitacion.getValue().getTipoID();
        int vistaMar;
        if (this.chk_vistaMar.isSelected()) {
            vistaMar = 1;
        } else {
            vistaMar = 0;
        }
        Habitaciones h = new Habitaciones(Numero, Descripcion, PrecioBase, vistaMar, CantidadCamas, idTipoHabitacion);
        h.fun_modificarHabitaciones();
        cargarHabitaciones();
    }

    @FXML
    private void acc_insertar(ActionEvent event) {
        String Numero = this.txt_numero.getText();
        String Descripcion = this.txt_descripcion.getText();
        int PrecioBase = Integer.parseInt(this.txt_precioBase.getText());
        int CantidadCamas;
        String idTipoHabitacion = this.combox_tipoHabitacion.getValue().getTipoID();
        int vistaMar;
        if (this.chk_vistaMar.isSelected()) {
            vistaMar = 1;
        } else {
            vistaMar = 0;
        }

        if ("3".equals(idTipoHabitacion)) {
            CantidadCamas = 1;
        } else {
            CantidadCamas = Integer.parseInt(this.txt_cantidad.getText());
        }

        Habitaciones h = new Habitaciones(Numero, Descripcion, PrecioBase, vistaMar, CantidadCamas, idTipoHabitacion);
        h.fun_insertarHabitacion();
        cargarHabitaciones();
    }

    @FXML
    private void acc_habilitarCantidadCamas(ActionEvent event) {
        String idTipoHabitacion = this.combox_tipoHabitacion.getValue().getTipoID();

        if ("3".equals(idTipoHabitacion)) {
            this.txt_cantidad.setDisable(false);
        } else {
            this.txt_cantidad.setText("1");
            this.txt_cantidad.setDisable(true);

        }

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

    public void cargarHabitaciones() {
        try {
            Habitaciones objHabitaciones = new Habitaciones();
            this.tv_habitacion.setItems(objHabitaciones.getHabitacionesBD());
        } catch (Exception e) {
        }
    }
}
