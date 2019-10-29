/*
 * @className KomunikaceControlleru
 * @author Miroslav Prokop 2019
 * @version 1.0.1-SNAPSHOT
 */

package cz.vse.java.prom20.adventura.logika;


import cz.vse.java.prom20.adventura.main.Controller;
import cz.vse.java.prom20.adventura.main.ControllerSouboje;

public class KomunikaceControlleru {
    private String predavanyPokemon;
    Hra hra;
    ControllerSouboje controllerSouboje;
    Controller controller;
    private boolean bylUzBoj = false;

    public ControllerSouboje getControllerSouboje() {
        return controllerSouboje;
    }

    public void setControllerSouboje(ControllerSouboje controllerSouboje) {
        this.controllerSouboje = controllerSouboje;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public KomunikaceControlleru(Hra hra) {
        this.hra = hra;
    }

    public void setUzBojProbehl(boolean uzBojProbehl) {
        this.bylUzBoj = uzBojProbehl;
    }

    public void setPredavanyPokemon(String predavanyPokemon) {
        this.predavanyPokemon = predavanyPokemon;
    }

    public String getPredavanyPokemon() {
        return predavanyPokemon;
    }

    public boolean getBylUzBoj() {
        return bylUzBoj;
    }
}
