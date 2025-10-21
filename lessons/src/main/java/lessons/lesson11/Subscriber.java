package lessons.lesson11;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Subscriber implements Runnable {
    private final MessageQueue queue;

    @Override
    public void run() {
        try {
            while (true) {
                String message = queue.getQueue().take();
                if ("exit".equalsIgnoreCase(message)) {
                    break;
                }
                System.out.println("Subscriber received: " + message);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Subscriber interrupted.");
        }
    }
}
