package tests.priority_queue;

import fhv.at.mwi.priority_queue.LeftistHeap;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class LeftistHeapTest {

    private LeftistHeap<Integer> pq = new LeftistHeap<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return (o1 - o2);
        }
    });

    @Test
    void enqueueValues() {
        pq.enqueue(100);
        pq.enqueue(1);
        pq.enqueue(50);
        pq.enqueue(200);
        assertEquals(200, (int)pq.dequeue());
    }

    @Test
    void dequeueWhenQueueIsEmpty() {
        while(!pq.isEmpty()) {
            pq.dequeue();
        }
        assertEquals(null, pq.dequeue());
    }
    @Test
    void dequeueWhenQueueMaxPriority() {
        pq.enqueue(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, (int)pq.dequeue());
    }

    @Test
    void isEmptyWhenQueueIsFull() {
        pq.enqueue(0);
        assertEquals(false, pq.isEmpty());
    }

    @Test
    void isEmptyWhenQueueIsEmpty() {
        while(!pq.isEmpty()) {
            pq.dequeue();
        }
        assertEquals(true, pq.isEmpty());
    }

}