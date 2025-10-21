package lessons.lesson11;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue();

        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(new Publisher(queue));
        executor.submit(new Subscriber(queue));

        executor.shutdown();
    }
}
