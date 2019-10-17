package cz.vse.java.prom20.adventura.main;


import cz.vse.java.prom20.adventura.logika.Batoh;
import cz.vse.java.prom20.adventura.logika.Hra;
import cz.vse.java.prom20.adventura.logika.KomunikaceControlleru;
import cz.vse.java.prom20.adventura.logika.Pokemoni;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    Hra hra;
    Start start;
    Batoh batoh = new Batoh();
    Pokemoni pokemoni = new Pokemoni();
    KomunikaceControlleru komunikaceControlleru = new KomunikaceControlleru(hra);

    public Gui(Hra hra, Start start) {
        this.hra = hra;
        this.start = start;
    }

    public void startMain() {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Souboje");
            stage.setScene(new Scene(root, 850, 900));
            Controller controller = loader.getController();
            controller.inicializace(hra);
            controller.setStartAndComunication(start, komunikaceControlleru);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startSouboje() {
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
            //controllerSouboje.refresh();
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

    public KomunikaceControlleru getKomunikaceControlleru() {
        return komunikaceControlleru;
    }
}
