package cine.controller;

import cine.model.CineData;
import cine.model.Cliente;
import cine.view.SalasView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblMensaje;

    @FXML
    private void onLogin() {
        String email = txtEmail.getText().trim();
        String pass = txtPassword.getText().trim();

        if (email.isEmpty() || pass.isEmpty()) {
            lblMensaje.setText("Complete email y contraseña");
            return;
        }

        Cliente encontrado = null;
        for (Cliente c : CineData.getClientes()) {
            if (c.getEmail().equalsIgnoreCase(email)
                    && c.getPassword().equals(pass)) {
                encontrado = c;
                break;
            }
        }

        if (encontrado == null) {
            lblMensaje.setText("Email o contraseña incorrectos");
        } else {
            lblMensaje.setText("");
            CineData.setClienteActual(encontrado);

            Stage stage = (Stage) txtEmail.getScene().getWindow();
            SalasView salasView = new SalasView(stage);
            Scene scene = new Scene(salasView.getRoot(), 600, 400);
            stage.setScene(scene);
        }
    }

    @FXML
    private void onRegister() {
        try {
            Stage stage = (Stage) txtEmail.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/cine/view/Register.fxml"));
            stage.setScene(new Scene(root, 400, 350));
            stage.setTitle("Cine - Registro clientes");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
