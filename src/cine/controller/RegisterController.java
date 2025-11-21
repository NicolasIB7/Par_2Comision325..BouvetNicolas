
package cine.controller;

import cine.model.CineData;
import cine.model.Cliente;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class RegisterController {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblMensaje;

    @FXML
    private void onRegistrar() {
        String nombre = txtNombre.getText().trim();
        String email = txtEmail.getText().trim();
        String pass = txtPassword.getText().trim();

        if (nombre.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            lblMensaje.setText("Complete todos los campos");
            return;
        }

        Cliente nuevo = new Cliente(nombre, email, pass);

        boolean agregado = CineData.registrarCliente(nuevo);

        if (!agregado) {
            lblMensaje.setText("El email ya está registrado");
        } else {
            lblMensaje.setText("Registrado correctamente");
        }
    }

    @FXML
    private void onVolver() {
        try {
            Stage stage = (Stage) txtNombre.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/cine/view/Login.fxml"));
            stage.setScene(new Scene(root, 400, 250));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

