package cz.vse.java.prom20.adventura.logika;

/**
 * Třída Vec
 * Implementuje instanci třídy Vec.
 *
 * @author Miroslav Prokop
 * @version 1.0
 */
public class Vec {

    private String nazevVeci;
    private boolean sebratelna;
    private String popis;

    /**
     * Vytvoří novou instanci třídy Vec
     *
     * @param nazevVeci  název věci
     * @param popis      popis věci, který se může zobrazovat
     * @param sebratelna přenositelnost věci
     */
    public Vec(String nazevVeci, boolean sebratelna, String popis) {
        this.nazevVeci = nazevVeci;
        this.sebratelna = sebratelna;
        this.popis = popis;
    }

    /**
     * Metoda vrátí název věci
     *
     * @return název věci
     */
    public String getNazevVeci() {
        return nazevVeci;
    }

    /**
     * Metoda vrátí zda je věc sebratelná
     *
     * @return ano/ne
     */
    public boolean isSebratelna() {
        return sebratelna;
    }

    /**
     * Metoda vrátí popis věci
     *
     * @return popis věci
     */
    public String getPopis() {
        return popis;
    }


}