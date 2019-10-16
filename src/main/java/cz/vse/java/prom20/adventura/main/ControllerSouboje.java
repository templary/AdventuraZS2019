package cz.vse.java.prom20.adventura.main;


import cz.vse.java.prom20.adventura.logika.KomunikaceControlleru;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;


public class ControllerSouboje implements Initializable {


    KomunikaceControlleru komunikaceControlleru;
    Controller controller;
    @FXML
    private Text souperTextLVL;
    @FXML
    private Text souperTextJmeno;


    public void setSouperStats(String jmeno) {
        System.out.println(jmeno);
        souperTextJmeno.setText(jmeno);

    }

    @FXML
    private void handleButtonBoj(javafx.event.ActionEvent event) {
        System.out.println(komunikaceControlleru.getPredavanyPokemon());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

