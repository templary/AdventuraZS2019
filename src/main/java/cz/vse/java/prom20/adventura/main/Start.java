
package cz.vse.java.prom20.adventura.main;


import cz.vse.java.prom20.adventura.logika.Hra;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Start extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML.fxml"));
        primaryStage.setTitle("POKEMOOOOON!");
        primaryStage.setScene(new Scene(root, 850, 900));
        primaryStage.show();
    }
}