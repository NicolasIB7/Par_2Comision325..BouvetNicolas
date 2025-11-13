/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cine.view;


import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import cine.model.CineData;
import cine.model.Sala;

/**
 * Vista simple que muestra las salas del cine en una tabla.
 */
public class SalasView {

    private VBox root;

    public SalasView(Stage stage) {
        root = new VBox(10);
        root.setStyle("-fx-padding: 20;");

        Label titulo = new Label("Salas disponibles");
        titulo.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");

        // Tabla de salas
        TableView<Sala> tabla = new TableView<>(CineData.getSalas());
        tabla.setPrefHeight(300);

        TableColumn<Sala, String> cNumero = new TableColumn<>("Sala");
        TableColumn<Sala, String> cPelicula = new TableColumn<>("Película");
        TableColumn<Sala, String> cLibres = new TableColumn<>("Butacas libres");

        cNumero.setCellValueFactory(
                p -> new SimpleStringProperty(String.valueOf(p.getValue().getNumeroSala()))
        );


        cPelicula.setCellValueFactory(
                p -> new SimpleStringProperty(p.getValue().getPelicula())
        );

        cLibres.setCellValueFactory(
                p -> new SimpleStringProperty(String.valueOf(p.getValue().contarLibres()))
        );

        tabla.getColumns().addAll(cNumero, cPelicula, cLibres);

        root.getChildren().addAll(titulo, tabla);
    }

    public VBox getRoot() {
        return root;
    }
}
