package IX_threads;

public class ThreadManager {
    public static void main(String[] args) {
        Account account = new Account();

        Thread t1 = new Thread( new RunnableThread("Lorem", 10, account) );
        Thread t2 = new Thread( new RunnableThread("Ipsum", -20, account) );
        Thread t3 = new Thread( new RunnableThread("Dolar", 10, account) );

        /*
        ExtendThread t1 = new ExtendThread("Lorem", 10, account);
        ExtendThread t2 = new ExtendThread("Ipsum", -20, account);
        ExtendThread t3 = new ExtendThread("Dolar", 10, account);
        */

        t1.start();
        t2.start();
        t3.start();
    }
}
