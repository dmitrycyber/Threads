package reworkFifthTask;


import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Container container = new Container();
        List<String> userNameList = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Task> taskList = new ArrayList<>();

        long before = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            taskList.add(new Task(container, userNameList));
        }
        executorService.invokeAll(taskList);
        long after = System.currentTimeMillis();

        System.out.println("Count of usernames is: " + userNameList.size());
        System.out.println("Time of executing is: " + (after - before) + " millis");

        executorService.shutdown();
    }
}
