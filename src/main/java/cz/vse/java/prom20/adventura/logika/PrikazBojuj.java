package cz.vse.java.prom20.adventura.logika;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Třída PrikazFinal implementuje souboje v průběhu hry.
 * třída se využívá ve všech místnostech krom 1,2 a final arény.
 *
 * @author Miroslav Prokop
 * @version 1.0
 */
class PrikazBojuj implements IPrikaz {
    private static final String NAZEV = "bojuj";
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
    public PrikazBojuj(HerniPlan herniPlan, Pokemoni pokemoni, Hra hra) {
        this.herniPlan = herniPlan;
        this.pokemoni = pokemoni;
        this.hra = hra;
    }


    /**
     * @param parametry - jmeno_pokemona_na_souboj
     *                  Kód zjistí, zda je hráč v místnosti kde je dovolené dělat souboje.
     *                  po zadání parametru kód ověří validitu vstupu.
     *                  Po ověření validity vstupu se dostáváme k volbě pokémona s kterým hráč chce bojovat.
     *                  Kód provede kontrolu validity druhého vstupu ze Scanneru
     *                  Program přečne bojové info o obou pokémonech a zeptá se, zda chce hráč pokračovat.
     *                  Souboj se vyhodnocuje pomocí porovnání statistik pokémonu a násobením jich s náhodným číslem (1-6)
     * @return hráč získá pokéma pokud ho porazí dle daného výpočtu o řádek výše. (> or ==)
     */
    @Override
    public String provedPrikaz(String... parametry) {

        if (herniPlan.getAktualniMistnost().getUrovenHernihoPlanu() == 2) {
            return "v této místnosti s pokémony nemusíš zatím bojovat.\nPoužij - chytni jmeno_pokemona ";
        }

        if (herniPlan.getAktualniMistnost().getUrovenHernihoPlanu() == 9) {
            return "Jsi v místnosti s Bossem. Použij příkaz -final";
        }

        if (parametry.length == 0) {
            return "Musíš zadat, proti jakému pokémonovi chceš bojovat.\n V této místnosti jsou: " + herniPlan.getAktualniMistnost().getSeznamPokemonuJakoText();
        }


        if (pokemoni.getSetChycenychPokemonu().isEmpty()) {
            hra.setKonecHry(true);
            System.out.println(getAsciiDragon());
            return "Zaútočil jsi bez pokémona a to tě stálo život. Prohrál jsi.";
        }

        String parametr = parametry[0];


        Pokemon p = herniPlan.getAktualniMistnost().getPokemonPokudTuJe(parametr);
        String bojovnik = "";
        if (p == null) {
            return parametr + " tu není";
        } else {
            if (herniPlan.getAktualniMistnost().getUrovenHernihoPlanu() == 2) {
                return "Tady bojovat nemusíš, pouzij příkaz chytni jmeno_pokemona";
            } else {
                if (herniPlan.getAktualniMistnost().getSeznamPokemonuJakoText() != null) {
                    System.out.println(getAsciiDragon());
                    System.out.println("Vyber svého bojovníka!\n " + pokemoni.getSeznamChycenychPokemonuJakoText());
                    Scanner scanner = new Scanner(System.in);


                    bojovnik = scanner.nextLine();


                    while (!pokemoni.getNazvyChycenychPokemonu().contains(bojovnik)) {
                        System.out.println(bojovnik + "Nemáš! Zadej znovu.");
                        bojovnik = scanner.nextLine();
                    }
                }
            }
        }
        Pokemon souper = herniPlan.getAktualniMistnost().getPokemonPokudTuJe(parametr);
        Pokemon pokemonHrace = pokemoni.getPokemonPokudTuJe(bojovnik);

        System.out.println("Tvůj pokémon: " + pokemonHrace.toString());
        System.out.println("--------");
        System.out.println("Protivník: " + souper.toString());
        System.out.println("--------");

        int pokusy = 3;
        Scanner sc = new Scanner(System.in);
        String ovladac = "";

        while (pokusy > 0) {

            while (!(ovladac.equals("konec") || ovladac.equals("boj"))) {
                System.out.println("Napiš: boj pro souboj, konec pro odstoupení");
                ovladac = sc.nextLine();
            }

            if (ovladac.equals("konec")) {
                return "Vzdal jsi se.";
            }

            if (ovladac.equals("boj")) {

                int randomNum = ThreadLocalRandom.current().nextInt(1, 6);
                int randomNum2 = ThreadLocalRandom.current().nextInt(1, 6);

                System.out.println("Hodil jsi: " + randomNum + "\nPokémon " + souper.getJmenoPokemona() + " Hodil: " + randomNum2);
                System.out.println("--------");

                int souperStats = souper.getLevel() * souper.getSilaPokemona() * randomNum2;
                int pokemonHraceStats = pokemonHrace.getLevel() * pokemonHrace.getSilaPokemona() * randomNum;

                if (souperStats < pokemonHraceStats || souperStats == pokemonHraceStats) {
                    herniPlan.getAktualniMistnost().odeberPokemona(souper);
                    pokemoni.chytPokemona(souper);
                    return "Tvůj pokémon " + pokemonHrace.getJmenoPokemona() + " Vyhrál.\nNyní je " + souper.getJmenoPokemona() + " tvůj.";
                } else {
                    System.out.println("Bohuže jsi prohrál, máš ještě " + pokusy + " pokusy než ti pokémon uteče.");
                    pokusy--;
                    if (pokusy == 0) {
                        herniPlan.getAktualniMistnost().odeberPokemona(souper);
                        return "Pokémon " + souper.getJmenoPokemona() + " ti bohužel utekl.";
                    }
                }
            }
        }
        return "Konec souboje.";
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

    /**
     * Metoda vrací ascii art ve stringu
     */
    public String getAsciiDragon() {
        return "                 .\"-,.__\n" +
                "                 `.     `.  ,\n" +
                "              .--'  .._,'\"-' `.\n" +
                "             .    .'         `'\n" +
                "             `.   /          ,'\n" +
                "               `  '--.   ,-\"'\n" +
                "                `\"`   |  \\\n" +
                "                   -. \\, |\n" +
                "                    `--Y.'      ___.\n" +
                "                         \\     L._, \\\n" +
                "               _.,        `.   <  <\\                _\n" +
                "             ,' '           `, `.   | \\            ( `\n" +
                "          ../, `.            `  |    .\\`.           \\ \\_\n" +
                "         ,' ,..  .           _.,'    ||\\l            )  '\".\n" +
                "        , ,'   \\           ,'.-.`-._,'  |           .  _._`.\n" +
                "      ,' /      \\ \\        `' ' `--/   | \\          / /   ..\\\n" +
                "    .'  /        \\ .         |\\__ - _ ,'` `        / /     `.`.\n" +
                "    |  '          ..         `-...-\"  |  `-'      / /        . `.\n" +
                "    | /           |L__           |    |          / /          `. `.\n" +
                "   , /            .   .          |    |         / /             ` `\n" +
                "  / /          ,. ,`._ `-_       |    |  _   ,-' /               ` \\\n" +
                " / .           \\\"`_/. `-_ \\_,.  ,'    +-' `-'  _,        ..,-.    \\`.\n" +
                ".  '         .-f    ,'   `    '.       \\__.---'     _   .'   '     \\ \\\n" +
                "' /          `.'    l     .' /          \\..      ,_|/   `.  ,'`     L`\n" +
                "|'      _.-\"\"` `.    \\ _,'  `            \\ `.___`.'\"`-.  , |   |    | \\\n" +
                "||    ,'      `. `.   '       _,...._        `  |    `/ '  |   '     .|\n" +
                "||  ,'          `. ;.,.---' ,'       `.   `.. `-'  .-' /_ .'    ;_   ||\n" +
                "|| '              V      / /           `   | `   ,'   ,' '.    !  `. ||\n" +
                "||/            _,-------7 '              . |  `-'    l         /    `||\n" +
                ". |          ,' .-   ,' ||               | .-.        `.      .'     ||\n" +
                " `'        ,'    `\".'    |               |    `.        '. -.'       `'\n" +
                "          /      ,'      |               |,'    \\-.._,.'/'\n" +
                "          .     /        .               .       \\    .''\n" +
                "        .`.    |         `.             /         :_,'.'\n" +
                "          \\ `...\\   _     ,'-.        .'         /_.-'\n" +
                "           `-.__ `,  `'   .  _.>----''.  _  __  /\n" +
                "                .'        /\"'          |  \"'   '_\n" +
                "               /_|.-'\\ ,\".             '.'`__'-( \\\n" +
                "                 / ,\"'\"\\,'               `/  `-.|\"";
    }


}
