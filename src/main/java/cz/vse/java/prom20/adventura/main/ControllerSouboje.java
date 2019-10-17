package cz.vse.java.prom20.adventura.main;


import cz.vse.java.prom20.adventura.logika.Hra;
import cz.vse.java.prom20.adventura.logika.KomunikaceControlleru;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;


public class ControllerSouboje implements Initializable {

    KomunikaceControlleru komunikaceControlleru;
    Hra hra;

    Controller controller;
    @FXML
    private Text souperTextLVL;
    @FXML
    private Text souperTextJmeno;

    public void setHra(Hra hra) {
        this.hra = hra;
    }

    public void setKomunikaceControlleru(KomunikaceControlleru komunikaceControlleru) {
        this.komunikaceControlleru = komunikaceControlleru;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setSouperStats() {
        String souper = komunikaceControlleru.getPredavanyPokemon();

        souperTextJmeno.setText(souper);

    }


    @FXML
    private void handleButtonBoj(javafx.event.ActionEvent event) {
        setSouperStats();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

