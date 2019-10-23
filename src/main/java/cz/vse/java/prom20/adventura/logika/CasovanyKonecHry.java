package cz.vse.java.prom20.adventura.logika;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class CasovanyKonecHry {
    private final ThreadLocal<Timer> timer = ThreadLocal.withInitial(Timer::new);
    private TimerTask exitApp = new TimerTask() {
        public void run() {
            System.exit(0);
        }
    };

    public void exitTimer() {
        timer.get().schedule(exitApp, new Date(System.currentTimeMillis() + 5 * 1000));
    }

}