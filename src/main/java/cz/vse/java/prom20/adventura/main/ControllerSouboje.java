package cz.vse.java.prom20.adventura.main;


import cz.vse.java.prom20.adventura.logika.Hra;
import cz.vse.java.prom20.adventura.logika.KomunikaceControlleru;
import cz.vse.java.prom20.adventura.logika.Pokemon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;


public class ControllerSouboje implements Initializable {


    private KomunikaceControlleru komunikaceControlleru;
    private Hra hra;
    private int counter = 0;
    private boolean bylUzBoj = false;


    @FXML
    private AnchorPane anchorPaneSouboje;
    @FXML
    private ComboBox<String> comboVyberPokemona;
    private Controller controller;
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


    public void setHra(Hra hra) {
        this.hra = hra;
    }

    void setKomunikaceControlleru(KomunikaceControlleru komunikaceControlleru) {
        this.komunikaceControlleru = komunikaceControlleru;
    }


    public void setController(Controller controller) {
        this.controller = controller;
    }


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
        ObservableList<String> list = FXCollections.observableArrayList(stringSet);

        return list;
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
        Pokemon pokemonSouper = hra.getHerniPlan().getAktualniMistnost().getPokemonPokudTuJe(souper);

        return pokemonSouper;
    }


    private Pokemon getTvujPokemon() {
        Pokemon tvujPokemon = hra.getPokemoni().getPokemonPokudTuJe(stringTvujPokemon);

        return tvujPokemon;
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


    @FXML
    private void handleButtonBoj(javafx.event.ActionEvent event) {
        if (!bylUzBoj) {
            textAreaVysledek.setText(hra.zpracujPrikaz("bojujgui " + getSouperuvPokemon().getJmenoPokemona() + " " + getTvujPokemon().getJmenoPokemona()));
            bylUzBoj = true;
        }
    }


    private void refresh() {
        setSouperStats();
        comboVyberPokemona();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Metoda na refresh předaných informací z hlavního kontroleru
     * //TODO jak udělat refresh druhého okna po spuštění chytřeji?
     */
    @FXML
    private void onMouseEnteredSouboje() {
        anchorPaneSouboje.setOnMouseEntered(event -> {
            System.out.println("test");
            if (counter == 0) {
                refresh();
                counter++;
            }
        });
    }


}

