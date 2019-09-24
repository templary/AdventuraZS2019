package cz.vse.java.prom20.adventura.logika;

/**
 * Třída PrikazTrenuj
 * Implementuje trénování pokémonů.
 *
 * @author Miroslav Prokop
 * @version 1.0
 */
class PrikazTrenuj implements IPrikaz {
    private static final String NAZEV = "trénuj";
    private Pokemoni pokemoni;
    private Batoh batoh;

    /**
     * Konstruktor třídy
     *
     * @param batoh     instance třídy Batoh
     * @param pokemoni  instance třídy Pokemoni
     */
    public PrikazTrenuj(Batoh batoh, Pokemoni pokemoni) {
        this.batoh = batoh;
        this.pokemoni = pokemoni;
    }

    /**
     * Vrátí String, pokud je splněco vše co má být, trénuje pokémona.
     *
     * @param parametry - jméno pokémona k trénování
     *                  kontroluje jestli je parametr validní pro kód.
     * @return textový return stavu, co se v kódu stalo.
     */
    @Override
    public String provedPrikaz(String... parametry) {


        if (parametry.length == 0) {
            return "Musíš zadat, jakého pokemona chceš trénovat.\n Máš u sebe: " + pokemoni.getSeznamChycenychPokemonuJakoText();
        }

        String parametr = parametry[0];


        Pokemon p = pokemoni.getPokemonPokudTuJe(parametr);

        if (p == null) {
            return "Pokémona: " + parametr + " nemáš.";
        }

        if (batoh.getNazvyVeci().contains("elixírS")) {
            batoh.odeberVec(batoh.getVecPokudTuJe("elixírS"));
            return pokemoni.trenujPokemona(p, 4);

        }
        if (batoh.getNazvyVeci().contains("elixírM")) {
            batoh.odeberVec(batoh.getVecPokudTuJe("elixírM"));
            return pokemoni.trenujPokemona(p, 5);

        }
        if (batoh.getNazvyVeci().contains("elixírL")) {
            batoh.odeberVec(batoh.getVecPokudTuJe("elixírL"));
            return pokemoni.trenujPokemona(p, 6);

        }
        return "Bez kouzenlého elixíru nemůžeš trénovat.";


    }


    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     * @return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
