package reworkFifthTask;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Container {
    private List<Queue<String>> queueList;
    Random random = new Random();

    public Container(){
        queueList = new ArrayList<>();
        queueList.add(generateQueue());
        for (int i = 0; i < 10; i++) {
            queueList.add(generateQueue());
        }
    }

    private Queue<String> generateQueue() {
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < 100_000; i++) {
            queue.add("queue" + i);
        }
        return queue;
    }

    public List<Queue<String>> getQueueList() {
        return queueList;
    }

    public Queue<String> getRandomQueue(){
        Queue<String> queue = queueList.get(random.nextInt(10));
        if (queue.isEmpty()){

        }
        return queue;
    }
}
