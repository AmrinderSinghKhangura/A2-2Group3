package utility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MyStackTests {
  MyStack<Integer> stack = new MyStack<>();

  @BeforeEach
  void setUp() {
    stack.push(1);
  }

  @Test
  void push() {
    assertEquals(1, stack.peek());

    stack.push(2);
    assertEquals(2, stack.peek());

    assertThrows(NullPointerException.class, () -> stack.push(null));
  }

  @Test
  void pop() {
    assertEquals(1, stack.pop());
    assertEquals(0, stack.size());

    assertThrows(EmptyStackException.class, () -> stack.pop());
  }

  @Test
  void peek() {
    assertEquals(1, stack.peek());

    stack.pop();
    assertThrows(EmptyStackException.class, () -> stack.peek());
  }

  @Test
  void clear() {
    stack.clear();
    assertEquals(0, stack.size());

    stack.push(1);
    stack.push(2);
    stack.push(3);
    stack.clear();
    assertEquals(0, stack.size());
  }

  @Test
  void isEmpty() {
    assertFalse(stack.isEmpty());

    stack.pop();
    assertTrue(stack.isEmpty());
  }

  @Test
  void toArray() {
    Integer[] arr = {1};
    Integer[] holder = new Integer[1];
    assertArrayEquals(arr, stack.toArray(holder));

    stack.push(2);
    stack.push(3);
    Integer[] arr2 = {1, 2, 3};
    assertArrayEquals(arr2, stack.toArray());

    assertThrows(NullPointerException.class, () -> stack.toArray(null));
  }

  @Test
  void contains() {
    assertTrue(stack.contains(1));
    assertFalse(stack.contains(2));

    stack.push(2);
    assertTrue(stack.contains(2));
    assertThrows(NullPointerException.class, () -> stack.contains(null));
  }

  @Test
  void search() {
    assertEquals(1, stack.search(1));

    stack.push(2);
    assertEquals(2, stack.search(2));
    assertEquals(-1, stack.search(3));
  }

  @Test
  void iterator() {
    assertNotNull(stack.iterator());

    var sIterator = stack.iterator();
    assertTrue(sIterator.hasNext());
    assertEquals(1, sIterator.next());

    assertThrows(NoSuchElementException.class, () -> sIterator.next());
  }

  @Test
  void testEquals() {
    MyStack<Integer> stack2 = new MyStack<>();
    stack2.push(1);
    assertTrue(stack.equals(stack2));

  }

  @Test
  void size() {
    assertEquals(1, stack.size());

    stack.push(2);
    assertEquals(2, stack.size());

    stack.clear();
    assertEquals(0, stack.size());
  }
}