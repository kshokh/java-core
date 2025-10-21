package lessons.lesson11.simplethreadpool;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.BlockingQueue;

@RequiredArgsConstructor
public class WorkerThread extends Thread {
    private final BlockingQueue<Runnable> taskQueue;
    private volatile boolean running = true;

    @Override
    public void run() {
        while (running) {
            try {
                Runnable task = taskQueue.take();
                task.run();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread().getName() + " interrupted.");
            }
        }
    }

    public void stopWorker() {
        running = false;
        this.interrupt();
    }
}
