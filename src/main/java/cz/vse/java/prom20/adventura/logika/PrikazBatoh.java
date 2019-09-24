package cz.vse.java.prom20.adventura.logika;


/**
 * Třída PrikazFinal implementuje příkaz batoh.
 * třída se využívá pro práci s věcmi v celé hře.
 *
 * @author Miroslav Prokop
 * @version 1.0
 */
public class PrikazBatoh implements IPrikaz {
    private static final String NAZEV = "batoh";

    private Batoh batoh;
    private HerniPlan herniPlan;

    /**
     * Konstruktor třídy
     *
     * @param herniPlan herní plán
     * @param batoh     instance třídy Batoh
     */
    public PrikazBatoh(Batoh batoh, HerniPlan herniPlan) {
        this.batoh = batoh;
        this.herniPlan = herniPlan;
    }

    /**
     * @param parametry - seber/vyhod + co
     *                  Kód zjistí, zda hráč validně zadal oba parametry.
     *                  zjistí, zda věc má v batohu nebo jestli je v místnosti.
     *                  pokud zadal vyhod - věc se odebere z batohu a vloží do místnoti.
     *                  pokud zadal seber - věc se z místnoti přesune do batohu.
     *                  - kontroluje zda jde sebrat.
     * @return String řetšzec o průběhu kódu.
     */
    @Override
    public String provedPrikaz(String... parametry) {

        if (parametry.length < 2) {
            return "Můžeš použít:\n -batoh seber + co\n -batoh vyhod + co ";
        }

        Vec parametr2Seber = herniPlan.getAktualniMistnost().getVecPokudTuJe(parametry[1]);
        Vec parametr2Vyhod = batoh.getVecPokudTuJe(parametry[1]);


        if (parametry[0].equals("vyhod")) {
            if (batoh.getNazvyVeci().contains(parametry[1])) {
                herniPlan.getAktualniMistnost().vlozVec(parametr2Vyhod);
                return batoh.odeberVec(parametr2Vyhod);
            }
            return "věc: " + parametry[1] + " není v batohu";
        }

        String parametr0 = parametry[0];
        if (parametr0.equals("seber")) {
            if (herniPlan.getAktualniMistnost().getNazvyVeci().contains(parametry[1])) {
                if (batoh.getObsahBatohu().size() + 1 <= Batoh.OBJEM_BATOHU) {
                    if (herniPlan.getAktualniMistnost().jeSebratelna(parametr2Seber)) {

                        herniPlan.getAktualniMistnost().odeberVec(parametr2Seber);

                        return batoh.pridejVec(parametr2Seber);
                    }
                    return "Nelze sebrat.";
                }
                return parametr2Seber.getNazevVeci() + " se dovnitř už bohužel nevejde, uvolni si v batohu místo.";
            }
            return "V této místnosti věc: " + parametry[1] + " není";
        }
        return "Špatně jsi zadal parametr.";
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