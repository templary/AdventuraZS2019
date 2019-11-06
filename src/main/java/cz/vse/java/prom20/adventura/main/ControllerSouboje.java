package cz.vse.java.prom20.adventura.main;


import cz.vse.java.prom20.adventura.logika.CasovanyKonecHry;
import cz.vse.java.prom20.adventura.logika.Hra;
import cz.vse.java.prom20.adventura.logika.KomunikaceControlleru;
import cz.vse.java.prom20.adventura.logika.Pokemon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;


public class ControllerSouboje implements Initializable {


    private KomunikaceControlleru komunikaceControlleru;
    private Controller controller;
    private Hra hra;
    private Start start;
    private Gui gui;
    private int counter = 0;
    private boolean bylUzBoj = false;


    @FXML
    private ComboBox<String> comboVyberPokemona;
    @FXML
    private Text souperTextLVL;
    @FXML
    private Text souperTextJmeno;
    private String stringTvujPokemon;
    @FXML
    private Text souperTextTyp;
    @FXML
    private Text souperTextSila;
    @FXML
    private Text tvujTextJmeno;
    @FXML
    private Text tvujTextTyp;
    @FXML
    private Text tvujTextLVL;
    @FXML
    private Text tvujTextSila;
    @FXML
    private TextArea textAreaVysledek;
    @FXML
    private Button buttonBoj;


    public void setHra(Hra hra) {
        this.hra = hra;
    }

    void setKomunikaceControlleru(KomunikaceControlleru komunikaceControlleru) {
        this.komunikaceControlleru = komunikaceControlleru;
    }

    void setStartAndGUI(Start start, Gui gui) {
        this.start = start;
        this.gui = gui;
    }

    private int clickCounter = 0;


    private void setSouperStats() {

        souperTextJmeno.setText(getSouperuvPokemon().getJmenoPokemona());
        String souperLVL = Integer.toString(getSouperuvPokemon().getLevel());
        String souperTyp = Integer.toString(getSouperuvPokemon().getIntTypPokemona());
        String souperSila = Integer.toString(getSouperuvPokemon().getSilaPokemona());
        souperTextLVL.setText(souperLVL);
        souperTextSila.setText(souperSila);
        souperTextTyp.setText(souperTyp);
    }

    private ObservableList<String> listSetter(Set<String> stringSet) {

        return FXCollections.observableArrayList(stringSet);
    }

    @FXML
    private void comboVyberPokemona() {
        if (hra.getPokemoni().getSetChycenychPokemonu() != null && counter == 0) {
            comboVyberPokemona.setItems(listSetter(hra.getPokemoni().getNazvyChycenychPokemonu()));
        }
    }

    @FXML
    public void handleComboBox(ActionEvent actionEvent) {
        stringTvujPokemon = comboVyberPokemona.getSelectionModel().getSelectedItem();
        setTvojeStats();

    }

    private Pokemon getSouperuvPokemon() {
        String souper = komunikaceControlleru.getPredavanyPokemon();

        return hra.getHerniPlan().getAktualniMistnost().getPokemonPokudTuJe(souper);
    }


    private Pokemon getTvujPokemon() {
        return hra.getPokemoni().getPokemonPokudTuJe(stringTvujPokemon);
    }

    private void setTvojeStats() {

        tvujTextJmeno.setText(getTvujPokemon().getJmenoPokemona());
        String tvujLVL = Integer.toString(getTvujPokemon().getLevel());
        String tvujTyp = Integer.toString(getTvujPokemon().getIntTypPokemona());
        String tvujSila = Integer.toString(getTvujPokemon().getSilaPokemona());
        tvujTextLVL.setText(tvujLVL);
        tvujTextTyp.setText(tvujTyp);
        tvujTextSila.setText(tvujSila);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @FXML
    private void handleButtonBoj(javafx.event.ActionEvent event) {
        if (bylUzBoj) {
            textAreaVysledek.setText("Boj už proběhl, ukonči okno a vyber jiného\npokémona. ");
        }
        if (!comboVyberPokemona.getSelectionModel().isEmpty()) {
            if (!bylUzBoj && clickCounter != 1) {
                String vysledekSouboje = hra.zpracujPrikaz("bojujgui " + getSouperuvPokemon().getJmenoPokemona() + " " + getTvujPokemon().getJmenoPokemona());
                bylUzBoj = true;
                if (vysledekSouboje.contains("Piplup")) {
                    textAreaVysledek.setText(vysledekSouboje);
                    buttonBoj.setText("Konec");
                    clickCounter++;
                    if (clickCounter == 1) {
                        CasovanyKonecHry casovanyKonecHry = new CasovanyKonecHry();
                        casovanyKonecHry.exitTimer();
                    }

                } else {
                    textAreaVysledek.setText(vysledekSouboje);
                }
            }
            controller.refresh();

        }
    }


    void refresh() {
        setSouperStats();
        comboVyberPokemona();
    }

    public void setToKomunikaceBylUzBoj() {
        komunikaceControlleru.setUzBojProbehl(bylUzBoj);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleButtonInfoWindowKonec(ActionEvent actionEvent) {
        CasovanyKonecHry casovanyKonecHry = new CasovanyKonecHry();
        casovanyKonecHry.exitTimer();
        //TODO vypsat že se hra ukonči
    }


}
