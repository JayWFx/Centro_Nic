package ni.uam.edu.centronic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("matricula-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1050, 720);
        stage.setTitle("Centro Nicaragüense de Formación Tecnológica - Matrícula");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}