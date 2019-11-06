package cz.vse.java.prom20.adventura.main;


import cz.vse.java.prom20.adventura.logika.Batoh;
import cz.vse.java.prom20.adventura.logika.Hra;
import cz.vse.java.prom20.adventura.logika.KomunikaceControlleru;
import cz.vse.java.prom20.adventura.logika.Pokemoni;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * trída  TextoveRozhrani
 * <p>
 * Toto je uživatelského rozhraní aplikace Adventura
 * Tato třída vytváří instanci třídy Hra, která představuje logiku aplikace.
 * Čte vstup zadaný uživatelem a předává tento řetězec logice a vypisuje odpověď logiky na konzoli.
 *
 * @author Miroslav Prokop
 * @version 1.0
 */

public class Gui {
    private Hra hra;
    private Start start;
    private Batoh batoh = new Batoh();
    private Pokemoni pokemoni = new Pokemoni();
    private Controller controller;
    private Controller mainController;
    private Stage mainStage;
    private KomunikaceControlleru komunikaceControlleru = new KomunikaceControlleru(hra);


    @FXML
    private ImageView imageMapa = new ImageView();
    Gui(Hra hra, Start start) {
        this.hra = hra;
        this.start = start;
    }


    void startMain() {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            mainStage = stage;
            stage.setTitle("Souboje");
            stage.setScene(new Scene(root, 850, 900));
            Controller controller = loader.getController();
            this.controller = controller;
            mainController = this.controller;
            controller.inicializace(hra);
            controller.setStartAndComunication(start, komunikaceControlleru, this);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void terminateMainWindow() {
        mainStage.hide();
    }


    void startSouboje() {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXMLSouboje.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Souboje");
            stage.setScene(new Scene(root, 600, 300));
            ControllerSouboje controllerSouboje = loader.getController();
            stage.show();
            controllerSouboje.setHra(hra);
            controllerSouboje.setKomunikaceControlleru(komunikaceControlleru);
            controllerSouboje.setStartAndGUI(start, this);
            controller.setControllerSouboje(controllerSouboje);
            controllerSouboje.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void starMapa() {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/mapa.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Mapa");
            stage.setScene(new Scene(root, 678, 432));
            Controller controller = loader.getController();
            stage.show();
            Image image = new Image("file:mapa.png");
            imageMapa.setImage(image);
            imageMapa.setCache(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void starNapovedaPokemoni() {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/napovedaPokemoni.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Tabulka pokémonů");
            stage.setScene(new Scene(root, 623, 583));
            Controller controller = loader.getController();
            stage.show();
            Image image = new Image("file:pokemoni.png");
            imageMapa.setImage(image);
            imageMapa.setCache(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void starInfoWindow() {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/info.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Konec");
            stage.setScene(new Scene(root, 325, 181));
            ControllerSouboje controllerSouboje = loader.getController();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Hra getHra() {
        return hra;
    }

    public Batoh getBatoh() {
        return batoh;
    }

    public Pokemoni getPokemoni() {
        return pokemoni;
    }

    public Start getStart() {
        return start;
    }

    KomunikaceControlleru getKomunikaceControlleru() {
        return komunikaceControlleru;
    }
}