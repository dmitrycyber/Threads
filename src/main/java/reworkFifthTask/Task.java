package reworkFifthTask;

import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Callable;

public class Task implements Runnable {
    List<Queue<String>> queueList;
    List<String> userNameList;
    Random random = new Random();
    Container container;
    Queue<String> queue;
    String username;

    public Task(Container container, List<String> userNameList) {
        this.container = container;
        this.queueList = container.getQueueList();
        this.userNameList = userNameList;
        queue = container.getRandomQueue();
    }

    @Override
    public void run() {
        String poll;
        synchronized (queue){
            username = queue.element();
            synchronized (username){
                System.out.println(queue.element() + "_done_by_thread_" + Thread.currentThread().getId());
                poll = queue.poll();
            }
        }

        System.out.println("TUT");

        userNameList.add(poll);


    }


//    @Override
//    public Integer call() throws Exception {
//        System.out.println("TUT");
//        synchronized (queue){
//            System.out.println(queue.element() + "_done_by_thread_" + Thread.currentThread().getId());
//            userNameList.add(queue.poll());
//        }
//        return null;
//    }
}
