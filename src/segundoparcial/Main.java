package segundoparcial;

import cine.model.CineData;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import cine.storage.CineStorage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        boolean cargado = CineStorage.cargarDatos();
        if (!cargado) {
            CineData.cargarDatosDemo();
            CineStorage.guardarDatos();
        }

        Parent root = FXMLLoader.load(
                getClass().getResource("/cine/view/Login.fxml")
        );

        Scene scene = new Scene(root, 400, 250);
        stage.setTitle("Cine - Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
