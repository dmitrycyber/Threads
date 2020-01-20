package fifthTask;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Container {
    private List<Queue<String>> queueList;
    private Random random = new Random();

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

    public Queue<String> getRandomQueue(){
        return queueList.get(random.nextInt(10));
    }

    public boolean isQueuesEmpty(){
        for (Queue<String> queue : queueList) {
            if (!queue.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
