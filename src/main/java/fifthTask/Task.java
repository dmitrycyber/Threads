package fifthTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;

public class Task implements Callable<List<String>> {
    private List<String> userNameList;
    private Container container;
    private Queue<String> queue;

    public Task(Container container) {
        this.container = container;
        userNameList = Collections.synchronizedList(new ArrayList<>());
        queue = container.getRandomQueue();
    }

    @Override
    public List<String> call() {
        while (!container.isQueuesEmpty()) {
            if (queue.isEmpty()) {
                queue = container.getRandomQueue();
            }
            String poll = queue.poll();
            if (poll != null) {
                userNameList.add(poll);
                System.out.println(poll + "_done_by_thread_" + Thread.currentThread().getId());
            }
        }
        return userNameList;
    }
}
