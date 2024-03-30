/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Jaime
 */
public class FXMLPrincipalController implements Initializable {

    @FXML
    private Button btn_salir;
    @FXML
    private Button btn_boton1;
    @FXML
    private Button btn_boron2;
    @FXML
    private Button btn_boron3;
    @FXML
    private VBox dataPane;
    @FXML
    private Button btn_boron4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void acc_MenuPantalla1(ActionEvent event) throws IOException {
        String pantalla = "/Vista/FXMLPantalla1.fxml";
        setDataPane(fadeAnimate(pantalla));
    }

    @FXML
    private void acc_MenuPatalla2(ActionEvent event) throws IOException {
        String pantalla = "/Vista/FXMLPantalla2.fxml";
        setDataPane(fadeAnimate(pantalla));
    }

    @FXML
    private void acc_MenuPantalla3(ActionEvent event) throws IOException {
        String pantalla = "/Vista/FXMLPantalla3.fxml";
        setDataPane(fadeAnimate(pantalla));

    }

    @FXML
    private void acc_salir(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void acc_irPantalla1(ActionEvent event) throws IOException {
        String pantalla = "/Vista/FXMLPantalla1.fxml";
        setDataPane(fadeAnimate(pantalla));
    }

    @FXML
    private void acc_irPantalla2(ActionEvent event) throws IOException {
        String pantalla = "/Vista/FXMLPantalla2.fxml";
        setDataPane(fadeAnimate(pantalla));
    }

    @FXML
    private void acc_irPantalla3(ActionEvent event) throws IOException {
        String pantalla = "/Vista/FXMLPantalla3.fxml";
        setDataPane(fadeAnimate(pantalla));
    }

    public VBox fadeAnimate(String url) throws IOException {
        VBox v = FXMLLoader.load(getClass().getResource(url));
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(v);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
        return v;
    }

    public void setDataPane(Node node) {
        dataPane.getChildren().setAll(node);
        dataPane.setPadding(new Insets(50, 100, 100, 200));
    }

    @FXML
    private void acc_MenuSalir(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void acc_irPantalla4(ActionEvent event) throws IOException {
        String pantalla = "/Vista/FXMLPantalla4.fxml";
        setDataPane(fadeAnimate(pantalla));
    }
}//fin clase
