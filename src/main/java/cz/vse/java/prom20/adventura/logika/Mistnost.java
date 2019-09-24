package cz.vse.java.prom20.adventura.logika;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Trida Mistnost - popisuje jednotlivé prostory (místnosti) hry
 * <p>
 * Tato třída je součástí jednoduché textové hry.
 * <p>
 * "Mistnost" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Mistnost může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Miroslav Prokop
 * @version 1.0
 */
public class Mistnost {


    private String nazevMistnosti;
    private String popisMistnosti;
    private Set<Mistnost> vychody;
    private int urovenHernihoPlanu;
    private int ciselneOznaceniMistnosti;
    private Set<Vec> seznamVeci;
    private Set<Pokemon> seznamPokemonu;
    // obsahuje sousední místnosti

    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazevMistnosti nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     *                       víceslovný název bez mezer.
     * @param popisMistnosti Popis prostoru.
     */
    public Mistnost(String nazevMistnosti, String popisMistnosti, int urovenHernihoPlanu, int ciselneOznaceniMistnosti) {
        this.nazevMistnosti = nazevMistnosti;
        this.popisMistnosti = popisMistnosti;
        this.urovenHernihoPlanu = urovenHernihoPlanu;
        this.ciselneOznaceniMistnosti = ciselneOznaceniMistnosti;


        vychody = new HashSet<>();

        this.seznamPokemonu = new HashSet<>();
        this.seznamVeci = new HashSet<>();
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     */

    /**
     * Metoda nastaveje východy podle parametru vedlejší
     * */
    public void setVychod(Mistnost vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda vkládá pokémona podle parametru pokémon - do setu pokémonu
     * */
    public void vlozPokemona(Pokemon pokemon) {
        seznamPokemonu.add(pokemon);
    }

    /**
     * Metoda vkládá věc do setu seznam věcí podle parametru vec
     * */
    public void vlozVec(Vec vec) {
        seznamVeci.add(vec);
    }

    /**
     * Metoda odebírá věc z setu věci podle parametru vec
     * */
    public void odeberVec(Vec vec) {
        seznamVeci.remove(vec);
    }

    /**
     * Metoda odebírá pokémona ze setu pokémonu podle parametru pokemon
     * */
    public void odeberPokemona(Pokemon pokemon) {
        seznamPokemonu.remove(pokemon);
    }

    /**
     * Metoda vrací úroveň herního plánu
     * */
    public int getUrovenHernihoPlanu() {
        return urovenHernihoPlanu;
    }

    /**
     * Metoda vrací set věcí
     * */
    public Set<Vec> getSetVeci() {
        return seznamVeci;
    }

    /**
     * Metoda vrací set pokémonů
     * */
    public Set<Pokemon> getSetPokemonu() {
        return seznamPokemonu;
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     * <p>
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */
    @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Mistnost)) {
            return false;    // pokud parametr není typu Mistnost, vrátíme false
        }
        // přetypujeme parametr na typ Mistnost
        Mistnost druhy = (Mistnost) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

        return (Objects.equals(this.nazevMistnosti, druhy.nazevMistnosti));
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazevMistnosti);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }


    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazevMistnosti;
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "Jsi v: " + nazevMistnosti + " " + popisMistnosti + ".\n"
                + popisVychodu();
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        String vracenyText = "východy:";
        for (Mistnost sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
        }
        return vracenyText;
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Mistnost, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Mistnost getSousedniProstor(String nazevSouseda) {
        List<Mistnost> hledaneProstory =
                vychody.stream()
                        .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
                        .collect(Collectors.toList());
        if (hledaneProstory.isEmpty()) {
            return null;
        } else {
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Mistnost.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Mistnost> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }

    public boolean jeSebratelna(Vec vec) {
        if (vec.isSebratelna()) {
            return true;
        }
        return false;
    }


    /**
     * Metoda nastavuje východy
     * */
    public void setVychody(Mistnost vedlejsiMistnost) {
        vychody.add(vedlejsiMistnost);
    }

    public int getCiselneOznaceniMistnosti() {
        return ciselneOznaceniMistnosti;
    }

    /**
     * Metoda vrací seznam věcí jako text
     * */
    public String getSeznamVeciJakoText() {
        if (this.seznamVeci.size() > 0) {
            String s = "";
            for (Vec v : this.seznamVeci) {
                s = s + v.getNazevVeci() + " ";
            }
            return s;
        } else {
            return "V teto mistnosti nejsou zadne veci.";
        }
    }


    /**
     * Metoda zjistuje jestli jsou v místnosti nějaké věci
     * */
    public boolean jeMistnostBezVeci() {
        if (this.seznamVeci == null) {
            return true;
        }
        return false;
    }

    /**
     * Metoda vrací seznam pokémonů jako text
     * */
    public String getSeznamPokemonuJakoText() {
        if (this.seznamPokemonu.size() > 0) {
            String s = "";
            for (Pokemon p : this.seznamPokemonu) {
                s = s + p.getJmenoPokemona() + " ";
            }
            return s;
        } else {
            return "V teto mistnosti nejsou žádní pokémoni.";
        }
    }

    /**
     * Metoda vrací názvy věci v setu seznam věcí
     * */
    public Set<String> getNazvyVeci() {
        return this.seznamVeci.stream()
                .map(vec -> vec.getNazevVeci())
                .collect(Collectors.toSet());
    }



    /**
     * Metoda vrací seznam východu jako text duplikace, kvůli testu.
     * */
    public String getSeznamVychoduJakoText() {
        String s = "";
        for (Mistnost m : this.vychody) {
            s = s + m.getNazev() + " ";
        }
        return s;

    }

    /**
     * Metoda vrací věc pokud zde je, null pokud ne.
     * */
    public Vec getVecPokudTuJe(String nazevVeci) {
        for (Vec vec : this.seznamVeci) {
            if (vec.getNazevVeci().equals(nazevVeci)) {
                return vec;
            }
        }
        return null;
    }

    /**
     * Metoda vrací pokemona pokud je, null pokud ne.
     * */
    public Pokemon getPokemonPokudTuJe(String nazevPokemona) {
        for (Pokemon pokemon : this.seznamPokemonu) {
            if (pokemon.getJmenoPokemona().equals(nazevPokemona)) {
                return pokemon;
            }
        }
        return null;
    }


}
