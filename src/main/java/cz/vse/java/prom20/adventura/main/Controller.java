package cz.vse.java.prom20.adventura.main;

import cz.vse.java.prom20.adventura.logika.IHra;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class Controller extends Pane {

    @FXML
    TextArea vystup;

    @FXML
    TextField vstup;

    @FXML
    Button tlacitkoZpracuj;

    private IHra hra;

    public void inicializace(IHra hra) {
        this.hra = hra;
        this.vypisUvitani();
        vystup.setEditable(false);
        vstup.requestFocus();
    }

    private void vypisUvitani() {
        this.vypis(hra.vratUvitani());
    }

    private void vypis(String text) {


    }

    public void zpracujVstup(ActionEvent event) {

    }


}
