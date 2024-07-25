package utility;

import Interfaces.Iterator;
import Interfaces.StackADT;

import java.util.EmptyStackException;

public class MyStack<E> implements StackADT<E> {
  private MyArrayList<E> data;
  private int top;
  private int size;
  //Constructor with Size Parameter
  public MyStack(int size) {
    this.size = size;
    data = new MyArrayList<E>(size);
    top = -1;
  }
  // Parameterless Constructor
  public MyStack() {
    data = new MyArrayList<E>();
    top = -1;
  }

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

  @Override
  public E peek() throws EmptyStackException {
    if (top == -1) {
      throw new EmptyStackException();
    } else {
      return data.get(top);
    }
  }

  @Override
  public void clear() {
    data.clear();
  }

  @Override
  public boolean isEmpty() {
    return top == -1;
  }

  @Override
  public Object[] toArray() {
    Object[] holder = new Object[data.size()];
    for (int i = 0; i < data.size(); i++) {
      holder[i] = data.get(i);
    }
    return holder;
  }

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

  @Override
  public Iterator<E> iterator() {
    return data.iterator();
  }

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

  @Override
  public int size() {
    return data.size();
  }
}
