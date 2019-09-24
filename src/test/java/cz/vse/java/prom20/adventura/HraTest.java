package cz.vse.java.prom20.adventura;

import cz.vse.java.prom20.adventura.logika.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author Jarmila Pavlíčková, Miroslav Prokop
 * @version 1.0
 */
public class HraTest {
    private Hra hra1;

    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Test kontroluje, zda je možné projít všechny místnosti a dostat se až do Final arény.
     *
     */
    @Test
    public void testPrucodHrou() {
        assertEquals("start", hra1.getHerniPlan().getAktualniMistnost().getNazev());
        hra1.zpracujPrikaz("jdi škola");
        hra1.zpracujPrikaz("chytni Pikaču");
        assertEquals("škola", hra1.getHerniPlan().getAktualniMistnost().getNazev());
        assertEquals(1, hra1.getPokemoni().getSetChycenychPokemonu().size());

        hra1.zpracujPrikaz("jdi chytání");
        assertEquals("chytání", hra1.getHerniPlan().getAktualniMistnost().getNazev());

        hra1.zpracujPrikaz("jdi les");
        assertEquals("les", hra1.getHerniPlan().getAktualniMistnost().getNazev());

        hra1.zpracujPrikaz("jdi louka");
        assertEquals("louka", hra1.getHerniPlan().getAktualniMistnost().getNazev());

        hra1.zpracujPrikaz("jdi les");
        assertEquals("les", hra1.getHerniPlan().getAktualniMistnost().getNazev());

        hra1.zpracujPrikaz("jdi aréna1");
        assertEquals("aréna1", hra1.getHerniPlan().getAktualniMistnost().getNazev());

        hra1.zpracujPrikaz("jdi Seladonové");
        assertEquals("Seladonové", hra1.getHerniPlan().getAktualniMistnost().getNazev());

        hra1.zpracujPrikaz("jdi Chromové");
        assertEquals("Chromové", hra1.getHerniPlan().getAktualniMistnost().getNazev());

        hra1.zpracujPrikaz("jdi Oblázkové");
        assertEquals("Oblázkové", hra1.getHerniPlan().getAktualniMistnost().getNazev());

        hra1.zpracujPrikaz("jdi aréna2");
        assertEquals("aréna2", hra1.getHerniPlan().getAktualniMistnost().getNazev());

        hra1.zpracujPrikaz("jdi krveprolití");
        assertEquals("krveprolití", hra1.getHerniPlan().getAktualniMistnost().getNazev());

        hra1.zpracujPrikaz("jdi boss");
        assertEquals("boss", hra1.getHerniPlan().getAktualniMistnost().getNazev());


    }

    @Test
    public void testVlozVecDoMistnosti() {
        Vec test1 = new Vec("test", true, "t1esttest");
        Vec test2 = new Vec("test2", true, "testtest");
        Pokemon ptest1 = new Pokemon("TestovacíP", 1, 1, 1);

        // od začátku jsou v místnosti 3 věci
        assertEquals(3, hra1.getHerniPlan().getAktualniMistnost().getSetVeci().size());

        hra1.getHerniPlan().getAktualniMistnost().vlozVec(test1);
        assertEquals(4, hra1.getHerniPlan().getAktualniMistnost().getSetVeci().size());

        hra1.getHerniPlan().getAktualniMistnost().vlozVec(test2);
        assertEquals(5, hra1.getHerniPlan().getAktualniMistnost().getSetVeci().size());
    }

    /**
     * test kontroluje, zda hra skončí pokud hráč zaútočí na pokemona bez pokemonu.*/
    @Test
    public void testNastaneKonec() {
        hra1.zpracujPrikaz("jdi škola");
        hra1.zpracujPrikaz("jdi chytání");
        hra1.zpracujPrikaz("bojuj Pikaču");
        assertEquals(true, hra1.getKonecHry());
    }


    /**
     * V místnosti máme 3 věci
     * pokeball
     * koberec
     * kompas
     *
     * Test zkouší, jestli jdou sebrat do batohu
     * a jestli zmizí z herního prostoru po vložení do batohu.
     * */
    @Test
    public void testSeberVec() {
        assertEquals(3, hra1.getHerniPlan().getAktualniMistnost().getSetVeci().size());
        hra1.zpracujPrikaz("batoh seber Pokébal");
        assertEquals(2, hra1.getHerniPlan().getAktualniMistnost().getSetVeci().size());
        assertEquals(1, hra1.getBatoh().getObsahBatohu().size());
        hra1.zpracujPrikaz("batoh seber Kompas");
        assertEquals(1, hra1.getHerniPlan().getAktualniMistnost().getSetVeci().size());
        assertEquals(2, hra1.getBatoh().getObsahBatohu().size());
    }

    /**
     * Test kontroluje zda nelze sebrat nepřenositelnou věc*/
    @Test
    public void testNeprenositelnost(){
        assertEquals(3, hra1.getHerniPlan().getAktualniMistnost().getSetVeci().size());
        hra1.zpracujPrikaz("batoh seber koberec");
        assertEquals(3,hra1.getHerniPlan().getAktualniMistnost().getSetVeci().size());
        assertEquals(0,hra1.getBatoh().getObsahBatohu().size());
    }
    /**
     * Test kontroluje, zda není možné batoh přeplnit. */
    @Test
    public void testPreplnitelnost() {
        Vec test1 = new Vec("test", true, "testtest");
        Vec test2 = new Vec("test2", true, "testtest");
        Vec test3 = new Vec("test3", true, "testtest");
        Vec test4 = new Vec("test4", true, "testtest");
        Vec test5 = new Vec("test5", true, "testtest");
        hra1.getHerniPlan().getAktualniMistnost().vlozVec(test1);
        hra1.getHerniPlan().getAktualniMistnost().vlozVec(test2);
        hra1.getHerniPlan().getAktualniMistnost().vlozVec(test3);
        hra1.getHerniPlan().getAktualniMistnost().vlozVec(test4);

        hra1.zpracujPrikaz("batoh seber Pokébal");
        hra1.zpracujPrikaz("batoh seber Kompas");


        assertEquals(5, hra1.getHerniPlan().getAktualniMistnost().getSetVeci().size());
        assertEquals(2, hra1.getBatoh().getObsahBatohu().size());

        hra1.zpracujPrikaz("batoh seber test");
        hra1.zpracujPrikaz("batoh seber test2");
        hra1.zpracujPrikaz("batoh seber test3");
        hra1.zpracujPrikaz("batoh seber test4");
        assertEquals(1, hra1.getHerniPlan().getAktualniMistnost().getSetVeci().size());
        assertEquals(6, hra1.getBatoh().getObsahBatohu().size());

        hra1.getHerniPlan().getAktualniMistnost().vlozVec(test5);
        assertEquals(2, hra1.getHerniPlan().getAktualniMistnost().getSetVeci().size());

        hra1.zpracujPrikaz("batoh seber test5");
        assertEquals(2, hra1.getHerniPlan().getAktualniMistnost().getSetVeci().size());
        assertEquals(6, hra1.getBatoh().getObsahBatohu().size());
    }

    /**
     * Test se postaví do poslední místnosti a zkusí, jestli jde vyhrát.
     * */
    @Test
    public void testJdeHraDohrát() {
        Mistnost skola = new Mistnost("škola", "Zde si vybereš prvního pokemona",  2, 2);
        hra1.getHerniPlan().setAktualniMistnost(skola);
        Pokemon pikacu = new Pokemon("Pikaču", 3, 3, 1000);
        hra1.getHerniPlan().getAktualniMistnost().vlozPokemona(pikacu);

        hra1.zpracujPrikaz("chytni Pikaču");
        assertEquals(1, hra1.getPokemoni().getSetChycenychPokemonu().size());

        Mistnost arenaFinal = new Mistnost("boss", "Bojuj, tady jde o všechno!",  9, 12);

        hra1.getHerniPlan().setAktualniMistnost(arenaFinal);
        //hra1.zpracujPrikaz("final");

        //assertEquals(true,hra1.getKonecHry());
    }
    /**
     * Test provede kontrolu zda funguje chytání,
     * */

    @Test
    public void testChytani(){
        hra1.zpracujPrikaz("jdi škola");
        assertEquals(3,hra1.getHerniPlan().getAktualniMistnost().getSetPokemonu().size());
        hra1.zpracujPrikaz("chytni Pikaču");
        assertEquals(2,hra1.getHerniPlan().getAktualniMistnost().getSetPokemonu().size());
        assertEquals(1,hra1.getPokemoni().getSetChycenychPokemonu().size());
    }
    /**
     * Test provede kontrolu zda fungují souboje,
     * */
    @Test
    public void testSouboj(){
        Pokemon pikacu = new Pokemon("Pikaču", 3, 3, 1000);
        Pokemon pikacu1 = new Pokemon("Pikaču1", 3, 3, 10);
        hra1.getHerniPlan().getAktualniMistnost().vlozPokemona(pikacu);

        assertEquals(1,hra1.getHerniPlan().getAktualniMistnost().getSetPokemonu().size());
        hra1.getPokemoni().chytPokemona(pikacu1);
        assertEquals(1,hra1.getPokemoni().getSetChycenychPokemonu().size());

        hra1.getPokemoni().chytPokemona(pikacu);
        hra1.getHerniPlan().getAktualniMistnost().odeberPokemona(pikacu);
        assertEquals(2,hra1.getPokemoni().getSetChycenychPokemonu().size());
        assertEquals(0,hra1.getHerniPlan().getAktualniMistnost().getSetPokemonu().size());
    }

}
