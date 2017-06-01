package IX_threads;

public class Account {
    private int balance = 100;

    public synchronized void increment( String user, int n ) {
        balance += n;
        System.out.println( user + ": " + balance );
    }

    public synchronized void decrement( String user, int n ) {
        balance -= n;
        System.out.println( user + ": " + balance );
    }
}
