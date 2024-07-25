package utility;

import Interfaces.Iterator;
import Interfaces.StackADT;

import java.util.EmptyStackException;
/**
 * This class is a custom implementation of the StackADT interface.
 * It uses an instance of MyArrayList to store the elements of the stack.
 * @param <E> The type of elements stored in the stack.
 */
public class MyStack<E> implements StackADT<E> {
  private MyArrayList<E> data;
  private int top;
  private int size;
  /**
   * Parameterized Constructor
   * @param size The size of the stack.
   */
  public MyStack(int size) {
    this.size = size;
    data = new MyArrayList<E>(size);
    top = -1;
  }
  /**
   * Parameterless Constructor
   */
  // Parameterless Constructor
  public MyStack() {
    data = new MyArrayList<E>();
    top = -1;
  }
  /**
   * Adds an element to the top of the stack.
   * @param toAdd The element to add to the stack.
   * @throws NullPointerException if the element to add is null.
   */
  @Override
  public void push(E toAdd) throws NullPointerException {
    if (toAdd == null) {
      throw new NullPointerException("Cannot add a null element to the list.");
    }
   else {
      top++;
      if (data.size() > top) {
        data.set(top, toAdd);
      } else {
        data.add(toAdd);
      }
    }
  }
    /**
     * Removes and returns the element at the top of the stack.
     * @return The element at the top of the stack.
     * @throws EmptyStackException if the stack is empty.
     */
  @Override
  public E pop() throws EmptyStackException {
    if (top == -1) {
      throw new EmptyStackException();
    } else {
      E temp = data.get(top);
      data.remove(top);
      top = top-1;
      return temp;
    }
  }
    /**
     * Returns the element at the top of the stack without removing it.
     * @return The element at the top of the stack.
     * @throws EmptyStackException if the stack is empty.
     */
  @Override
  public E peek() throws EmptyStackException {
    if (top == -1) {
      throw new EmptyStackException();
    } else {
      return data.get(top);
    }
  }
    /**
     * Removes all elements from the stack.
     */
  @Override
  public void clear() {
    data.clear();
  }
    /**
     * Returns true if the stack is empty.
     * @return True if the stack is empty.
     */
  @Override
  public boolean isEmpty() {
    return top == -1;
  }
    /**
     * Returns an array containing all elements in the stack.
     * @return An array containing all elements in the stack.
     */
  @Override
  public Object[] toArray() {
    Object[] holder = new Object[data.size()];
    for (int i = 0; i < data.size(); i++) {
      holder[i] = data.get(i);
    }
    return holder;
  }
    /**
     * Returns an array containing all elements in the stack.
     * @param holder The array to fill with elements from the stack.
     * @return An array containing all elements in the stack.
     * @throws NullPointerException if the array to fill is null.
     */
  @Override
  public E[] toArray(E[] holder) throws NullPointerException {
    if (holder == null) {
      throw new NullPointerException("Cannot convert to an array of null.");
    }
    if (holder.length < data.size()) {
      holder = (E[]) new Object[data.size()];
    }
    for (int i = 0; i < data.size(); i++) {
      holder[i] = data.get(i);
    }
    return holder;
  }
    /**
     * Returns true if the stack contains the specified element.
     * @param toFind The element to find in the stack.
     * @return True if the stack contains the specified element.
     * @throws NullPointerException if the element to find is null.
     */
  @Override
  public boolean contains(E toFind) throws NullPointerException {
    if (toFind == null) {
      throw new NullPointerException("Cannot find a null element in the list.");
    }
    for (int i = 0; i < data.size(); i++) {
      if (data.get(i).equals(toFind)) {
        return true;
      }
    }
    return false;
  }
    /**
     * Searches for the specified element in the stack.
     * @param toFind The element to find in the stack.
     * @return The index of the first occurrence of the specified element in the stack, or -1 if the stack does not contain the element.
     * @throws NullPointerException if the element to find is null.
     */
  @Override
  public int search(E toFind) {
    if (toFind == null) {
      throw new NullPointerException("Cannot find a null element in the list.");
    }
    for (int i = 0; i < data.size(); i++) {
      if (data.get(i).equals(toFind)) {
        return i+1;
      }
    }
    return -1;
  }
    /**
     * Returns an iterator over the elements in the stack in proper sequence.
     * @return An iterator over the elements in the stack in proper sequence.
     */
  @Override
  public Iterator<E> iterator() {
    return data.iterator();
  }
    /**
     * Compares the stack to the specified stack for equality.
     * @param that The stack to compare to this stack.
     * @return True if the stacks are equal.
     */
  @Override
  public boolean equals(StackADT<E> that) {
    if (that == null) {
      return false;
    }
    if (this.size() != that.size()) {
      return false;
    }
    Iterator<E> iterator1 = this.iterator();
    Iterator<E> iterator2 = that.iterator();
    while (iterator1.hasNext() && iterator2.hasNext()) {
      if (!iterator1.next().equals(iterator2.next())) {
        return false;
      }
    }
    return true;
  }
    /**
     * Returns the number of elements in the stack.
     * @return The number of elements in the stack.
     */
  @Override
  public int size() {
    return data.size();
  }
}
