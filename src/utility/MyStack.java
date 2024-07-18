package utility;

import Interfaces.Iterator;
import Interfaces.StackADT;

import java.util.EmptyStackException;

public class MyStack<E> implements StackADT<E> {
  @Override
  public void push(E toAdd) throws NullPointerException {

  }

  @Override
  public E pop() throws EmptyStackException {
    return null;
  }

  @Override
  public E peek() throws EmptyStackException {
    return null;
  }

  @Override
  public void clear() {

  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public Object[] toArray() {
    return new Object[0];
  }

  @Override
  public E[] toArray(E[] holder) throws NullPointerException {
    return null;
  }

  @Override
  public boolean contains(E toFind) throws NullPointerException {
    return false;
  }

  @Override
  public int search(E toFind) {
    return 0;
  }

  @Override
  public Iterator<E> iterator() {
    return null;
  }

  @Override
  public boolean equals(StackADT<E> that) {
    return false;
  }

  @Override
  public int size() {
    return 0;
  }
}
