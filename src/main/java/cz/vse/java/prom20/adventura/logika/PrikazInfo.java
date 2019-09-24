package cz.vse.java.prom20.adventura.logika;

/**
 * Třída PrikazInfo implementuje pro hru příkaz info.
 * Účel této třídy je předat hráci informace o pokémonech aby se mohl rozhodout, s kým bojovat.
 *
 * @author Miroslav Prokop
 * @version 1.0
 */
class PrikazInfo implements IPrikaz {
    private static final String NAZEV = "info";
    private HerniPlan herniPlan;
    private Pokemoni pokemoni;

    /**
     * Konstruktor třídy
     *
     * @param herniPlan herní plán
     * @param pokemoni  instance třídy Pokemoni
     */
    public PrikazInfo(HerniPlan herniPlan,  Pokemoni pokemoni) {
        this.herniPlan = herniPlan;
        this.pokemoni = pokemoni;
    }


    /**
     * @param parametry - o jakém pokémonu chceme zjistit informace.
     *                  kontroluje jestli je parametr validní pro kód.
     * @return textový return výsledku běhu kódu, pokud uživatel zadá vše jak má, vypíší se mu informace o pokémonovi.
     */
    @Override
    public String provedPrikaz(String... parametry) {


        //kontrola parametru
        if (parametry.length == 0) {
            return "Musíš zadat, o jakém pokémonovi chceš bojové informace.\n Máš u sebe: " + pokemoni.getSeznamChycenychPokemonuJakoText() +
                    "\nOkolo tebe jsou: " + herniPlan.getAktualniMistnost().getSeznamPokemonuJakoText();
        }
        //validní parametr předán do stringu
        String parametr = parametry[0];

        //dle parametru předání do Pokemon
        Pokemon pokemonVPokebalu = pokemoni.getPokemonPokudTuJe(parametr);
        Pokemon pokemonVMistnost = herniPlan.getAktualniMistnost().getPokemonPokudTuJe(parametr);


        //vyhodnocení
        if (pokemonVMistnost == null && pokemonVPokebalu == null) {
            return "Pokémon: " + parametr + "nikde není.";
        }

        if (pokemonVMistnost != null) {
            return "Pokémon: " + pokemonVMistnost.getJmenoPokemona() + " Má sílu: " + pokemonVMistnost.getSilaPokemona() + " a level: " + pokemonVMistnost.getLevel();
        }

        if (pokemonVPokebalu != null) {
            return "Pokémon: " + pokemonVPokebalu.getJmenoPokemona() + " Má sílu: " + pokemonVPokebalu.getSilaPokemona() + " a level: " + pokemonVPokebalu.getLevel();
        }

        return "null";
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
