package IX_threads;

public class UserThread implements Runnable {
    private String name;
    private int amount;
    private Account account;

    public UserThread(String name, int amount, Account account) {
        this.name    = name;
        this.amount  = amount;
        this.account = account;
    }

    public void run() {
        try {
            if( amount < 0 ) {
                for( int i = amount; i < 0; i++ ) {
                    Thread.sleep(10);
                    account.decrement(name, 1);
                }
            }else{
                for( int i = 0; i < amount; i++ ) {
                    Thread.sleep(10);
                    account.increment(name, 1);
                }
            }
        } catch (InterruptedException e) {}
    }
}
