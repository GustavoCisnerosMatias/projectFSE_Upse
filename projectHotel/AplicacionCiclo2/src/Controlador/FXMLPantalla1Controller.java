/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Persona;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Jaime
 */
public class FXMLPantalla1Controller implements Initializable {

    @FXML
    private Button btn_consultar;
    @FXML
    private TableView<Persona> tv_personas;
    @FXML
    private TableColumn<?, ?> col_id;
    @FXML
    private TableColumn<?, ?> col_nombres;
    @FXML
    private TableColumn<?, ?> col_direccion;
    @FXML
    private TableColumn<?, ?> col_estado;

    @FXML
    private TextField txt_nombre;
    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_cedula;
    @FXML
    private TextField txt_telefono;
    private VBox dataPane;
    @FXML
    private Button btn_guardar;
    @FXML
    private Button btn_modificar;
    @FXML
    private Button btn_eliminar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.col_id.setCellValueFactory(new PropertyValueFactory("cedula"));
        this.col_nombres.setCellValueFactory(new PropertyValueFactory("nombres"));
        this.col_direccion.setCellValueFactory(new PropertyValueFactory("telefono"));
        this.col_estado.setCellValueFactory(new PropertyValueFactory("email"));
        this.cargarPersonas();
    }

    @FXML
    private void acc_consultar(ActionEvent event) throws IOException {
        Persona objPersona = cargarClientes(this.txt_cedula.getText());
        if (objPersona != null) {
            this.txt_nombre.setText(objPersona.getNombres());
            this.txt_telefono.setText(objPersona.getTelefono());
            this.txt_email.setText(objPersona.getEmail());
        } else {
            Modelo.GeneralBD.fun_mensajeInformacion("Cliente no registrado en la Base de datos");
        }

    }

    public void cargarPersonas() {
        try {
            Persona p = new Persona();
            this.tv_personas.setItems(p.getPersonasBD());
        } catch (Exception e) {
        }
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
    private void acc_guardar(ActionEvent event) {
        String Cedula = this.txt_cedula.getText();
        String Nombre = this.txt_nombre.getText();
        String Telefono = this.txt_telefono.getText();
        String Email = this.txt_email.getText();
        Persona p = new Persona(Cedula, Nombre, Telefono, Email);
        p.fun_insertarPersona();
        this.cargarPersonas();

    }

    @FXML
    private void acc_modificar(ActionEvent event) {
        String Cedula = this.txt_cedula.getText();
        String Nombre = this.txt_nombre.getText();
        String Telefono = this.txt_telefono.getText();
        String Email = this.txt_email.getText();
        Persona p = new Persona(Cedula, Nombre, Telefono, Email);
        p.fun_modificarPersona();
        this.cargarPersonas();
    }

    @FXML
    private void acc_eliminar(ActionEvent event) {
        String Cedula = this.txt_cedula.getText();
        String Nombre = this.txt_nombre.getText();
        String Telefono = this.txt_telefono.getText();
        String Email = this.txt_email.getText();
        Persona p = new Persona(Cedula, Nombre, Telefono, Email);
        p.fun_eliminarPersona();
        this.cargarPersonas();
    }


}
