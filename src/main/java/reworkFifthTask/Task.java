package reworkFifthTask;

import java.util.List;
import java.util.Queue;

public class Task implements Runnable {
    List<Queue<String>> queueList;
    List<String> userNameList;
    Container container;
    Queue<String> queue;

    public Task(Container container, List<String> userNameList) {
        this.container = container;
        this.queueList = container.getQueueList();
        this.userNameList = userNameList;
        queue = container.getRandomQueue();
    }

    @Override
    public void run() {
        while (!container.isQueuesEmpty()) {
            if (queue.isEmpty()) {
                queue = container.getRandomQueue();
            }
            String poll;
            synchronized (queue) {
                poll = queue.poll();
            }
            if (poll != null) {
                synchronized (userNameList){
                    userNameList.add(poll);
                }
                System.out.println(poll + "_done_by_thread_" + Thread.currentThread().getId());
            }
        }
    }
}
