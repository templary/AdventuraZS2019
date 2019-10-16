
package cz.vse.java.prom20.adventura;

import cz.vse.java.prom20.adventura.logika.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


/*******************************************************************************
 * Testovací třída SeznamPrikazuTest slouží ke komplexnímu otestování třídy
 * SeznamPrikazu
 *
 * @author Miroslav Prokop
 * @version 1.0
 */

public class SeznamPrikazuTest {
    private HerniPlan herniPlan;
    private Pokemon pokemon;
    private Pokemoni pokemoni;
    private Batoh batoh;
    private Vec vec;

    private PrikazKonec prKonec;
    private PrikazJdi prJdi;


    @Before
    public void setUp() {
        Hra hra = new Hra();
        prKonec = new PrikazKonec(hra);
        prJdi = new PrikazJdi(hra.getHerniPlan());

    }

    @Test
    public void testVlozeniVybrani() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        assertEquals(prKonec, seznPrikazu.vratPrikaz("konec"));
        assertEquals(prJdi, seznPrikazu.vratPrikaz("jdi"));
        assertNull(seznPrikazu.vratPrikaz("nápověda"));
        assertEquals(null, seznPrikazu.vratPrikaz("prohledej"));


    }

    @Test
    public void testJePlatnyPrikaz() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("konec"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("jdi"));
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("nápověda"));
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("Konec"));
    }

    @Test
    public void testNazvyPrikazu() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        String nazvy = seznPrikazu.vratNazvyPrikazu();
        assertEquals(true, nazvy.contains("konec"));
        assertEquals(true, nazvy.contains("jdi"));
        assertEquals(false, nazvy.contains("nápověda"));
        assertEquals(false, nazvy.contains("Konec"));
    }

}

