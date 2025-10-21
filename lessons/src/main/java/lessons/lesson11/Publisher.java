package lessons.lesson11;

import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@RequiredArgsConstructor
public class Publisher implements Runnable {
    private final MessageQueue queue;
    private volatile boolean running = true;

    @Override
    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Words to publish ('exit' to quit):");
            while (running) {
                String input = scanner.nextLine();
                if ("exit".equalsIgnoreCase(input.trim())) {
                    running = false;
                    queue.getQueue().put("exit");
                } else {
                    queue.getQueue().put(input);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Publisher interrupted.");
        }
    }
}
