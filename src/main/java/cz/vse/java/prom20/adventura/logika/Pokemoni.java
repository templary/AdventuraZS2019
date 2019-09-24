package cz.vse.java.prom20.adventura.logika;


import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Třída PrikazFinal implementuje pokémony a práci s nimi.
 * třída se využívá pro všechny operace prováděné nad instancemi pokémon.
 *
 * @author Miroslav Prokop
 * @version 1.0
 */
public class Pokemoni {
    private Set<Pokemon> chyceniPokemoni;


    public Pokemoni() {
        this.chyceniPokemoni = new HashSet<>();
    }

    /**
     * Metoda vrací seznam chycených pokémonů
     * */
    public String getSeznamChycenychPokemonuJakoText() {
        if (this.chyceniPokemoni.size() > 0) {
            String s = "Máš u sebe: ";
            for (Pokemon p : this.chyceniPokemoni) {
                s = s + p.getJmenoPokemona() + " ";
            }
            return s;
        } else {
            return "Nemáš zatím žádného pokémona";
        }
    }

    /**
     * Metoda smaže pokémona z setu chycených
     * */
    public void zabijPokemona(Pokemon p) {
        chyceniPokemoni.remove(p);
    }

    /**
     * Metoda přidá pokémona do setu chycených +
     * vrací stringové potvrzení
     * */
    public String chytPokemona(Pokemon p) {

        this.chyceniPokemoni.add(p);
        return p.getJmenoPokemona() + " jsi úspěšně chytnul.";

    }

    /**
     * Metoda vrací seznam chycených pokémonů
     * */
    public Set getNazvyChycenychPokemonu() {
        return chyceniPokemoni.stream()
                .map(pokemon -> pokemon.getJmenoPokemona())
                .collect(Collectors.toSet());
    }

    /**
     * Metoda vytvoří z setu chycených pokémonu array
     * vrací array chycených.
     * */
    public Pokemon[] getChyceniPokemoniArray(int poleLength) {
        int i = 0;
        Pokemon[] arrayChycenych = new Pokemon[poleLength];

        for (Pokemon x : this.chyceniPokemoni) {
            arrayChycenych[i++] = x;
        }

        return arrayChycenych;
    }

    /**
     * @param nazevPokemona název pokémona k testování
     * Metoda kontroluje zda pokémon existuje,
     * vrací Pokemona pokud existuje, pokud ne Null.
     * */
    public Pokemon getPokemonPokudTuJe(String nazevPokemona) {
        for (Pokemon pokemon : this.chyceniPokemoni) {
            if (pokemon.getJmenoPokemona().equals(nazevPokemona)) {
                return pokemon;
            }
        }
        return null;
    }

    /**
     * Metoda přidává sílu pokémonovi podle parametru oKolik a jakému pokémonovi.
     * vrací string potvrzení.
     * */
    public String trenujPokemona(Pokemon pokemon, int oKolik) {
        pokemon.setSilaPokemona((pokemon.getSilaPokemona() + oKolik));
        return "Trénink proběl uspěšně, tvuj pokémon: " + pokemon.getJmenoPokemona() + "má nyní sílu" + pokemon.getSilaPokemona();
    }


    /**
     * Metoda vrací set chycených pokémonu
     * */
    public Set getSetChycenychPokemonu() {
        return chyceniPokemoni;
    }

    /**
     * Metoda vrací jméno pokémona jako text
     * */
    public String getJmenoPokemonaJakoText(Pokemon p) {
        return p.getJmenoPokemona();
    }

    /**
     * Metoda vrací typ pokémona.
     * */
    public String getTypPokemona(Pokemon pokemon) {
        return getTypPokemona(pokemon);
    }
}