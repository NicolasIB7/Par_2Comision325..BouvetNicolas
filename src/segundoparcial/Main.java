
package segundoparcial;

import cine.model.CineData;
import cine.view.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        CineData.cargarDatosDemo();

        LoginView loginView = new LoginView(stage);
        Scene scene = new Scene(loginView.getRoot(), 400, 300);

        stage.setTitle("Cine - Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
