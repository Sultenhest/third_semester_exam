package IX_threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadRace {
    public static void main( String[] args ) {
        System.out.println( "The race begins.." );

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.execute( new ThreadRaceCompetitor("Red") );
        executorService.execute( new ThreadRaceCompetitor("Blue") );
        executorService.execute( new ThreadRaceCompetitor("Green") );
        executorService.execute( new ThreadRaceCompetitor("Yellow") );
        executorService.execute( new ThreadRaceCompetitor("Black") );
        executorService.execute( new ThreadRaceCompetitor("White") );
        executorService.execute( new ThreadRaceCompetitor("Purple") );
        executorService.execute( new ThreadRaceCompetitor("Orange") );
        executorService.execute( new ThreadRaceCompetitor("Pink") );
        executorService.execute( new ThreadRaceCompetitor("Teal") );

        executorService.shutdown();

        while ( !executorService.isTerminated() ){}

        ThreadRaceContext.getResults();
    }
}
