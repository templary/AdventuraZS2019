package cz.vse.java.prom20.adventura.logika;


import java.util.Set;

/**
 * Třída Pokemon implementuje pokémona.
 * třída se využívá pro konstrukci pokémonů ve hře.
 *
 * @author Miroslav Prokop
 * @version 1.0
 */
public class Pokemon {


    private String jmenoPokemona;
    private int intTypPokemona;
    private int silaPokemona;
    private int level;
    private String[] typPokemona = new String[7];

    /**
     * Konstruktor třídy
     *
     * @param jmenoPokemona  jméno pokémona
     * @param intTypPokemona číselný typ pokémona - který se do Stringu převádí pomocí pole (array typPokemona)
     * @param silaPokemona   int hodnota síly pokémona
     * @param level          int level pokémona
     */
    public Pokemon(String jmenoPokemona, int intTypPokemona, int silaPokemona, int level) {
        this.jmenoPokemona = jmenoPokemona;
        this.intTypPokemona = intTypPokemona;
        this.silaPokemona = silaPokemona;
        this.level = level;

        typPokemona[0] = "Normální";
        typPokemona[1] = "Lesní";
        typPokemona[2] = "Kamenný";
        typPokemona[3] = "Elektrický";
        typPokemona[4] = "Vodní";
        typPokemona[5] = "Ohnivý";
        typPokemona[6] = "Legendární";

    }

    /**
     * Metoda vrací typ pokémona podle array typPokémona
     * */
    public String getStringTypPokemona() {
        return typPokemona[intTypPokemona];
    }

    /**
     * Metoda vrací int typ pokémona
     * */
    public int getIntTypPokemona() {
        return intTypPokemona;
    }

    /**
     * Metoda vrací stringové jméno pokémona
     * */
    public String getJmenoPokemona() {
        return jmenoPokemona;
    }

    /**
     * Metoda vrací int sílu pokémona
     * */
    public int getSilaPokemona() {
        return silaPokemona;
    }

    /**
     * Metoda vrací pokémona podle parametru pokemon
     * */
    public Pokemon getPokemon(Pokemon pokemon) {
        return pokemon;
    }

    /**
     * Metoda vrací int level pokémona
     * */
    public int getLevel() {
        return level;
    }

    /**
     * Metoda nastavuje sílu pokémona podle parametru silaPokemona
     * */
    public void setSilaPokemona(int silaPokemona) {
        this.silaPokemona = silaPokemona;
    }

    /**
     * Metoda string soupis některých vlastností pokémona.
     * */
    @Override
    public String toString() {
        return jmenoPokemona + "Má " + getLevel() + " Level " + " Je:" + getStringTypPokemona() + " Má sílu:" + silaPokemona;
    }
}
