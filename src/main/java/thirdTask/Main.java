package thirdTask;

import java.util.*;
import java.util.concurrent.*;

public class Main {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Queue<String>> listOfQueues = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listOfQueues.add(generateQueue());
        }

        long before = System.currentTimeMillis();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<List<String>> future = executorService.submit(new Serve(listOfQueues));
        List<String> listOfUserNames = future.get();
        long after = System.currentTimeMillis();
        System.out.println("Time of executing is: " + (after - before) + " milliseconds");
        System.out.println("Count of usernames is: " + listOfUserNames.size());

        ExecutorService executorService1 = Executors.newFixedThreadPool(10);
        executorService1.submit(new Serve(listOfQueues));

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

            for (int i = 0; i < queueList.size(); i++) {
                while (!queueList.get(i).isEmpty()) {
                    System.out.println(queueList.get(i).element() + " served by thread: " + Thread.currentThread().getId());
                    list.add(queueList.get(i).poll());
                }
            }
            return list;
        }
    }
}
