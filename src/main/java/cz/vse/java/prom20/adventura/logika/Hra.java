package cz.vse.java.prom20.adventura.logika;

/**
 * Třída Hra - třída představující logiku adventury.
 * <p>
 * Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 * a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 * Vypisuje uvítací a ukončovací text hry.
 * Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 * @author Jarmila Pavlickova, Miroslav Prokop
 * @version 1.0
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;
    private Batoh batoh;
    private Pokemon pokemon;
    private Pokemoni pokemoni;
    private Vec vec;
    //private KomunikaceControlleru komunikaceControlleru;

    /**
     * Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan();
        //komunikaceControlleru = new KomunikaceControlleru();
        platnePrikazy = new SeznamPrikazu();

        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));


        batoh = new Batoh();
        //TODO ------------------------------ Testovací entity.

        Vec elixirS = new Vec("elixírS", true, "Pomocí tohoto elixíru můžeš trénovat pokémona.(+4 síly.)");
        batoh.pridejVec(elixirS);

        //TODO ------------------------------
        pokemoni = new Pokemoni();


        platnePrikazy.vlozPrikaz(new PrikazProhledej(this.herniPlan, this.batoh, this.pokemoni));
        platnePrikazy.vlozPrikaz(new PrikazBatoh(this.batoh, this.herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazChytni(this.herniPlan, this.pokemoni));
        platnePrikazy.vlozPrikaz(new PrikazBojuj(this.herniPlan, this.pokemoni, this));
        platnePrikazy.vlozPrikaz(new PrikazTrenuj(this.batoh, this.pokemoni));
        platnePrikazy.vlozPrikaz(new PrikazInfo(this.herniPlan, this.pokemoni));
        platnePrikazy.vlozPrikaz(new PrikazFinal(this.herniPlan, this.pokemoni, this));


    }

    /**
     * Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "Vítejte!\n" +
                "Právě jsi vstoupil do úžasného světa pokémonů\n" + "Tvůj úkol je tak jednoduchý a přitom velice složitý. Chyť je všechny!\n" +
                "Napište 'nápověda', pokud si nevíte rady, jak hrát dál.\n" +
                "\n" +
                herniPlan.getAktualniMistnost().dlouhyPopis();
    }

    /**
     * Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return "Díky, že jste si zahráli. Ahoj.";
    }

    /**
     * Vrací true, pokud hra skončila.
     */
    public boolean konecHry() {
        return konecHry;
    }

    /**
     * Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     * Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     * Pokud ano spustí samotné provádění příkazu.
     *
     * @param radek text, který zadal uživatel jako příkaz do hry.
     * @return vrací se řetězec, který se má vypsat na obrazovku
     */
    public String zpracujPrikaz(String radek) {
        String[] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String[] parametry = new String[slova.length - 1];
        System.arraycopy(slova, 1, parametry, 0, parametry.length);
        String textKVypsani = " .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.provedPrikaz(parametry);
        } else {
            textKVypsani = "Nevím co tím myslíš? Tento příkaz neznám. ";
        }
        return textKVypsani;
    }

    public boolean getKonecHry() {
        return konecHry;
    }


    /**
     * Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     * mohou ji použít i další implementace rozhraní Prikaz.
     *
     * @param konecHry hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }

    /**
     * Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     * kde se jejím prostřednictvím získává aktualní místnost hry.
     *
     * @return odkaz na herní plán
     */


    public HerniPlan getHerniPlan() {
        return herniPlan;
    }

    /**
     * @return vrací pokémona
     */
    public Pokemon getPokemon() {
        return pokemon;
    }

    /**
     * @return vrací pokémony
     */
    public Pokemoni getPokemoni() {
        return pokemoni;
    }

    /**
     * @return vrací batoh
     */
    public Batoh getBatoh() {
        return batoh;
    }

/*    public KomunikaceControlleru getKomunikaceControlleru() {
        return komunikaceControlleru;
    }*/
}

