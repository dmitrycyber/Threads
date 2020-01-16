package reworkFifthTask;


import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Container container = new Container();
        List<String> userNameList = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.submit(new Task(container, userNameList));
        }
        sleep(4000);
        System.out.println("Count of usernames is: " + userNameList.size());
    }

    private static void sleep(int millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
