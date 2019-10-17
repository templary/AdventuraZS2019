
package cz.vse.java.prom20.adventura.main;


import cz.vse.java.prom20.adventura.logika.Hra;
import cz.vse.java.prom20.adventura.logika.KomunikaceControlleru;
import javafx.application.Application;
import javafx.stage.Stage;



public class Start extends Application {
    Gui gui = new Gui(new Hra(), this);
    KomunikaceControlleru komunikaceControlleru = new KomunikaceControlleru(gui.getHra());
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        gui.startMain();
        // gui.startSouboje();
    }

    public Gui getGui() {
        return gui;
    }

    //public KomunikaceControlleru getKomunikaceControlleru() {
    //    return komunikaceControlleru;
    //}
}