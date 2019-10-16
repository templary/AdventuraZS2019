
package cz.vse.java.prom20.adventura.main;


import cz.vse.java.prom20.adventura.logika.Batoh;
import cz.vse.java.prom20.adventura.logika.Hra;
import cz.vse.java.prom20.adventura.logika.Pokemoni;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Start extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    /*    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/FXML.fxml"));

        Controller controller = loader.getController();
        controller.inicializace(new Hra());

        primaryStage.setTitle("POKEMOOOOON!");
        primaryStage.setScene(new Scene(root, 850, 900));
        primaryStage.show();
    }*/

    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML.fxml"));
        Parent root = loader.load();

        stage.setTitle("Adventura");
        stage.setScene(new Scene(root));

        Controller controller = loader.getController();
        controller.inicializace(new Hra());
        //controller.setlistOfItemsInRoom();
        Batoh batoh = new Batoh();
        Pokemoni pokemoni = new Pokemoni();

        stage.show();

    }
}