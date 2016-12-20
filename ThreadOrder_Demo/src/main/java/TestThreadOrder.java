package coderr.kerwin.codetest.test;

public class TestThreadOrder {

    public static void main(String[] args) throws InterruptedException {
        TempObject obj1 = new TempObject(1,5);
        TempObject obj2 = new TempObject(2,5);
        TempObject obj3 = new TempObject(3,5);
        new Thread(new SelfRunnable3(obj3, obj1)).start();
        Thread.sleep(100);
        new Thread(new SelfRunnable3(obj1, obj2)).start();
        Thread.sleep(100);
        new Thread(new SelfRunnable3(obj2, obj3)).start();
        Thread.sleep(100);
    }

    static class SelfRunnable3 implements Runnable {

        private TempObject prev;
        private TempObject self;
        private static int num = 1;

        public SelfRunnable3(TempObject prev, TempObject self) {
            this.prev = prev;
            this.self = self;
        }

        public void run() {
            while (num <= 75) {
                System.out.println("p:"+prev.getId()+";s:"+self.getId()+";p-"+num);
                synchronized (prev) {
                    System.out.println("p:"+prev.getId()+";s:"+self.getId()+";s-"+num);
                    synchronized (self) {
                        self.notify();
                        self.print(num);
                        num+=self.getCount();
                    }
                    try {
                        prev.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    static class TempObject {

        private int id;
        private int count;

        public TempObject(int id, int count) {
            super();
            this.id = id;
            this.count = count;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public synchronized void print(int num) {
            for (int i = 0; i < count; i++) {
                System.out.println("Thread " + id + ":" + num++);
            }
        }

    }

}