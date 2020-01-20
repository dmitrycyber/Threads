package reworkFifthTask;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;

public class Task implements Callable<Integer> {
    private List<Queue<String>> queueList;
    private List<String> userNameList;
    private Container container;
    private Queue<String> queue;

    public Task(Container container, List<String> userNameList) {
        this.container = container;
        this.queueList = container.getQueueList();
        this.userNameList = userNameList;
        queue = container.getRandomQueue();
    }

    @Override
    public Integer call() throws Exception {
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
        return null;
    }
}
