package utility;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MyDLLTests {

  private MyDLL<Integer> dll = new MyDLL<>();

  @BeforeEach
  void setUp() {
    dll.add(1);
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void size() {
    assertEquals(1, dll.size());

    dll.add(2);
    assertEquals(2, dll.size());
  }

  @Test
  void clear() {
    dll.clear();
    assertEquals(0, dll.size());

    dll.add(1);
    dll.add(2);
    dll.clear();
    assertEquals(0, dll.size());
  }

  @Test
  void add() {
    dll.add(2);
    assertEquals(2, dll.get(1));

    dll.add(1, 3);
    assertEquals(3, dll.get(1));

    dll.add(0, 4);
    assertEquals(4, dll.get(0));

    assertThrows(IndexOutOfBoundsException.class, () -> dll.add(4, 5));
    assertThrows(NullPointerException.class, () -> dll.add(null));
  }

  @Test
  void addAll() {
    MyDLL<Integer> dll2 = new MyDLL<>();
    dll2.add(2);
    dll2.add(3);
    dll.addAll(dll2);
    assertEquals(3, dll.size());
    assertEquals(2, dll.get(1));

    assertThrows(NullPointerException.class, () -> dll.addAll(null));
  }

  @Test
  void get() {
    assertEquals(1, dll.get(0));
    assertThrows(IndexOutOfBoundsException.class, () -> dll.get(1));
  }

  @Test
  void remove() {
    assertEquals(1, dll.remove(0));
    assertEquals(0, dll.size());

    dll.add(1);
    dll.add(2);
    assertEquals(1, dll.remove(1));
    assertEquals(1, dll.size());

    assertThrows(IndexOutOfBoundsException.class, () -> dll.remove(1));
  }

  @Test
  void set() {
    assertEquals(1, dll.set(0, 2));
    assertEquals(2, dll.get(0));

    assertThrows(IndexOutOfBoundsException.class, () -> dll.set(1, 2));
    assertThrows(NullPointerException.class, () -> dll.set(0, null));
  }

  @Test
  void isEmpty() {
    assertFalse(dll.isEmpty());

    dll.clear();
    assertTrue(dll.isEmpty());
  }

  @Test
  void contains() {
    assertTrue(dll.contains(1));
    assertFalse(dll.contains(2));
    assertThrows(NullPointerException.class, () -> dll.contains(null));
  }

  @Test
  void toArray() {
    Integer[] arr = {1};
    assertArrayEquals(arr, dll.toArray(new Integer[1]));

    dll.add(2);
    arr = new Integer[]{1, 2};
    assertArrayEquals(arr, dll.toArray());

    assertThrows(NullPointerException.class, () -> dll.toArray(null));
  }

  @Test
  void iterator() {
    assertNotNull(dll.iterator());

    var it = dll.iterator();
    assertTrue(it.hasNext());
    assertEquals(1, it.next());

    assertThrows(NoSuchElementException.class, () -> it.next());
  }
}