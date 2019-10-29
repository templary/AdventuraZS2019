package cz.vse.java.prom20.adventura.logika;


import java.util.concurrent.ThreadLocalRandom;

/**
 * /**
 * Třída PrikazFinal implementuje souboje v průběhu hry.
 * třída se využívá ve všech místnostech krom 1,2 a final arény.
 *
 * @author Miroslav Prokop
 * @version 1.0
 */
class PrikazBojujProGUI implements IPrikaz {
    private static final String NAZEV = "bojujgui";
    private HerniPlan herniPlan;
    private Pokemoni pokemoni;
    private Hra hra;


    /**
     * Konstruktor třídy
     *
     * @param herniPlan herní plán
     * @param hra       instance třídy Hra
     * @param pokemoni  instance třídy Pokemoni
     */
    PrikazBojujProGUI(HerniPlan herniPlan, Pokemoni pokemoni, Hra hra) {
        this.herniPlan = herniPlan;
        this.pokemoni = pokemoni;
        this.hra = hra;
    }


    /**
     * @param parametry - jmeno_pokemona_na_souboj
     *                  Kód zjistí, zda je hráč v místnosti kde je dovolené dělat souboje.
     *                  po zadání parametru kód ověří validitu vstupu.
     *                  Po ověření validity vstupu se dostáváme k volbě pokémona s kterým hráč chce bojovat.
     *                  Kód provede kontrolu validity druhého vstupu ze Scanneru
     *                  Program přečne bojové info o obou pokémonech a zeptá se, zda chce hráč pokračovat.
     *                  Souboj se vyhodnocuje pomocí porovnání statistik pokémonu a násobením jich s náhodným číslem (1-6)
     * @return hráč získá pokéma pokud ho porazí dle daného výpočtu o řádek výše. (> or ==)
     */
    @Override
    public String provedPrikaz(String... parametry) {


        if (pokemoni.getSetChycenychPokemonu().isEmpty()) {
            hra.setKonecHry(true);
            return "Zaútočil jsi bez pokémona a to tě stálo život. Prohrál jsi.";

        }

        String parametr = parametry[0];


        Pokemon p = herniPlan.getAktualniMistnost().getPokemonPokudTuJe(parametr);
        String bojovnik = parametry[1];
        if (p == null) {
            return parametr + " tu není";
        } else {
            if (herniPlan.getAktualniMistnost().getUrovenHernihoPlanu() == 2) {
                return "Tady bojovat nemusíš, pouzij příkaz chytni jmeno_pokemona";
            } else {
                if (herniPlan.getAktualniMistnost().getSeznamPokemonuJakoText() != null) {

                }
            }
        }
        Pokemon souper = herniPlan.getAktualniMistnost().getPokemonPokudTuJe(parametr);
        Pokemon pokemonHrace = pokemoni.getPokemonPokudTuJe(bojovnik);

        System.out.println("Tvůj pokémon: " + pokemonHrace.toString());
        System.out.println("--------");
        System.out.println("Protivník: " + souper.toString());
        System.out.println("--------");

        int pokusy = 3;

        while (pokusy > 0) {


            int randomNum = ThreadLocalRandom.current().nextInt(1, 6);
            int randomNum2 = ThreadLocalRandom.current().nextInt(1, 6);

            System.out.println("Hodil jsi: " + randomNum + "\nPokémon " + souper.getJmenoPokemona() + " Hodil: " + randomNum2);
            System.out.println("--------");

            int souperStats = souper.getLevel() * souper.getSilaPokemona() * randomNum2;
            int pokemonHraceStats = pokemonHrace.getLevel() * pokemonHrace.getSilaPokemona() * randomNum;

            if (souperStats < pokemonHraceStats || souperStats == pokemonHraceStats) {
                herniPlan.getAktualniMistnost().odeberPokemona(souper);
                pokemoni.chytPokemona(souper);
                return "Tvůj pokémon " + pokemonHrace.getJmenoPokemona() + " Vyhrál.\nNyní je " + souper.getJmenoPokemona() + " tvůj.";
            } else {
                System.out.println("Bohuže jsi prohrál, máš ještě " + pokusy + " pokusy než ti pokémon uteče.");
                pokusy--;
                if (pokusy == 0) {
                    herniPlan.getAktualniMistnost().odeberPokemona(souper);
                    return "Pokémon " + souper.getJmenoPokemona() + " ti bohužel utekl.";
                }
            }
        }
        return "Konec souboje.";
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