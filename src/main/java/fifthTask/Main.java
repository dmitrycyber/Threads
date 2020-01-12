package fifthTask;

import java.util.*;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Queue<String>> listOfQueues = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listOfQueues.add(generateQueue());
        }

        long before = System.nanoTime();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<List<String>> future = executorService.submit(new Serve(listOfQueues));
        List<String> listOfUserNames = future.get();
        long after = System.nanoTime();
        System.out.println("Time of executing is: " + (after - before) + " nanoseconds");
        System.out.println("Count of usernames is: " + listOfUserNames.size());

        executorService.shutdown();
    }

    private static Queue<String> generateQueue() {
        Queue<String> queue = new LinkedList<>();
        Random random = new Random();
        Integer numberOfQueue = random.nextInt(100);
        for (int i = 0; i < 100_000; i++) {
            queue.add("user username" + (i + 1) + "(queue" + numberOfQueue + ")");
        }
        return queue;
    }

    public static class Serve implements Callable<List<String>> {
        private List<Queue<String>> queueList;

        public Serve(List<Queue<String>> queueList) {
            this.queueList = queueList;
        }

        @Override
        public List<String> call() throws Exception {
            List<String> list = new ArrayList<>();
            Random random = new Random();
            Integer numberOfQueue = random.nextInt(10);
            while (!queueList.get(numberOfQueue).isEmpty()) {
                if (queueList.get(numberOfQueue).isEmpty()) {
                    numberOfQueue = random.nextInt(10);
                }
                System.out.println(queueList.get(numberOfQueue).element() + " served by thread: " + Thread.currentThread().getId());
                list.add(queueList.get(numberOfQueue).poll());
            }
            return list;
        }
    }
}
