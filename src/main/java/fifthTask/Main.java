package fifthTask;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    private static List<String> list = new ArrayList<>();
    private static final List<Queue<String>> listOfQueues = new ArrayList<>();
    private static Random random = new Random();
    private static Queue<String> queue;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        for (int i = 0; i < 10; i++) {
            listOfQueues.add(generateQueue());
        }

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.submit(new Serve());
        }

        TimeUnit.SECONDS.sleep(1);
        System.out.println("Count of usernames is: " + list.size());

        executorService.shutdown();

//        long before = System.nanoTime();
//        List<String> list = future.get();
//        long after = System.nanoTime();
//        System.out.println("Time of executing is: " + (after - before) + " nanoseconds");
    }

    private static synchronized Queue<String> getRandomQueue(List<Queue<String>> queueList) {
        int numberOfQueue = random.nextInt(10);
        Queue<String> queue = listOfQueues.get(numberOfQueue);
        if (!queue.isEmpty()) {
            return queue;
        }
        return getRandomQueue(listOfQueues);
    }

    private static Queue<String> generateQueue() {
        Queue<String> queue = new LinkedList<>();
        int numberOfQueue = random.nextInt(100);
        for (int i = 0; i < 10; i++) {
            queue.add("user username" + (i + 1) + "(queue" + numberOfQueue + ")");
        }
        return queue;
    }

    public static class Serve implements Runnable {
        final Queue<String> randomQueue = getRandomQueue(listOfQueues);

        @Override
        public void run() {

            while (!randomQueue.isEmpty()) {
                System.out.println(randomQueue.element() + " served by thread: " + Thread.currentThread().getId());
                synchronized (randomQueue){
                    list.add(randomQueue.poll());
                }
            }
        }
    }
}