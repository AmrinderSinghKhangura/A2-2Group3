package utility;

import Interfaces.Iterator;
import Interfaces.ListADT;

import java.util.Arrays;
import java.util.NoSuchElementException;
/**
 *
 * @author anna
 */
public class MyArrayList<E> implements ListADT<E> {
//Declare Default Capacity of the Array
  private static final int DEFAULT_CAPACITY = 10;
    //Declare the Array
  private E[] elements;
    //Declare the Size of the Array
  private int size;

  /**
   * Parameterless Constructor
   * precondition: none
   * postcondition: creates an array of default capacity and sets the size to 0
   *
   */
  public MyArrayList() {
    elements = (E[]) new Object[DEFAULT_CAPACITY];
    size = 0;
  }
  /**
   * Parameterless Constructor
   * precondition: none
   * postcondition: creates an array of defined capacity and sets size to 0
   *
   */

    public MyArrayList(int initialCapacity) {
        elements = (E[]) new Object[initialCapacity];
        size = 0;
  }
    //Private helper methods
  /**
   * gets the index of the first occurence of the specified element in this list
   * precondition: none
   * postcondition: returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element
   *
   */
    //
    private int indexOf(E toFind) {
      for (int i = 0; i < size; i++) {
        if (toFind.equals(elements[i])) {
          return i;
        }
      }
      return -1;
    }
    /**
     * increases the capacity of the array, if necessary, to ensure that it can hold at least the number of elements specified by the minimum capacity argument
     * precondition: none
     * postcondition: increases the capacity of the array, if necessary, to ensure that it can hold at least the number of elements specified by the minimum capacity argument
     *
     */
  private void ensureCapacity() {
    if (size == elements.length) {
      elements = Arrays.copyOf(elements, size * 2);
    }
  }
  /**
   * returns the size of the array
   * precondition: none
   * postcondition: returns the size of the array
   * */

  @Override
  public int size() {
    return size;
  }
    /**
     * clears the array
     * precondition: none
     * postcondition: clears the array
     * */
  @Override
  public void clear() {
    for (int i = 0; i < size; i++) {
      elements[i] = null;
    }
    size = 0;
  }
    /**
     * adds the specified element at the specified index
     * precondition: none
     * postcondition: adds the specified element at the specified index
     * @throws NullPointerException if the element to add is null
     * @throws IndexOutOfBoundsException if the index is out of range
     * */
  @Override
  public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
    if(toAdd == null) {
      throw new NullPointerException("Cannot add a null element to the list.");
    }
    if(index < 0 || index > size) {
      throw new IndexOutOfBoundsException("Index is out of range: " + index);
    }
    ensureCapacity();
    System.arraycopy(elements, index, elements, index + 1, size - index);
    elements[index] = toAdd;
    size++;
    return true;
  }
    /**
     * adds the specified element to the array
     * precondition: none
     * postcondition: adds the specified element to the array
     * @throws  NullPointerException if the element to add is null
     * @throws IndexOutOfBoundsException if the index is out of range
     * */
  @Override
  public boolean add(E toAdd) throws NullPointerException, IndexOutOfBoundsException {
    if(toAdd == null) {
      throw new NullPointerException("Cannot add a null element to the list.");
    }

    ensureCapacity();
    elements[size++] = toAdd;
    return true;
  }
    /**
     * adds all the elements in the specified list to the array
     * precondition: none
     * postcondition: adds all the elements in the specified list to the array
     * @throws NullPointerException if the element to add is null
     * */
  @Override
  public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
    if(toAdd == null) {
      throw new NullPointerException("Cannot add a null element to the list.");
    }
    boolean modified = false;
    ensureCapacity();
    Iterator<?extends E> it = toAdd.iterator();
    while(it.hasNext()) {
      add(it.next());
      modified = true;
    }
    return modified;

  }
    /**
     * gets the element at the specified index
     * precondition: elements in the array
     * postcondition: gets the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     * */
  @Override
  public E get(int index) throws IndexOutOfBoundsException {
    if(index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index is out of range: " + index);
    }
    return elements[index];
  }
    /**
     * removes the element at the specified index
     * precondition: elements in the array
     * postcondition: removes the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     * */
  @Override
  public E remove(int index) throws IndexOutOfBoundsException {
    if(index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index is out of range: " + index);
    }
    E oldValue = elements[index];
    int numMoved = size - index - 1;
    if(numMoved > 0) {
      System.arraycopy(elements, index + 1, elements, index, numMoved);
    }
    elements[--size] = null; // clear to let GC do its work
    return oldValue;
  }
    /**
     * removes the first occurrence of the specified element from the array
     * precondition: elements in the array
     * postcondition: removes the first occurrence of the specified element from the array
     * @throws NullPointerException if the element to remove is null
     * */

  @Override
  public E remove(E toRemove) throws NullPointerException {
    if(toRemove == null) {
      throw new NullPointerException("Cannot remove a null element from the list.");
    }
    for(int index = 0; index < size; index++) {
      if(toRemove.equals(elements[index])) {
        remove(index);
        return toRemove;
      }
    }
    return null;
  }
    /**
     * sets the element at the specified index to the specified element
     * precondition: elements in the array
     * postcondition: sets the element at the specified index to the specified element
     * @throws NullPointerException if the element to set is null
     * @throws IndexOutOfBoundsException if the index is out of range
     * */
  @Override
  public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
    if(toChange == null) {
      throw new NullPointerException("Cannot set a null element in the list.");
    }
    if(index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index is out of range: " + index);
    }
    E oldValue = elements[index];
    elements[index] = toChange;
    return oldValue;
  }
    /**
     * checks if the array is empty
     * precondition: none
     * postcondition: returns true if the array is empty, false otherwise
     * */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }
    /**
     * checks if the array contains the specified element
     * precondition: none
     * postcondition: returns true if the array contains the specified element, false otherwise
     * @throws NullPointerException if the element to find is null
     * */
  @Override
  public boolean contains(E toFind) throws NullPointerException {
    if(toFind == null) {
      throw new NullPointerException("Cannot find a null element in the list.");
    }
    return indexOf(toFind) >= 0;
  }
    /**
     * searches for the specified element in the array
     * precondition: none
     * postcondition: returns the index of the first occurrence of the specified element in the array, or -1 if the array does not contain the element
     * @throws NullPointerException if the element to find is null
     * */
  @Override
  public E[] toArray(E[] toHold) throws NullPointerException {
    if (toHold == null) throw new NullPointerException("The array provided cannot be null");
    if (toHold.length < size) {
      return Arrays.copyOf(elements, size, (Class<E[]>) toHold.getClass());
    }
    System.arraycopy(elements, 0, toHold, 0, size);
    if (toHold.length > size) {
      toHold[size] = null;
    }
    return toHold;
  }

  /**
   * Returns an array containing all of the elements in this list in proper sequence (from first to last element).
   * precondition: none
   * postcondition: returns an array containing all of the elements in this list in proper sequence (from first to last element)
   * */
  @Override
  public Object[] toArray() {
    return Arrays.copyOf(elements, size);
  }
    /**
     * returns an iterator over the elements in this list in proper sequence
     * precondition: none
     * postcondition: returns an iterator over the elements in this list in proper sequence
     * */
  @Override
  public Iterator<E> iterator() {
    return new Iterator<E>() {
      private int currentIndex = 0;

      @Override
      public boolean hasNext() {
        return currentIndex < size;
      }

      @Override
      public E next() {
        if (!hasNext()) throw new NoSuchElementException();
        return elements[currentIndex++];
      }
    };
  }

}
