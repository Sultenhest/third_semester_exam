package IX_threads;

public class ThreadRaceContext {
    private static int spot = 0;
    private static String[] results = new String[10];

    public synchronized static void add( String raceCarInfo ){
        results[ spot ] = raceCarInfo;
        spot++;
    }

    public static void getResults() {
        System.out.println( "Results:" );

        for ( int i = 0; i < 10; i++ ) {
            System.out.println( ( i + 1 ) + ": " + results[ i ] );
        }
    }
}
