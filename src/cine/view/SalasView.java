package cine.view;

import cine.model.CineData;
import cine.model.Sala;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SalasView {

    private VBox root;

    public SalasView(Stage stage) {
        root = new VBox(10);
        root.setPadding(new Insets(20));

        Label titulo = new Label("Salas disponibles");
        titulo.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

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

        Button btnButacas = new Button("Ver butacas");

        btnButacas.setOnAction(e -> {
            Sala sel = tabla.getSelectionModel().getSelectedItem();
            if (sel != null) {
                ButacasView butacasView = new ButacasView(stage, sel);
                Scene sceneButacas = new Scene(butacasView.getRoot(), 600, 400);
                stage.setScene(sceneButacas);
            }
        });

        root.getChildren().addAll(titulo, tabla, btnButacas);
    }

    public VBox getRoot() {
        return root;
    }
}
