package sixthTask;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class Main {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(6);

    public static void main(String[] args) {
        while (true) {
            new Thread(new goPoker(cyclicBarrier)).start();
            sleep(1);
        }
    }

    public static class goPoker implements Runnable {
        CyclicBarrier cyclicBarrier;

        public goPoker(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Start the game!");
        }
    }

    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
