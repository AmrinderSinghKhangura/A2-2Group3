package utility;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTests {

  private MyArrayList<Integer> list = new MyArrayList<>();

  @BeforeEach
  void setUp() {
    list.add(1);
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void size() {
    assertEquals(1, list.size());

    list.add(2);
    assertEquals(2, list.size());
  }

  @Test
  void clear() {
    list.clear();
    assertEquals(0, list.size());

    list.add(1);
    list.add(2);
    list.add(3);
    list.clear();
    assertEquals(0, list.size());

    list.clear();
    assertEquals(0, list.size());
  }

  @Test
  void add() {
    list.add(2);
    assertEquals(2, list.size());

    list.add(2, 3);
    assertEquals(3, list.size());
    assertEquals(3, list.get(2));

    list.add(0, 4);
    assertEquals(4, list.size());
    assertEquals(4, list.get(0));

    assertThrows(IndexOutOfBoundsException.class, () -> list.add(5, 5));
    assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 5));
    assertThrows(NullPointerException.class, () -> list.add(null));
  }

  @Test
  void addAll() {
    MyArrayList<Integer> list2 = new MyArrayList<>();
    list2.add(2);
    list2.add(3);
    list2.add(4);
    list.addAll(list2);
    assertEquals(4, list.size());
    assertEquals(2, list.get(1));
    assertEquals(3, list.get(2));
    assertEquals(4, list.get(3));

    assertThrows(NullPointerException.class, () -> list.addAll(null));
  }

  @Test
  void get() {
    assertEquals(1, list.get(0));

    assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
  }

  @Test
  void remove() {
    list.remove(0);
    assertEquals(0, list.size());

    list.add(1);
    list.add(2);
    list.remove(0);
    assertEquals(1, list.size());

    assertEquals(2, list.get(0));

    assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
    assertThrows(NullPointerException.class, () -> list.remove(null));
  }

  @Test
  void set() {
    list.set(0, 2);
    assertEquals(2, list.get(0));

    assertThrows(IndexOutOfBoundsException.class, () -> list.set(43, 1));
    assertThrows(NullPointerException.class, () -> list.set(0, null));
  }

  @Test
  void isEmpty() {
    assertFalse(list.isEmpty());

    list.clear();
    assertTrue(list.isEmpty());
  }

  @Test
  void contains() {
    assertTrue(list.contains(1));
    assertFalse(list.contains(2));
    assertThrows(NullPointerException.class, () -> list.contains(null));
  }

  @Test
  void toArray() {
    Integer[] arr = new Integer[1];
    arr[0] = 1;
    assertArrayEquals(arr, list.toArray(arr));
    assertThrows(NullPointerException.class, () -> list.toArray(null));

    list.add(2);
    list.add(3);
    Integer[] arr2 = {1, 2, 3};
    assertArrayEquals(arr2, list.toArray());
  }

  @Test
  void iterator() {
    assertNotNull(list.iterator());
    var it = list.iterator();
    assertTrue(it.hasNext());
    assertEquals(1, it.next());
    assertThrows(NoSuchElementException.class, () -> it.next());
  }
}