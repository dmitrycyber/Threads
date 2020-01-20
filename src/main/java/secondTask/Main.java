package secondTask;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {
    public static Queue<String> queue = new LinkedList();

    public static void main(String[] args) {
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();

        sleep(1);

        System.out.println("Queue size is: " + queue.size());

    }


    public static class Producer implements Runnable {
        public void run() {
            for (int i = 0; i < 100; i++) {
                queue.add("Message " + (i + 1));
            }
        }
    }

    public static class Consumer implements Runnable {

        public void run() {
            while (!queue.isEmpty()) {
                System.out.println(queue.poll());
            }
        }
    }

    public static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
