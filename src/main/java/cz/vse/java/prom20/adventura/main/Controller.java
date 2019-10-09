package cz.vse.java.prom20.adventura.main;

import cz.vse.java.prom20.adventura.logika.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;


public class Controller implements Initializable {
    Mistnost mistnost;
    Vec vec;
    Batoh batoh;
    HerniPlan herniPlan;
    Pokemoni pokemoni;

    @FXML
    TextArea text = new TextArea();
    @FXML
    TextField terminal;
    @FXML
    private ListView<String> listOfItemsInRoom = new ListView<String>();
    @FXML
    private ListView<String> listOfItemsInBackPack = new ListView<String>();
    @FXML
    private ListView<String> listOfPokemonsInRoom = new ListView<String>();
    @FXML
    private ListView<String> listOfPokemonsInPokeball = new ListView<String>();
    @FXML
    private ListView<String> listOfExits = new ListView<String>();

    @FXML
    private TextArea textVypis;

    private IHra hra;

    public void inicializace(IHra hra) {
        this.hra = hra;
        batoh = new Batoh();
        pokemoni = new Pokemoni();
        textVypis.setText(this.hra.vratUvitani());
        setlistOfItemsInRoom();
        setListOfPokemonsInRoom();
        //setListOfItemsInBackPack();
        setListOfExits();
    }

    private void listRefresh() {
        setListOfExits();
        setListOfPokemonsInRoom();
        setlistOfItemsInRoom();
        setListOfItemsInBackPack();
        setlistOfPokemonsInPokeball();
    }


    private StringBuilder getTextAreaText() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(text.getText()).append("\n");
        return stringBuilder;
    }


    @FXML
    private void onClickListOfExits() {
        listOfExits.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    String curentItem = listOfExits.getSelectionModel().getSelectedItem();
                    System.out.println(curentItem);
                    textVypis.setText(hra.zpracujPrikaz("jdi " + curentItem));
                    listRefresh();
                }
            }
        });
    }

    @FXML
    private void onClicklistOfItemsInRoom() {
        listOfItemsInRoom.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    String curentItem = listOfItemsInRoom.getSelectionModel().getSelectedItem();
                    System.out.println(curentItem);
                    textVypis.setText(hra.zpracujPrikaz("batoh seber " + curentItem));
                    listRefresh();

                }
            }
        });
    }

    @FXML
    private void onClicklistOfPokemonsInRoom() {
        listOfPokemonsInRoom.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    String curentItem = listOfPokemonsInRoom.getSelectionModel().getSelectedItem();
                    System.out.println(curentItem);
                    if (pokemoni.getSetChycenychPokemonu().size() < 1) {
                        textVypis.setText(hra.zpracujPrikaz("chytni " + curentItem));
                    } else {
                        //TODO Preprogramovat PrikazBojuj!
                    }
                    listRefresh();

                }
            }
        });
    }


    @FXML
    private void handleButtonTerminal(javafx.event.ActionEvent event) {
        System.out.println("čtení");
        System.out.println(terminal.getText());

        String odpoved = hra.zpracujPrikaz(terminal.getText());
        textVypis.setText(odpoved);
        listRefresh();
        System.out.println(batoh.getNazvyVeci());


    }


    private ObservableList<String> listSetter(Set<String> stringSet) {
        ObservableList<String> list = FXCollections.observableArrayList(stringSet);

        return list;
    }

    @FXML
    private void setlistOfItemsInRoom() {
        listOfItemsInRoom.setItems(listSetter(hra.getHerniPlan().getAktualniMistnost().getNazvyVeci()));
    }

    @FXML
    private void setListOfPokemonsInRoom() {
        listOfPokemonsInRoom.setItems(listSetter(hra.getHerniPlan().getAktualniMistnost().getNazvyPokemonu()));
    }

    @FXML
    private void setListOfItemsInBackPack() {
        listOfItemsInBackPack.setItems(listSetter(batoh.getNazvyVeci())); //TODO
    }

    @FXML
    private void setListOfExits() {
        listOfExits.setItems(listSetter(hra.getHerniPlan().getAktualniMistnost().getNazvyVychodu()));
    }

    @FXML
    private void setlistOfPokemonsInPokeball() {
        listOfPokemonsInPokeball.setItems(listSetter(pokemoni.getNazvyChycenychPokemonu())); //TODO
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

