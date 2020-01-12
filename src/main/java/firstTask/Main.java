package firstTask;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        new Thread(new Task()).start();
    }

    public static class Task implements Runnable {

        public void run() {
            while (true) {
                System.out.println("5 seconds");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
