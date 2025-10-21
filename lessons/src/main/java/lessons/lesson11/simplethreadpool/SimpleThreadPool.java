package lessons.lesson11.simplethreadpool;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SimpleThreadPool {

    @Getter
    private final BlockingQueue<Runnable> taskQueue;
    private final List<WorkerThread> workers;
    private volatile boolean isRunning = true;

    public SimpleThreadPool(int threadCount) {
        this.taskQueue = new LinkedBlockingQueue<>();
        this.workers = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            WorkerThread worker = new WorkerThread(taskQueue);
            worker.setName("Worker-" + (i + 1));
            worker.start();
            workers.add(worker);
        }

        System.out.println("Thread pool initialized with " + threadCount + " threads.");
    }

    public void submit(Runnable task) {
        if (!isRunning) {
            throw new IllegalStateException("Thread pool is shutting down.");
        }
        taskQueue.offer(task);
    }

    public void shutdown() {
        System.out.println("Shutting down thread pool...");
        isRunning = false;

        for (WorkerThread worker : workers) {
            worker.stopWorker();
        }
        System.out.println("Thread pool shutdown complete.");
    }
}
