package cz.vse.java.prom20.adventura.logika;

import java.util.HashSet;
import java.util.Set;


/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 * <p>
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny prostory,
 * propojuje je vzájemně pomocí východů
 * a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 * @author Miroslav Prokop
 * @version 1.0
 */
public class HerniPlan {

    private Mistnost aktualniMistnost;
    private Set<Mistnost> seznamMistnosti;
    // private Set<Pokemon> seznamPokemonu;

    /**
     * Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     * Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        this.seznamMistnosti = new HashSet<>();
        //this.seznamPokemonu = new HashSet<>();
        zalozProstoryHry();

    }

    /**
     * Vytváří jednotlivé prostory a propojuje je pomocí východů.
     * Jako výchozí aktuální prostor nastaví start.
     * dále vytáří pokémony, vkládá je do místností. to same s věcma.
     */
    private void zalozProstoryHry() {

        Mistnost start = new Mistnost("start", "Zde startuješ",  1, 1);
        Mistnost skola = new Mistnost("škola", "Zde si vybereš prvního pokemona",  2, 2);
        Mistnost chytani = new Mistnost("chytání", "Zde si chytneš prvního pokemona",  3, 3);
        Mistnost temnyLes = new Mistnost("les", "Les plný nástrah a tajemství",  4, 4);
        Mistnost veselaLouka = new Mistnost("louka", "Štěstí a radost, to je to, co dne potkáš. Možná.",  4, 5);
        Mistnost arena1 = new Mistnost("aréna1", "Pokud chceš být úspěšným trenérem, musíš umět bojovat!", 5, 6);
        Mistnost oblazkoveMesto = new Mistnost("Oblázkové", "Město plné pokemonů a hodných lidí",  6, 7);
        Mistnost chromoveMesto = new Mistnost("Chromové", "Město, kde se život řídí penězi",  6, 8);
        Mistnost seladonoveMesto = new Mistnost("Seladonové", "Hlavní moto tohoto města je úspěch, lidé zde jsou tvrdě za svým snem.",  6, 9);
        Mistnost arena2 = new Mistnost("aréna2", "Tentokrát se již budeš muset snažit daleko víc!",  7, 10);
        Mistnost ostrovKrveproliti = new Mistnost("krveprolití", "Pokud chceš vstupit, musíš být nejen silný, ale i chytrý!",  8, 11);
        Mistnost arenaFinal = new Mistnost("boss", "Bojuj, tady jde o všechno!", 9, 12);

        /*
        typPokemona[0] = "Normální";
        typPokemona[1] = "Lesní";
        typPokemona[2] = "Kamenný";
        typPokemona[3] = "Elektrický";
        typPokemona[4] = "Vodní";
        typPokemona[5] = "Ohnivý";
        typPokemona[6] = "Legendární";

    }*/


        Pokemon pikacu = new Pokemon("Pikaču", 3, 3, 1);
        Pokemon bulbasaurus = new Pokemon("Bulbasaurus", 1, 3, 1);
        Pokemon charmaneder = new Pokemon("Charmander", 5, 3, 1);

        skola.vlozPokemona(pikacu);
        skola.vlozPokemona(bulbasaurus);
        skola.vlozPokemona(charmaneder);

        Pokemon raicu = new Pokemon("Raiču", 3, 7, 1);
        Pokemon ivisaurus = new Pokemon("Ivysaurus", 1, 5, 2);
        Pokemon charmeleon = new Pokemon("Charmeleon", 5, 6, 1);
        chytani.vlozPokemona(raicu);
        chytani.vlozPokemona(ivisaurus);
        chytani.vlozPokemona(charmeleon);

        Pokemon oddish = new Pokemon("Oddish", 1, 22, 1);
        Pokemon bellsprut = new Pokemon("Bellsprut", 1, 24, 1);
        Pokemon exeggcute = new Pokemon("Exeggcute", 1, 27, 1);
        temnyLes.vlozPokemona(oddish);
        temnyLes.vlozPokemona(bellsprut);
        temnyLes.vlozPokemona(exeggcute);

        Pokemon gloom = new Pokemon("Gloom", 1, 10, 2);
        Pokemon weepinbell = new Pokemon("Weepinbell", 1, 12, 2);
        Pokemon caterpie = new Pokemon("Caterpie", 1, 15, 1);
        Pokemon metapod = new Pokemon("Metapod", 1, 8, 2);
        veselaLouka.vlozPokemona(gloom);
        veselaLouka.vlozPokemona(weepinbell);
        veselaLouka.vlozPokemona(caterpie);
        veselaLouka.vlozPokemona(metapod);

        Pokemon ninetales = new Pokemon("Ninetales", 5, 17, 3);
        Pokemon rapidas = new Pokemon("Rapidas", 5, 23, 2);
        Pokemon poliwrath = new Pokemon("Poliwrath", 4, 16, 3);
        Pokemon alakazam = new Pokemon("Alakazam", 3, 41, 5);
        arena1.vlozPokemona(ninetales);
        arena1.vlozPokemona(rapidas);
        arena1.vlozPokemona(poliwrath);
        arena1.vlozPokemona(alakazam);

        Pokemon onix = new Pokemon("Onix", 2, 22, 5);
        Pokemon golem = new Pokemon("Golem", 2, 17, 4);
        Pokemon graveler = new Pokemon("Graveler", 2, 19, 5);
        oblazkoveMesto.vlozPokemona(onix);
        oblazkoveMesto.vlozPokemona(golem);
        oblazkoveMesto.vlozPokemona(graveler);

        Pokemon drowzee = new Pokemon("Drowzee", 3, 19, 6);
        Pokemon voltrob = new Pokemon("Voltrob", 3, 20, 5);
        Pokemon electrode = new Pokemon("Electrode", 3, 19, 5);
        chromoveMesto.vlozPokemona(drowzee);
        chromoveMesto.vlozPokemona(voltrob);
        chromoveMesto.vlozPokemona(electrode);

        Pokemon horsea = new Pokemon("Horsea", 4, 26, 3);
        Pokemon seadra = new Pokemon("Seadra", 4, 25, 4);
        Pokemon vaporeon = new Pokemon("Vaporeon", 4, 45, 5);
        seladonoveMesto.vlozPokemona(horsea);
        seladonoveMesto.vlozPokemona(seadra);
        seladonoveMesto.vlozPokemona(vaporeon);

        Pokemon snorlax = new Pokemon("Snorlax", 0, 26, 8);
        Pokemon flareon = new Pokemon("Flareon", 5, 22, 8);
        Pokemon dragonite = new Pokemon("Dragonite", 5, 30, 7);
        arena2.vlozPokemona(snorlax);
        arena2.vlozPokemona(flareon);
        arena2.vlozPokemona(dragonite);

        Pokemon zapdos = new Pokemon("Zapdos", 3, 26, 9);
        Pokemon mewtwo = new Pokemon("Mewtwo", 3, 29, 9);
        Pokemon dragonair = new Pokemon("Dragonair", 5, 40, 6);
        Pokemon lapras = new Pokemon("Lapras", 4, 23, 10);
        ostrovKrveproliti.vlozPokemona(zapdos);
        ostrovKrveproliti.vlozPokemona(mewtwo);
        ostrovKrveproliti.vlozPokemona(dragonair);
        ostrovKrveproliti.vlozPokemona(lapras);

        Pokemon piplup = new Pokemon("Piplup", 6, 16, 100);
        arenaFinal.vlozPokemona(piplup);


        Vec elixirS = new Vec("elixírS", true, "Pomocí tohoto elixíru můžeš trénovat pokémona.(+4 síly.)");
        Vec elixirM = new Vec("elixírM", true, "Pomocí tohoto elixíru můžeš trénovat pokémona.(+5 síly.)");
        Vec elixirL = new Vec("elixírL", true, "Pomocí tohoto elixíru můžeš trénovat pokémona.(+6 síly.)");

        Vec pokeball = new Vec("Pokébal", true, "Tohle je pokeball");
        Vec strom = new Vec("Strom", false, "Toto je starý a moudrý strom.");
        Vec hora = new Vec("Hora", false, "Vysoká hora");
        Vec krev = new Vec("Krev", false, "tratoliště krve");
        Vec susenka = new Vec("Sušenka", true, "Sušenkou můžeš nakrmit pokémona.");
        Vec stul = new Vec("Stůl", false, "Stůl");
        Vec koberec = new Vec("Koberec", false, "Krásný koberec");
        Vec lahev = new Vec("Láhev", true, "Láhev od rumu.");
        Vec kompas = new Vec("Kompas", true, "Starý a zničený kompas.");
        Vec tabule = new Vec("Tabule", false, "Popsaná tabule");
        Vec trava = new Vec("Tráva", false, "Neposekaná tráva");
        Vec kamen = new Vec("Kámen", false, "Velký kámen");
        Vec socha = new Vec("Socha", false, "Socha největšího bojovníka.");
        Vec radnice = new Vec("Radnice", false, "Městská radnice.");
        Vec mrtvola = new Vec("Mrtvola", false, "Dejsi si pozor, abysi nedopadl stejně.");


        start.vlozVec(pokeball);
        start.vlozVec(koberec);
        start.vlozVec(kompas);

        skola.vlozVec(tabule);
        skola.vlozVec(susenka);
        skola.vlozVec(stul);

        chytani.vlozVec(strom);

        temnyLes.vlozVec(strom);
        temnyLes.vlozVec(susenka);

        veselaLouka.vlozVec(trava);
        veselaLouka.vlozVec(strom);

        arena1.vlozVec(krev);
        arena1.vlozVec(stul);
        arena1.vlozVec(pokeball);

        oblazkoveMesto.vlozVec(hora);
        oblazkoveMesto.vlozVec(kamen);
        oblazkoveMesto.vlozVec(susenka);
        oblazkoveMesto.vlozVec(radnice);

        seladonoveMesto.vlozVec(socha);
        seladonoveMesto.vlozVec(radnice);

        chromoveMesto.vlozVec(radnice);
        chromoveMesto.vlozVec(trava);
        chromoveMesto.vlozVec(lahev);
        chromoveMesto.vlozVec(strom);

        arena2.vlozVec(kamen);
        arena2.vlozVec(strom);
        arena2.vlozVec(krev);
        arena2.vlozVec(trava);


        ostrovKrveproliti.vlozVec(krev);
        ostrovKrveproliti.vlozVec(kamen);
        ostrovKrveproliti.vlozVec(mrtvola);

        arenaFinal.vlozVec(hora);
        arenaFinal.vlozVec(krev);
        arenaFinal.vlozVec(mrtvola);


        chytani.vlozVec(elixirL);
        veselaLouka.vlozVec(elixirS);
        ostrovKrveproliti.vlozVec(elixirM);

        seznamMistnosti.add(start);
        seznamMistnosti.add(skola);
        seznamMistnosti.add(chytani);
        seznamMistnosti.add(temnyLes);
        seznamMistnosti.add(veselaLouka);
        seznamMistnosti.add(arena1);
        seznamMistnosti.add(oblazkoveMesto);
        seznamMistnosti.add(chromoveMesto);
        seznamMistnosti.add(seladonoveMesto);
        seznamMistnosti.add(arena2);
        seznamMistnosti.add(ostrovKrveproliti);
        seznamMistnosti.add(arenaFinal);


        start.setVychody(skola);
        skola.setVychody(chytani);


        skola.vlozVec(pokeball);
        skola.vlozVec(stul);


        chytani.setVychody(temnyLes);
        chytani.setVychody(veselaLouka);


        temnyLes.setVychody(veselaLouka);
        temnyLes.setVychody(arena1);

        veselaLouka.setVychody(temnyLes);
        veselaLouka.setVychody(arena1);

        arena1.setVychody(oblazkoveMesto);
        arena1.setVychody(chromoveMesto);
        arena1.setVychody(seladonoveMesto);

        oblazkoveMesto.setVychody(arena2);
        oblazkoveMesto.setVychody(chromoveMesto);
        oblazkoveMesto.setVychody(seladonoveMesto);

        chromoveMesto.setVychody(oblazkoveMesto);
        chromoveMesto.setVychody(seladonoveMesto);
        chromoveMesto.setVychody(arena2);

        seladonoveMesto.setVychody(oblazkoveMesto);
        seladonoveMesto.setVychody(chromoveMesto);
        seladonoveMesto.setVychody(arena2);

        arena2.setVychody(ostrovKrveproliti);

        ostrovKrveproliti.setVychody(arenaFinal);

        setAktualniMistnost(start);
    }

    /**
     * Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     * @return aktuální prostor
     */


    public Mistnost getAktualniMistnost() {
        return aktualniMistnost;
    }

    /**
     * Metoda nastaví aktuální mistnost, používá se nejčastěji při přechodu mezi prostory
     *
     * @param mistnost nový aktuální mistnost
     */
    public void setAktualniMistnost(Mistnost mistnost) {
        aktualniMistnost = mistnost;
    }


}
