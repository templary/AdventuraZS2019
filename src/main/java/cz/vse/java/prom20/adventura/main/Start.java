
package cz.vse.java.prom20.adventura.main;


import cz.vse.java.prom20.adventura.logika.Hra;
import cz.vse.java.prom20.adventura.logika.IHra;
import cz.vse.java.prom20.adventura.logika.KomunikaceControlleru;
import cz.vse.java.prom20.adventura.uiText.TextoveRozhrani;
import javafx.application.Application;
import javafx.stage.Stage;


public class Start extends Application {

    private Gui gui = new Gui(new Hra(), this);
    KomunikaceControlleru komunikaceControlleru = new KomunikaceControlleru(gui.getHra());

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("text")) {
            IHra hra = new Hra();
            TextoveRozhrani textoveRozhrani = new TextoveRozhrani(hra);
            textoveRozhrani.hraj();
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        gui.startMain();
    }

    Gui getGui() {
        return gui;
    }
}