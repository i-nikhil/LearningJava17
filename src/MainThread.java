import java.util.concurrent.TimeUnit;

public class MainThread {
    public static void main(String[] args) {

        var currentThread = Thread.currentThread();

        System.out.println(currentThread);
        System.out.println(currentThread.getClass());

        currentThread.setName("My Main Thread");
        currentThread.setPriority(Thread.MAX_PRIORITY); // Min = 1, Normal = 5, Max = 10
        System.out.println(currentThread);

        // High priority threads have a better chance of being scheduled by a thread scheduler

        //using thread class
        CustomThreadA a = new CustomThreadA();
        CustomThreadB b = new CustomThreadB();

        //using runnable interface
        Runnable myRunnable = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println('*');
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread c = new Thread(myRunnable);

        Thread d = new Thread(() -> System.out.println("New thread"));

        // a.run(); b.run(); //sequential execution

        //concurrent execution
        a.start();
        b.start();
        c.start();

        try {
            a.join();
            b.join();
            c.join();
            d.start();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("______________________________________________");

        BankAccount account1 = new BankAccount(10000);

        Thread t1 = new Thread(() -> account1.withdraw(2500));
        Thread t2 = new Thread(() -> account1.deposit(5000));
        Thread t3 = new Thread(() -> account1.withdraw(2500));

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(account1.getBalance());
    }
}

/**
 * Custom thread A
 */
class CustomThreadA extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1000); //checked exception - thrown at compile time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * Custom thread B
 */
class CustomThreadB extends Thread {
    @Override
    public void run() {
        for (char i = 'a'; i <= 'e'; i++) {
            System.out.println(i);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// synchronizing a thread
class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public synchronized void deposit(double amount) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        balance += amount;

        System.out.println("deposited: " + amount + ", current balance: " + balance);
    }

    public void withdraw(double amount) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        synchronized (new Object()) {
            balance -= amount;
        }

        System.out.println("withdrawn: " + amount + ", current balance: " + balance);
    }
}