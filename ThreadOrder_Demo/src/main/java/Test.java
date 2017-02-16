import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

    public synchronized static void  staticA() throws InterruptedException {
        while(true) {
            System.out.println("static a");
            Thread.sleep(5000);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(new RunStaticA(test));
        cachedThreadPool.execute(new RunA(test));
        Thread.sleep(3000);
        cachedThreadPool.execute(new RunB(test));
    }


    synchronized public void a() throws InterruptedException {
        while(true) {
            System.out.println("a");
            Thread.sleep(5000);
        }
    }
    synchronized public void b() throws InterruptedException {
         while(true) {
             System.out.println("b");
             Thread.sleep(1000);
         }
    }

    static class RunStaticA implements Runnable {
        Test test;
        public RunStaticA(Test test) {
            this.test = test;
        }
        public void run() {
            try {
                test.staticA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    static class RunA implements Runnable {
        Test test;
        public RunA(Test test) {
            this.test = test;
        }
        public void run() {
            try {
                test.a();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    static class RunB implements Runnable {
        Test test;
        public RunB(Test test) {
            this.test = test;
        }
        public void run() {
            try {
                test.b();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}