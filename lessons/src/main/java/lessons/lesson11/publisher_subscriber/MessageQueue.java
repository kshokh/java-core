package lessons.lesson11.publisher_subscriber;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import lombok.Getter;

@Getter
public class MessageQueue {
    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
}
