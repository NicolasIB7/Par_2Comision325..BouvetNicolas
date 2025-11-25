package cine.view;

import cine.model.CineData;
import cine.model.Cliente;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {

    private VBox root;

    public LoginView(Stage stage) {
        root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Label titulo = new Label("Login clientes");
        titulo.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");

        TextField txtEmail = new TextField();
        txtEmail.setPromptText("Email");

        PasswordField txtPassword = new PasswordField();
        txtPassword.setPromptText("Contraseña");

        Label lblMensaje = new Label();

        Button btnLogin = new Button("Ingresar");
        Button btnDemo = new Button("Autocompletar Ana (demo)");

        btnDemo.setOnAction(e -> {
            txtEmail.setText("ana@mail.com");
            txtPassword.setText("1234");
        });

        btnLogin.setOnAction(e -> {
            String email = txtEmail.getText().trim();
            String pass = txtPassword.getText().trim();

            if (email.isEmpty() || pass.isEmpty()) {
                lblMensaje.setText("Complete email y contraseña");
                return;
            }

            Cliente cli = CineData.login(email, pass);
            if (cli == null) {
                lblMensaje.setText("Email o contraseña incorrectos");
            } else {
                CineData.setClienteActual(cli);
                lblMensaje.setText("");

                SalasView salasView = new SalasView(stage);
                Scene sceneSalas = new Scene(salasView.getRoot(), 600, 400);
                stage.setScene(sceneSalas);
            }
        });

        root.getChildren().addAll(titulo, txtEmail, txtPassword, btnLogin, btnDemo, lblMensaje);
    }

    public VBox getRoot() {
        return root;
    }
}
