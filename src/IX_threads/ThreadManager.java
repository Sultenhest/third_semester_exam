package IX_threads;

public class ThreadManager {
    public static void main(String[] args) {
        Account account = new Account();

        Thread t1 = new Thread( new UserThread("Lorem", 10, account) );
        Thread t2 = new Thread( new UserThread("Ipsum", -20, account) );
        Thread t3 = new Thread( new UserThread("Dolar", 10, account) );

        t1.start();
        t2.start();
        t3.start();
    }
}
