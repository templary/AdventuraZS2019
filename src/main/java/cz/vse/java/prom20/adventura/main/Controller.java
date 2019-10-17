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
    Start start;
    Pokemon pokemon;
    ControllerSouboje controllerSouboje;
    KomunikaceControlleru komunikaceControlleru;
    private String predavanyPokemon;

    public void setStartAndComunication(Start start, KomunikaceControlleru komunikaceControlleru) {
        this.start = start;
        this.komunikaceControlleru = komunikaceControlleru;
    }

    @FXML
    private TextArea text = new TextArea();
    @FXML
    private TextField terminal;
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

    void inicializace(IHra iHra) {
        this.hra = iHra;
        batoh = iHra.getBatoh();
        pokemoni = iHra.getPokemoni();
        textVypis.setText(this.hra.vratUvitani());
        setlistOfItemsInRoom();
        setListOfPokemonsInRoom();
        setListOfItemsInBackPack();
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
                    String currentItem = listOfItemsInRoom.getSelectionModel().getSelectedItem();
                    System.out.println(currentItem);
                    textVypis.setText(hra.zpracujPrikaz("batoh seber " + currentItem));
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
                // controllerSouboje.setHra(hra);
                if (event.getClickCount() == 2) {
                    String currentPokemon = listOfPokemonsInRoom.getSelectionModel().getSelectedItem();
                    //hra.getKomunikaceKontroleru().setPredavanyPokemon(currentPokemon);
                    System.out.println(currentPokemon);

                    if (pokemoni.getSetChycenychPokemonu().size() < 1 && hra.getHerniPlan().getAktualniMistnost().getUrovenHernihoPlanu() == 2) {
                        textVypis.setText(hra.zpracujPrikaz("chytni " + currentPokemon));

                    } else {
                        Gui gui = start.getGui();
                        gui.startSouboje();
                        gui.getKomunikaceControlleru().setPredavanyPokemon(currentPokemon);
                    }


                    listRefresh();

                }
            }
        });
    }

    // public StringProperty getPredavanyPokemon() {
    //  return new SimpleStringProperty(predavanyPokemon);
    // }

    @FXML
    private void handleButtonBoj(javafx.event.ActionEvent event) {
        System.out.println("boj");
        System.out.println(predavanyPokemon);
    }


/*    private void start() {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXMLSouboje.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Souboje");
            stage.setScene(new Scene(root, 600, 300));
            stage.show();
            //controllerSouboje.setHra(hra);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


    @FXML
    private void handleButtonTerminal(javafx.event.ActionEvent event) {
        System.out.println("čtení");
        System.out.println(terminal.getText());

        String odpoved = hra.zpracujPrikaz(terminal.getText());
        textVypis.setText(odpoved);
        listRefresh();
        System.out.println(batoh.getNazvyVeci());
        // System.out.println(hra.getKomunikaceKontroleru().getPredavanyPokemon());


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
        listOfItemsInBackPack.setItems(listSetter(batoh.getNazvyVeci()));
    }

    @FXML
    private void setListOfExits() {
        listOfExits.setItems(listSetter(hra.getHerniPlan().getAktualniMistnost().getNazvyVychodu()));
    }

    @FXML
    private void setlistOfPokemonsInPokeball() {
        //noinspection unchecked
        listOfPokemonsInPokeball.setItems(listSetter(pokemoni.getNazvyChycenychPokemonu()));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

