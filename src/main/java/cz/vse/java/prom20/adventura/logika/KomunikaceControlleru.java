package cz.vse.java.prom20.adventura.logika;

public class KomunikaceControlleru {
    private String predavanyPokemon;

    public KomunikaceControlleru() {
        predavanyPokemon = null;
    }

    public String getPredavanyPokemon() {
        return predavanyPokemon;
    }

    public void setPredavanyPokemon(String predavanyPokemon) {
        this.predavanyPokemon = predavanyPokemon;
    }
}
