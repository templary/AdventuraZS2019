package cz.vse.java.prom20.adventura.uiText;


import cz.vse.java.prom20.adventura.logika.IHra;

import java.util.Scanner;
/**
 *  trída  TextoveRozhrani
 * 
 *  Toto je uživatelského rozhraní aplikace Adventura
 *  Tato třída vytváří instanci třídy Hra, která představuje logiku aplikace.
 *  Čte vstup zadaný uživatelem a předává tento řetězec logice a vypisuje odpověď logiky na konzoli.
 *  
 *  
 *
 *@author Miroslav Prokop
 *@version    1.0
 */

public class TextoveRozhrani {
    private IHra hra;


    /**
     *  Vytváří hru.
     */
    public TextoveRozhrani(IHra hra) {
        this.hra = hra;
    }

    /**
     *  Hlavní metoda hry. Vypíše úvodní text a pak opakuje čtení a zpracování
     *  příkazu od hráče do konce hry (dokud metoda konecHry() z logiky nevrátí
     *  hodnotu true). Nakonec vypíše text epilogu.
     */
    public void hraj() {
        System.out.println(asciiLogo());
        System.out.println(hra.vratUvitani());

        // základní cyklus programu - opakovaně se čtou příkazy a poté
        // se provádějí do konce hry.

        while (!hra.konecHry()) {
            String radek = prectiString();
            System.out.println(hra.zpracujPrikaz(radek));
        }
        System.out.println(getAsciiRIP());
        System.out.println(hra.vratEpilog());
    }

    /**
     *  Metoda přečte příkaz z příkazového řádku
     *
     *@return    Vrací přečtený příkaz jako instanci třídy String
     */
    private String prectiString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        return scanner.nextLine();
    }

    /**
     * Metoda vrací ascii art
     */

    private String asciiLogo(){
        return "                                  ,'\\\n" +
                "    _.----.        ____         ,'  _\\   ___    ___     ____\n" +
                "_,-'       `.     |    |  /`.   \\,-'    |   \\  /   |   |    \\  |`.\n" +
                "\\      __    \\    '-.  | /   `.  ___    |    \\/    |   '-.   \\ |  |\n" +
                " \\.    \\ \\   |  __  |  |/    ,','_  `.  |          | __  |    \\|  |\n" +
                "   \\    \\/   /,' _`.|      ,' / / / /   |          ,' _`.|     |  |\n" +
                "    \\     ,-'/  /   \\    ,'   | \\/ / ,`.|         /  /   \\  |     |\n" +
                "     \\    \\ |   \\_/  |   `-.  \\    `'  /|  |    ||   \\_/  | |\\    |\n" +
                "      \\    \\ \\      /       `-.`.___,-' |  |\\  /| \\      /  | |   |\n" +
                "       \\    \\ `.__,'|  |`-._    `|      |__| \\/ |  `.__,'|  | |   |\n" +
                "        \\_.-'       |__|    `-._ |              '-.|     '-.| |   |\n" +
                "                                `'                            '-._|";
    }

    /**
     * Metoda vrací ascii art ve stringu
     */
    private String getAsciiRIP(){
        return "                                 _____  _____\n" +
                "                                <     `/     |\n" +
                "                                 >          (\n" +
                "                                |   _     _  |\n" +
                "                                |  |_) | |_) |\n" +
                "                                |  | \\ | |   |\n" +
                "                                |            |\n" +
                "                 ______.______%_|            |__________  _____\n" +
                "               _/                                       \\|     |\n" +
                "              |                                                <\n" +
                "              |_____.-._________              ____/|___________|\n" +
                "                                |            |\n" +
                "                                |            |\n" +
                "                                |            |\n" +
                "                                |            |\n" +
                "                                |   _        <\n" +
                "                                |__/         |\n" +
                "                                 / `--.      |\n" +
                "                               %|            |%\n" +
                "                           |/.%%|          -< @%%%\n" +
                "                           `\\%`@|     v      |@@%@%%\n" +
                "                         .%%%@@@|%    |    % @@@%%@%%%%\n" +
                "                    _.%%%%%%@@@@@@%%_/%\\_%@@%%@@@@@@@%%%%%%";
    }

}
