/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Controlador;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Jaime
 */
public class Main extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }

    @Override
    public void start(Stage primerstage) throws Exception {
        try {
            
       Modelo.GeneralBD objCon=new Modelo.GeneralBD();
       if(objCon.conectarBD()){
           System.out.println("Se conectó correctamente con la BD");
       }else{
           System.out.println("Error al conectar con la BD");
           return;
       }
        //armar el boton de pregunta
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Atención");
        alert.setContentText("Desea salir de la aplicación?");
        Button closeButton=new Button("Cerrar la aplicación");
        Button addStage=new Button("Abrir la ventana");
        //fin boton

        VBox mainPanel = (VBox) FXMLLoader.load(getClass().getResource("/Vista/FXMLPrincipal.fxml"));
        Scene scene = new Scene(mainPanel);
        primerstage.setTitle("Mi Aplicación");
        primerstage.setScene(scene);
        primerstage.setMaximized(true);
        //modificado
        primerstage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume();           //Consumar el evento
                alert.showAndWait();
                if(alert.getResult().equals(ButtonType.OK)){
                   primerstage.close();
                   Platform.exit();
                }else{
                    System.out.println("cancel");
                }
               
            }
        });
        //fin modificado
        primerstage.show();

        Rectangle2D prinerscreen = Screen.getPrimary().getVisualBounds();
        primerstage.setX((prinerscreen.getWidth() - primerstage.getWidth()) / 2);
        primerstage.setY((prinerscreen.getHeight() - primerstage.getHeight()) / 2);
      } catch (Exception e) {
        }
    }//fin funcion

}//fin clase
