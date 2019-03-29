package sedgewick.basic.problems.queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sedgewick.basic.ds.queue.Queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

class QueueUsingStacks_FastDequeueTest {
    @Test
    void testDequeueEmptyQueue() {
        Queue<Integer> queue = new QueueUsingStacks_FastDequeue<>();
        Assertions.assertThrows(NoSuchElementException.class, queue::dequeue);
    }

    @Test
    void testEnqueueSingle() {
        Queue<Integer> queue = new QueueUsingStacks_FastDequeue<>();
        queue.enqueue(1);
        Assertions.assertEquals(new Integer(1), queue.dequeue());
    }

    @Test
    void testEnqueueDouble() {
        Queue<Integer> queue = new QueueUsingStacks_FastDequeue<>();
        List<Integer> values = Arrays.asList(1, 2, 3);
        values.forEach(value -> {
            try {
                queue.enqueue(value);
            } catch (Exception ignore) {}
        });

        List<Integer> results = new ArrayList<>();
        while(!queue.isEmpty())
            results.add(queue.dequeue());

        Assertions.assertEquals(values, results);
    }
}