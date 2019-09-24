package cz.vse.java.prom20.adventura.logika;

/**
 * Třída PrikazChytni implementuje mechaniku chytání pokémonů.
 * třída se využívá v první místnosti s pokémony -> dává pokémona zadarmo, bez souboje.
 *
 * @author Miroslav Prokop
 * @version 1.0
 */
class PrikazChytni implements IPrikaz {
    private static final String NAZEV = "chytni";
    private HerniPlan herniPlan;
    private Pokemoni pokemoni;
    private Boolean mohuChytat = true;

    /**
     * Konstruktor třídy
     *
     * @param herniPlan herní plán
     * @param pokemoni  instance třídy Pokemoni
     */
    public PrikazChytni(HerniPlan herniPlan, Pokemoni pokemoni) {
        this.herniPlan = herniPlan;
        this.pokemoni = pokemoni;
    }


    /**
     * @param parametry jméno_pokémona kterého chce hráč chytat.
     *                  Kód zjistí, zda hráč zadal dobře parametr.
     *                  validním paramtrem vytvoří pokémona p
     *                  zjistí, zda již hráč z této místnosti nedostal již nějakého pokémana, pokud ne volá chytPokemona()
     *                  po chytutí naství mohuChytat na false, tím zajistí to, aby další pokémon již nešel získat.
     *                  přidá pokémona do pokébalu a odebere ho z místnosti.
     * @return String o průběhu kódu.
     */
    @Override
    public String provedPrikaz(String... parametry) {

        if (herniPlan.getAktualniMistnost().getUrovenHernihoPlanu() !=2){
            return "chytat pomocí tohoto příkazu můžeš jen ve škole.";

        }

        if (parametry.length == 0) {
            return "Musíš zadat, jakého pokemona chceš chytat.\n V této místnosti jsou: " + herniPlan.getAktualniMistnost().getSeznamPokemonuJakoText();
        }



        String parametr = parametry[0];


        Pokemon p = herniPlan.getAktualniMistnost().getPokemonPokudTuJe(parametr);

        if (p == null) {
            return parametr + " tu není";
        } else {
            if (herniPlan.getAktualniMistnost().getUrovenHernihoPlanu() == 2 && mohuChytat) {


                herniPlan.getAktualniMistnost().odeberPokemona(p);
                mohuChytat = false;
                return pokemoni.chytPokemona(p);

            } else {
                return "Vybrat si můžeš pouze jednoho pokémona.";
            }
        }
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
