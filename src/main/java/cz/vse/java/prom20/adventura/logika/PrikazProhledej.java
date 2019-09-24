package cz.vse.java.prom20.adventura.logika;

/**
 * Třída PrikazProhledej
 * Implementuje prohledávíní
 * 1) Pokémonů v místnosti
 * 2) Pokémonů v pokébalu
 * 3) Věcí v místnosti
 * 4) Věcí v batohu
 *
 * @author Miroslav Prokop
 * @version 1.0
 */
class PrikazProhledej implements IPrikaz {
    private static final String NAZEV = "prohledej";
    private HerniPlan herniPlan;
    private Pokemoni pokemoni;
    private Batoh batoh;


    /**
     * Konstruktor třídy
     *
     * @param herniPlan herní plán
     * @param batoh     instance třídy Batoh
     * @param pokemoni  instance třídy Pokemoni
     */
    public PrikazProhledej(HerniPlan herniPlan, Batoh batoh, Pokemoni pokemoni) {
        this.herniPlan = herniPlan;
        this.batoh = batoh;
        this.pokemoni = pokemoni;
    }


    /**
     * @param parametry - co má program prohledávat.
     *                  kontroluje jestli je parametr validní pro kód.
     * @return textový return výsledku běhu kódu, pokud uživatel zadá vše jak má, vypíše se mu obsah věcí.
     */
    @Override
    public String provedPrikaz(String... parametry) {

        if (parametry.length == 0) {
            return "Musíš zadat co chceš hledat.\nPro pokémony v místnosti - prohledej pokémony\nPro věci v místnosti - prohledej věci\nPro batoh - prohledej batoh\nPro pokémony v pokébalu - prohledej Pokébal";
        }

        String parametr = parametry[0];


        if (parametr.equals("pokémony")) {
            return "V této místnosti jsou pokemoni: " + herniPlan.getAktualniMistnost().getSeznamPokemonuJakoText();
        }

        if (parametr.equals("věci")) {
            return "V této místnosti jsou: " + herniPlan.getAktualniMistnost().getSeznamVeciJakoText();
        }

        if (parametr.equals("batoh")) {
            return batoh.getSeznamVeciJakoText();
        }

        if (parametr.equals("pokébal")) {
            return "V pokébalu máš: " + pokemoni.getSeznamChycenychPokemonuJakoText();
        }

        return "Zadal jsi nesprávný výraz pro hledání. Možnosti:\n - prohledej pokemony \n - prohledej věci\n - prohledej batoh\n - prohledej pokébal";


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
