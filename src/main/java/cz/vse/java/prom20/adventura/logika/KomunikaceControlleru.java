/*
 * @className KomunikaceControlleru
 * @author Miroslav Prokop 2019
 * @version 1.0.1-SNAPSHOT
 */

package cz.vse.java.prom20.adventura.logika;


public class KomunikaceControlleru {
    private String predavanyPokemon;
    Hra hra;
    private boolean bylUzBoj = false;


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
