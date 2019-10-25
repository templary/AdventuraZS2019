package cz.vse.java.prom20.adventura.logika;

public class UkladaniHry {
    private Hra hra;

    public UkladaniHry(Hra hra) {
        this.hra = hra;
    }

    public void ulozHru() {
        hra.getHerniPlan().getAktualniMistnost();
        hra.getPokemoni().getSetChycenychPokemonu();
        hra.getBatoh().getObsahBatohu();

    }


}
