package reworkFifthTask;


import java.util.ArrayList;
import java.util.List;

import java.util.Queue;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Container container = new Container();
        List<String> userNameList = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Callable<Integer>> listOfTasks = new ArrayList<>();
        boolean isCanceled = true;


        while (isCanceled){

            if (userNameList.size() == 1000_000){
                isCanceled = false;
            }
            executorService.submit(new Task(container, userNameList));
            System.out.println("usersSize is " + userNameList.size());

        }

        //sleep(1);

        System.out.println(userNameList.size());

    }

    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
