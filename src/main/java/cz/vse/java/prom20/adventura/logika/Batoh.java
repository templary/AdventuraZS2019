package cz.vse.java.prom20.adventura.logika;

/**
 * Třída Batoh
 * Zpracovává logiku chování batohu.
 *
 * @author Miroslav Prokop
 * @version 1.0
 */

//test
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Batoh {

    public static final int OBJEM_BATOHU = 6; //zadaný maximální objem batohu

    private Set<Vec> obsahBatohu;

    public Batoh() {
        this.obsahBatohu = new HashSet<>();
    }

    /**
     *metoda pridá věc podle parametru v do batohu.
     *
     */
    public String pridejVec(Vec v) {
        this.obsahBatohu.add(v);
        return "Uspěšně jsi pridal " + v.getNazevVeci() + " do batohu.";
    }

    /**
     *metoda vrátí obsah batohu. - set
     *
     */
    public Set getObsahBatohu() {
        return obsahBatohu;
    }

    /**
     *metoda vrátí set názvů věcí.
     *
     */
    public Set<String> getNazvyVeci() {
        return this.obsahBatohu.stream()
                .map(vec -> vec.getNazevVeci())
                .collect(Collectors.toSet());
    }

    /**
     *metoda odebere věc podle parametru v
     * vrací string potvrzení/odmítnutí.
     *
     */
    public String odeberVec(Vec v) {
        if (this.obsahBatohu.remove(v)) {
            return "Vec: " + v.getNazevVeci() + " byla uspesne odebrána";
        }

        return "Tuto věc v batohu nemáš.";
    }

    /**
     *metoda vrací název věci podle parametru v jako string
     *
     */
    public String getNazevVeciJakoText(Vec v) {
        return v.getNazevVeci();
    }

    /**
     *metoda vrací string soupis  všech věcí v batohu.
     *
     */
    public String getSeznamVeciJakoText() {
        if (this.obsahBatohu.size() > 0) {
            String s = "Batoh má " + getVolneMisto() + "/" + Batoh.OBJEM_BATOHU + " míst volných, je v něm: ";
            for (Vec v : this.obsahBatohu) {
                s = s + v.getNazevVeci() + " ";
            }
            return s;
        } else {
            return "V batohu nejsou zadne veci.";
        }
    }


    /**
     *metoda vrací věc podle parametru nazevVeci pokud je v batohu.
     *
     */
    public Vec getVec(String nazevVeci) {
        boolean mamOdebrat = false;
        for (Vec v : this.obsahBatohu) {

            if (v.getNazevVeci().equals(nazevVeci)) {
                mamOdebrat = true;
                return v;
            }
        }

        return null;
    }

    /**
     *metoda vrací int objem batohu.
     *
     */
    public int getObjemBatohu() {
        return OBJEM_BATOHU;
    }

    /**
     *metoda zjistí, zda je batoh prázdný T/F
     *
     */
    public boolean jeBatohPrazdny() {
        if (obsahBatohu.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     *metoda zjistí zda je batoh plný T/F
     *
     */
    public boolean jeBatohPlny() {
        if (obsahBatohu.size() <= Batoh.OBJEM_BATOHU) {
            return true;
        }
        return false;
    }

    /**
     *metoda vypočítá volné místno v batohu
     *
     */
    public int getVolneMisto() {
        return Batoh.OBJEM_BATOHU - obsahBatohu.size();
    }

    /**
     *metoda vrátí věc podle parametru nazevVeci pokud se v batohu nachází.
     *
     */
    public Vec getVecPokudTuJe(String nazevVeci) {
        for (Vec vec : this.obsahBatohu) {
            if (vec.getNazevVeci().equals(nazevVeci)) {
                return vec;
            }
        }
        return null;
    }
}