package cz.vse.java.prom20.adventura.main;

import cz.vse.java.prom20.adventura.logika.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;


public class Controller implements Initializable {
    private Mistnost mistnost;
    private Vec vec;
    private Batoh batoh;
    private HerniPlan herniPlan;
    private Pokemoni pokemoni;
    private Start start;
    private Pokemon pokemon;
    private ControllerSouboje controllerSouboje;
    private KomunikaceControlleru komunikaceControlleru;
    private String predavanyPokemon;
    private Gui gui;
    private UkladaniHry ukladaniHry;


    @FXML
    private Text textLokace;
    @FXML
    private MenuItem menuBarNovaHra;
    @FXML
    private TextArea text = new TextArea();
    @FXML
    private TextField terminal;
    @FXML
    private ListView<String> listOfItemsInRoom = new ListView<>();
    @FXML
    private ListView<String> listOfItemsInBackPack = new ListView<>();
    @FXML
    private ListView<String> listOfPokemonsInRoom = new ListView<>();
    @FXML
    private ListView<String> listOfPokemonsInPokeball = new ListView<>();
    @FXML
    private ListView<String> listOfExits = new ListView<>();
    @FXML
    private TextArea textVypis;
    @FXML
    private ImageView imageMapa = new ImageView();
    void setStartAndComunication(Start start, KomunikaceControlleru komunikaceControlleru, Gui gui) {
        this.start = start;
        this.komunikaceControlleru = komunikaceControlleru;
        this.gui = gui;
    }

    private IHra hra;

    void inicializace(IHra iHra) {
        this.hra = iHra;
        batoh = iHra.getBatoh();
        pokemoni = iHra.getPokemoni();
        textVypis.setText(this.hra.vratUvitaniProGUI());
        //TODO testovací pokemon
        Pokemon pokemon = new Pokemon("test", 1, 11111, 111);
        pokemoni.chytPokemona(pokemon);
        refresh();

    }

    private void predejController() {
        komunikaceControlleru.setController(this);
    }


    void setControllerSouboje(ControllerSouboje controllerSouboje) {
        this.controllerSouboje = controllerSouboje;
    }

    private void predejControllerControlleruSouboje() {
        controllerSouboje.setController(this);
    }

    void refresh() {
        setListOfExits();
        setListOfPokemonsInRoom();
        setlistOfItemsInRoom();
        setListOfItemsInBackPack();
        setlistOfPokemonsInPokeball();
        settextLokace();
    }


    private StringBuilder getTextAreaText() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(text.getText()).append("\n");
        return stringBuilder;
    }


    @FXML
    private void onClickListOfExits() {
        listOfExits.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String curentItem = listOfExits.getSelectionModel().getSelectedItem();
                System.out.println(curentItem);
                textVypis.setText(hra.zpracujPrikaz("jdi " + curentItem));
                refresh();
            }
        });
    }

    @FXML
    private void onClicklistOfItemsInRoom() {
        listOfItemsInRoom.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String currentItem = listOfItemsInRoom.getSelectionModel().getSelectedItem();
                System.out.println(currentItem);
                textVypis.setText(hra.zpracujPrikaz("batoh seber " + currentItem));
                refresh();

            }
        });
    }

    @FXML
    private void onClicklistOfPokemonsInRoom() {
        listOfPokemonsInRoom.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String currentPokemon = listOfPokemonsInRoom.getSelectionModel().getSelectedItem();
                System.out.println(currentPokemon);


                if (hra.getHerniPlan().getAktualniMistnost().getUrovenHernihoPlanu() > 2 && pokemoni.getSetChycenychPokemonu().size() == 0) {
                    hra.konecHry();
                    textVypis.setText(hra.vratUtokBezPokemona());
                } else {
                    if (pokemoni.getSetChycenychPokemonu().size() < 1 || hra.getHerniPlan().getAktualniMistnost().getUrovenHernihoPlanu() == 2) {
                        textVypis.setText(hra.zpracujPrikaz("chytni " + currentPokemon));
                    } else {
                        refresh();
                        Gui gui = start.getGui();
                        gui.getKomunikaceControlleru().setPredavanyPokemon(currentPokemon);
                        gui.startSouboje();
                        predejControllerControlleruSouboje();
                    }
                }
                refresh();
            }
        });
    }

    @FXML
    private void handleButtonBoj(javafx.event.ActionEvent event) {
        System.out.println("boj");
        System.out.println(predavanyPokemon);
    }


    @FXML
    private void handleButtonTerminal(javafx.event.ActionEvent event) {
        System.out.println("čtení");
        System.out.println(terminal.getText());
        if (terminal.getText().equals("bojuj")) {
            textVypis.setText("Tento příkaz je pouze pro textové rozhraní!");
        }

        String odpoved = hra.zpracujPrikaz(terminal.getText());
        textVypis.setText(odpoved);
        refresh();
        System.out.println(batoh.getNazvyVeci());


    }


    private ObservableList<String> listSetter(Set<String> stringSet) {

        return FXCollections.observableArrayList(stringSet);
    }

    private void listCleaner() {
        listOfItemsInRoom.getItems().clear();
        listOfItemsInBackPack.getItems().clear();
        listOfPokemonsInPokeball.getItems().clear();
        listOfPokemonsInRoom.getItems().clear();
    }

    @FXML
    private void setlistOfItemsInRoom() {
        listOfItemsInRoom.setItems(listSetter(hra.getHerniPlan().getAktualniMistnost().getNazvyVeci()));
        listOfItemsInRoom.setCellFactory(param -> new ListCell<String>() {
            private ImageView imageView = new ImageView();

            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Image vychodImage = new Image(getClass().getClassLoader().getResourceAsStream("imgItems/" + name + ".png"));
                    imageView.setImage(vychodImage);
                    setText(name);
                    setGraphic(imageView);
                }
            }
        });
    }

    @FXML
    private void setListOfPokemonsInRoom() {
        listOfPokemonsInRoom.setItems(listSetter(hra.getHerniPlan().getAktualniMistnost().getNazvyPokemonu()));
        listOfPokemonsInRoom.setCellFactory(param -> new ListCell<String>() {
            private ImageView imageView = new ImageView();

            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Image vychodImage = new Image(getClass().getClassLoader().getResourceAsStream("imgItems/Pokébal.png"));
                    imageView.setImage(vychodImage);
                    setText(name);
                    setGraphic(imageView);
                }
            }
        });
    }

    @FXML
    private void setListOfItemsInBackPack() {
        listOfItemsInBackPack.setItems(listSetter(batoh.getNazvyVeci()));
        listOfItemsInBackPack.setCellFactory(param -> new ListCell<String>() {
            private ImageView imageView = new ImageView();

            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Image vychodImage = new Image(getClass().getClassLoader().getResourceAsStream("imgItems/" + name + ".png"));
                    imageView.setImage(vychodImage);
                    setText(name);
                    setGraphic(imageView);
                }
            }
        });
    }

    @FXML
    private void setListOfExits() {
        listOfExits.setItems(listSetter(hra.getHerniPlan().getAktualniMistnost().getNazvyVychodu()));
    }

    @FXML
    private void setlistOfPokemonsInPokeball() {
        //noinspection unchecked
        listOfPokemonsInPokeball.setItems(listSetter(pokemoni.getNazvyChycenychPokemonu()));
        listOfPokemonsInPokeball.setCellFactory(param -> new ListCell<String>() {
            private ImageView imageView = new ImageView();

            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Image vychodImage = new Image(getClass().getClassLoader().getResourceAsStream("imgItems/Pokébal.png"));
                    imageView.setImage(vychodImage);
                    setText(name);
                    setGraphic(imageView);
                }
            }
        });
    }

    private void settextLokace() {
        textLokace.setText(hra.getHerniPlan().getAktualniMistnost().getNazev());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    //-----------------menu část-----------------

    @FXML
    private void onActionMenuBarNovaHra(ActionEvent actionEvent) {
        listCleaner();
        hra.newGame();
        gui.startMain();
        predejController();
        //gui.terminateMainWindow();
        refresh();
    }

    @FXML
    private void onActionMenuBarUkoncit(ActionEvent actionEvent) {
        hra.konecHry();
        textVypis.setText(hra.vratEpilog());
        CasovanyKonecHry casovanyKonecHry = new CasovanyKonecHry();
        casovanyKonecHry.exitTimer();
        textVypis.setText("Hra se ukončí za 5 sekund.");
    }


    @FXML
    private void onActionMenuBarNapoveda(ActionEvent actionEvent) {
        gui.starNapovedaPokemoni();
    }

    @FXML
    private void onActionMenuBarHerniPlan(ActionEvent actionEvent) {
        gui.starMapa();
    }
}
