package reworkFifthTask;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Container {
    private List<Queue<String>> queueList;
    Random random = new Random();

    public Container(){
        queueList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            queueList.add(generateQueue());
        }
    }

    private Queue<String> generateQueue() {
        Queue<String> queue = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < 100_000; i++) {
            queue.add("user" + i + "queue" + random.nextInt(100));
        }
        return queue;
    }

    public List<Queue<String>> getQueueList() {
        return queueList;
    }

    public Queue<String> getRandomQueue(){
        Queue<String> queue = queueList.get(random.nextInt(10));
        return queue;
    }

    public boolean isQueuesEmpty(){
        for (int i = 0; i < queueList.size(); i++) {
            if (!queueList.get(i).isEmpty()){
                return false;
            }
        }
        return true;
    }
}
