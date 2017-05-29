package IX_threads;

import java.util.Random;

public class ThreadRaceCompetitor implements Runnable {
    private String name;

    public ThreadRaceCompetitor( String name ) {
        setName( name );
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void run() {
        try {
            int raceTime = getRandomWaitTime();

            Thread.sleep( raceTime );

            ThreadRaceContext.add( getName() + " - Time: " + raceTime );
        } catch (InterruptedException e) {}
    }

    private int getRandomWaitTime() {
        Random rand = new Random();
        return rand.nextInt( 999 ) + 1;
    }
}
