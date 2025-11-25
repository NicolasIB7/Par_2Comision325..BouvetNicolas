package cine.view;

import cine.model.Sala;
import cine.model.Butaca;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ButacasView {

    private BorderPane root;
    private Sala sala;
    private int filaSeleccionada = -1;
    private int nroSeleccionado = -1;

    public ButacasView(Stage stage, Sala sala) {
        this.sala = sala;
        root = new BorderPane();
        root.setPadding(new Insets(10));

        Label lblTitulo = new Label("Butacas - Sala " + sala.getNumeroSala()
                + " - " + sala.getPelicula());
        lblTitulo.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");
        stage.setTitle("Cine - Butacas");
        BorderPane.setAlignment(lblTitulo, Pos.CENTER);
        root.setTop(lblTitulo);

        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10));

        int filas = sala.getFilas();
        int columnas = sala.getColumnas();

        for (int f = 1; f <= filas; f++) {
            for (int c = 1; c <= columnas; c++) {
                Butaca b = sala.getButaca(f, c);
                Button btn = new Button(f + "-" + c);
                btn.setMinWidth(40);

                if (b.isOcupada()) {
                    btn.setDisable(true);
                    btn.setStyle("-fx-background-color: lightgray;");
                }

                int filaFinal = f;
                int nroFinal = c;

                btn.setOnAction(e -> {
                    filaSeleccionada = filaFinal;
                    nroSeleccionado = nroFinal;
                });

                grid.add(btn, c - 1, f - 1);
            }
        }

        root.setCenter(grid);

        Label lblSeleccion = new Label("Butaca seleccionada: ");

        Button btnConfirmar = new Button("Confirmar selección");
        Button btnVolver = new Button("Volver a Salas");

        btnConfirmar.setOnAction(e -> {
            if (filaSeleccionada == -1 || nroSeleccionado == -1) {
                lblSeleccion.setText("Seleccione una butaca.");
            } else {
                ConfirmacionView cv = new ConfirmacionView(stage, sala, filaSeleccionada, nroSeleccionado);
                Scene escena = new Scene(cv.getRoot(), 400, 300);
                stage.setScene(escena);
            }
        });

        btnVolver.setOnAction(e -> {
            SalasView salasView = new SalasView(stage);
            Scene sceneSalas = new Scene(salasView.getRoot(), 600, 400);
            stage.setScene(sceneSalas);
        });

        HBox abajo = new HBox(10, lblSeleccion, btnConfirmar, btnVolver);
        abajo.setAlignment(Pos.CENTER);
        abajo.setPadding(new Insets(10));

        root.setBottom(abajo);
    }

    public BorderPane getRoot() {
        return root;
    }
}
