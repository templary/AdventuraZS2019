package cz.vse.java.prom20.adventura.logika;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Třída PrikazFinal implementuje závěrečný souboj.
 * třída se využívá v posledním souboji s Bossem Piplupem.
 *
 * @author Miroslav Prokop
 * @version 1.0
 */
class PrikazFinal implements IPrikaz {
    private static final String NAZEV = "final";
    private final static String finalBoss = "Piplup";
    private HerniPlan herniPlan;
    private Pokemoni pokemoni;
    private Hra hra;

    /**
     * Konstruktor třídy
     *
     * @param herniPlan herní plán
     * @param hra       instance třídy Hra
     * @param pokemoni  instance třídy Pokemoni
     */
    public PrikazFinal(HerniPlan herniPlan, Pokemoni pokemoni, Hra hra) {
        this.herniPlan = herniPlan;
        this.pokemoni = pokemoni;
        this.hra = hra;
    }


    /**
     * @param parametry - zde se parametry nevyužívají.
     *                  Kód zjistí, zda je hráč ve finálí místnosti a jestli má ještě nějaké pokémony na souboj.
     *                  Pokud je hráč ve Finální mistnosti a má pokémony začína souboj.
     *                  pri zadaní příkazu se vybere náhodný pokémon a začne souboj, pokud nevyhraje, pokémon zemře a souboj
     *                  může opakovat pomocí znovu napsání příkazu final
     *                  Souboj se vyhodnocuje pomocí porovnání statistik pokémonu a násobením jich s náhodným číslem (1-6)
     *                  Kažný pokémon má tři útoky dokud neprohraje. V tomto případě po prohře zemře.
     * @return Hráč vyhrává pokud nějaký z jeho pokémonů porazí Bosse, prohrává, poku dnemá žádné další pokémony na souboj.
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (herniPlan.getAktualniMistnost().getUrovenHernihoPlanu() == 9 && pokemoni.getSetChycenychPokemonu().size() == 0) {
            hra.setKonecHry(true);
            //System.out.println(getAsciiArt());
            return "Už nemáš žádné pokémony, bohužel jsi prohrál.";
        }


        if (herniPlan.getAktualniMistnost().getUrovenHernihoPlanu() != 9) {
            return "Ještě jsi nedošel na konec, pro boj použij\n- bojuj jmeno_pokemona";
        } else {
            System.out.println("Finální zápas začíná, všichni tvoji pokémoni se jeden po druhém pokusí porazit Piplupa");
        }


        Pokemon boss = herniPlan.getAktualniMistnost().getPokemonPokudTuJe(finalBoss);
        int chyceniPokemoni = pokemoni.getSetChycenychPokemonu().size();

        Pokemon[] poleP = pokemoni.getChyceniPokemoniArray(chyceniPokemoni);


        int y = 0;


        while (pokemoni.getSetChycenychPokemonu().size() != 0) {
            Pokemon aktualni = poleP[y];
            System.out.println("Pokémon " + aktualni.getJmenoPokemona() + " vs Piplup.");
            System.out.println("------");
            int pokusy = 3;

            while (pokusy > 0) {

                int randomNum = ThreadLocalRandom.current().nextInt(1, 6);
                int randomNum2 = ThreadLocalRandom.current().nextInt(1, 6);

                System.out.println("Hodil jsi: " + randomNum + "\nPokémon Piplup" + " Hodil: " + randomNum2);
                System.out.println("------");

                int piplupStats = boss.getLevel() * boss.getSilaPokemona() * randomNum2;
                int pokemonHraceStats = aktualni.getLevel() * aktualni.getSilaPokemona() * randomNum;

                if (piplupStats < pokemonHraceStats || piplupStats == pokemonHraceStats) {
                    hra.setKonecHry(true);
                    System.out.println(getAsciiArt());
                    return "Vyhrál jsi! Podařilo se ti porazit finálního bosse Piplupa!" + "\nOslavuj " + aktualni.getJmenoPokemona() + " dokázal nemožné!";

                } else {
                    System.out.println("Tento soubo jsi prohrál, máš ještě " + pokusy + " než " + aktualni.getJmenoPokemona() + " zemře.");
                    pokusy--;
                    if (pokusy == 0) {
                        pokemoni.zabijPokemona(aktualni);
                        return boss.getJmenoPokemona() + " usmrtil tvého pokémona " + aktualni.getJmenoPokemona();
                    }
                }
            }
        }
        hra.setKonecHry(true);
        return "Prohrál jsi, poslal jsi všechny své pokémony na smrt.";
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

    private String getAsciiArt() {
        return "     __                                _.--'\"7\n" +
                "    `. `--._                        ,-'_,-  ,'\n" +
                "     ,'  `-.`-.                   /' .'    ,|\n" +
                "     `.     `. `-     __...___   /  /     - j\n" +
                "       `.     `  `.-\"\"        \" .  /       /\n" +
                "         \\     /                ` /       /\n" +
                "          \\   /                         ,'\n" +
                "          '._'_               ,-'       |\n" +
                "             | \\            ,|  |   ...-'\n" +
                "             || `         ,|_|  |   | `             _..__\n" +
                "            /|| |          | |  |   |  \\  _,_    .-\"     `-.\n" +
                "           | '.-'          |_|_,' __!  | /|  |  /           \\\n" +
                "   ,-...___ .=                  ._..'  /`.| ,`,.      _,.._ |\n" +
                "  |   |,.. \\     '  `'        ____,  ,' `--','  |    /      |\n" +
                " ,`-..'  _)  .`-..___,---'_...._/  .'      '-...'   |      /\n" +
                "'.__' \"\"'      `.,------'\"'      ,/            ,     `.._.' `.\n" +
                "  `.             | `--........,-'.            .         \\     \\\n" +
                "    `-.          .   '.,--\"\"     |           ,'\\        |      .\n" +
                "       `.       /     |          L          ,\\  .       |  .,---.\n" +
                "         `._   '      |           \\        /  .  L      | /   __ `.\n" +
                "            `-.       |            `._   ,    l   .    j |   '  `. .\n" +
                "              |       |               `\"' |  .    |   /  '      .' |\n" +
                "              |       |                   j  |    |  /  , `.__,'   |\n" +
                "              `.      L                 _.   `    j ,'-'           |\n" +
                "               |`\"---..\\._______,...,--' |   |   /|'      /        j\n" +
                "               '       |                 |   .  / |      '        /\n" +
                "                .      .              ____L   \\'  j    -',       /\n" +
                "               / `.     .          _,\"     \\   | /  ,-','      ,'\n" +
                "              /    `.  ,'`-._     /         \\  i'.,'_,'      .'\n" +
                "             .       `.      `-..'             |_,-'      _.'\n" +
                "             |         `._      |            ''/      _,-'\n" +
                "             |            '-..._\\             `__,.--'\n" +
                "            ,'           ,' `-.._`.            .\n" +
                "           `.    __      |       \"'`.          |\n" +
                "             `-\"'  `\"\"\"\"'            7         `.\n" +
                "                                    `---'--.,'\"`'";
    }

}
