package cine.view;

import cine.model.CineData;
import cine.model.Cliente;
import cine.model.Sala;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConfirmacionView {

    private VBox root;

    public ConfirmacionView(Stage stage, Sala sala, int fila, int asiento) {
        root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Cliente cliente = CineData.getClienteActual();

        Label lblTitulo = new Label("Confirmación de compra");
        lblTitulo.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        Label lblCliente = new Label("Cliente: "
                + cliente.getNombre() + " (" + cliente.getEmail() + ")");

        Label lblPelicula = new Label("Película: " + sala.getPelicula());
        Label lblSala = new Label("Sala: " + sala.getNumeroSala());
        Label lblButaca = new Label("Butaca seleccionada: fila " + fila + ", asiento " + asiento);
        Label lblPrecio = new Label("Precio: $" + sala.getPrecioEntrada());

        Label lblMensaje = new Label();

        Button btnConfirmar = new Button("Confirmar compra");
        Button btnCancelar = new Button("Cancelar");

        btnConfirmar.setOnAction(e -> {
            boolean ok = CineData.comprarEntrada(cliente, sala, fila, asiento);

            if (ok) {
                lblMensaje.setText("Compra realizada correctamente.");
                cine.storage.CineStorage.guardarDatos();
                SalasView salasView = new SalasView(stage);
                Scene sceneSalas = new Scene(salasView.getRoot(), 600, 400);
                stage.setScene(sceneSalas);

            } else {
                lblMensaje.setText("Error: la butaca ya está ocupada.");
            }
        });

        btnCancelar.setOnAction(e -> {
            SalasView salasView = new SalasView(stage);
            Scene escenaSalas = new Scene(salasView.getRoot(), 600, 400);
            stage.setScene(escenaSalas);
        });

        root.getChildren().addAll(
                lblTitulo,
                lblCliente,
                lblPelicula,
                lblSala,
                lblButaca,
                lblPrecio,
                btnConfirmar,
                btnCancelar,
                lblMensaje
        );
    }

    public VBox getRoot() {
        return root;
    }
}
