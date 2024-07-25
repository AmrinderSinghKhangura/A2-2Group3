package utility;

import Exceptions.EmptyQueueException;
import Interfaces.Iterator;
import Interfaces.QueueADT;

public class MyQueue<E> implements QueueADT<E>
{
  private MyDLL<E> data;
  private int front;
  private int capacity;
  private int length;

  /**
   * This is the parametered constructor for the MyQueue class with a given capacity
   * @param capacity
   */
  public MyQueue(int capacity)
  {
    this.capacity = capacity;
    data = new MyDLL<>();
    length = 0;
    front = -1;
  }

  /**
   * This is the default constructor for the MyQueue class with a capacity of 100
   */
  public MyQueue()
  {
    this.capacity = 100;
    data = new MyDLL<>();
    length = 0;
    front = -1;
  }

  /**
   * This is the getLength method for the MyQueue class
   * @return length
   */
  public int getLength()
  {
    return length;
  }

  /**
   * This is the getCapacity method for the MyQueue class
   * @return capacity
   */
  public int getCapacity()
  {
    return capacity;
  }

  /**
   * enqueue method to add an element to the queue
   * @param toAdd element to add
   * @return front
   */
  @Override
  public void enqueue(E toAdd) throws NullPointerException
  {
    if(isEmpty())
    {
      front++;
    }
    if(toAdd == null)
    {
      throw new NullPointerException();
    }
    if(isFull())
    {
//      throw new FullQueueException();
    }
    data.add(toAdd);
    length++;
  }

  /**
   * dequeue method to remove an element from the front of the queue
   * @return element removed
   */
  @Override
  public E dequeue() throws EmptyQueueException
  {
    if(isEmpty())
    {
      throw new EmptyQueueException();
    }

    length--;
    return data.remove(front);
  }

  @Override
  public E peek() throws EmptyQueueException
  {
    if(isEmpty())
    {
      throw new EmptyQueueException();
    }
    return data.get(front);
  }

  @Override
  public void dequeueAll()
  {
    data.clear();
    length = 0;
    front = -1;
  }

  @Override
  public boolean isEmpty()
  {
    return length == 0;
  }

  @Override
  public Iterator<E> iterator()
  {
    return data.iterator();

  }

  @Override
  public boolean equals(QueueADT<E> that)
  {
    MyQueue<E> queue = (MyQueue<E>) that;
    Iterator<E> iterator1 = this.iterator();
    Iterator<E> iterator2 = queue.iterator();

    if(this.size() != that.size())
    {
      return false;
    }

    while(iterator1.hasNext() && iterator2.hasNext())
    {
      if(!iterator1.next().equals(iterator2.next()))
      {
        return false;
      }
    }

    return true;
  }

  @Override
  public Object[] toArray()
  {
    return data.toArray();
  }

  @Override
  public Object[] toArray(Object[] holder) throws NullPointerException
  {
    return data.toArray((E[]) holder);
  }

  @Override
  public boolean isFull()
  {
    return length == capacity;
  }

  @Override
  public int size()
  {
    return this.length;
  }
}
