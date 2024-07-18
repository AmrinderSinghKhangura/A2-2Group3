package utility;

import Interfaces.Iterator;
import Interfaces.QueueADT;

public class MyQueue<E> implements QueueADT<E>{
  @Override
  public void enqueue(E toAdd) throws NullPointerException {

  }

  @Override
  public E dequeue() throws EmptyQueueException {
    return null;
  }

  @Override
  public E peek() throws EmptyQueueException {
    return null;
  }

  @Override
  public void dequeueAll() {

  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public Iterator<E> iterator() {
    return null;
  }

  @Override
  public boolean equals(QueueADT<E> that) {
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
  public boolean isFull() {
    return false;
  }

  @Override
  public int size() {
    return 0;
  }
}
