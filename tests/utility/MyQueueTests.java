package utility;

import Exceptions.EmptyQueueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MyQueueTests {

  MyQueue<Integer> queue = new MyQueue<>();

  @BeforeEach
  void setUp() {
    queue.enqueue(1);
  }

  @Test
  void enqueue() throws EmptyQueueException, NullPointerException {
    queue.enqueue(2);
    assertEquals(2, queue.size());
    assertEquals(1, queue.peek());

    assertThrows(NullPointerException.class, () -> queue.enqueue(null));
  }

  @Test
  void dequeue() throws EmptyQueueException {
    assertEquals(1, queue.dequeue());
    assertEquals(0, queue.size());

    assertThrows(EmptyQueueException.class, () -> queue.dequeue());
  }

  @Test
  void peek() throws EmptyQueueException {
    assertEquals(1, queue.peek());

    queue.dequeue();
    assertThrows(EmptyQueueException.class, () -> queue.peek());

  }

  @Test
  void dequeueAll() {
    queue.dequeueAll();
    assertEquals(0, queue.size());

    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);
    queue.dequeueAll();
    assertEquals(0, queue.size());
  }

  @Test
  void isEmpty() throws EmptyQueueException {
    assertFalse(queue.isEmpty());

    queue.dequeue();
    assertTrue(queue.isEmpty());
  }

  @Test
  void iterator() {
    assertNotNull(queue.iterator());

    var qIterator = queue.iterator();
    assertTrue(qIterator.hasNext());
    assertEquals(1, qIterator.next());

    assertThrows(NoSuchElementException.class, () -> qIterator.next());
  }

  @Test
  void testEquals() {
    MyQueue<Integer> queue2 = new MyQueue<>();
    queue2.enqueue(1);
    assertTrue(queue.equals(queue2));
  }

  @Test
  void toArray() {
    Integer[] arr = {1};
    assertArrayEquals(arr, queue.toArray(new Integer[1]));
  }

  @Test
  void isFull() {
    // need to implement later as I am not sure the logic as we have no size limit currently
  }

  @Test
  void size() {
    assertEquals(1, queue.size());

    queue.enqueue(2);
    assertEquals(2, queue.size());

    queue.dequeueAll();
    assertEquals(0, queue.size());
  }
}