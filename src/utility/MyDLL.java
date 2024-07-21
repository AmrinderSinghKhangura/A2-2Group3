package utility;

import Interfaces.Iterator;
import Interfaces.ListADT;

import java.sql.Array;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class MyDLL<E> implements ListADT<E> {

  private E data;
  private Node head;
  private Node tail;
  private int size;

  public MyDLL()
  {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  @Override
  public int size()
  {
    return size;
  }

  @Override
  public void clear()
  {
    head = null;
    tail = null;
    size = 0;
  }

  @Override
  public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException
  {
    if(toAdd == null)
    {
      throw new NullPointerException();
    }

    if(index >= size || index < 0)
    {
      throw new IndexOutOfBoundsException();
    }

    Node n = new Node(toAdd);
    Node current = head;

    //If index is 0, add to the start of the list
    if(index == 0)
    {
      if(isEmpty())
      {
        tail = n;
        head = n;
      }
      else
      {
        head.prev = n;
        n.next = head;
        head = n;
      }
    }

    //If index is the size (end of the list) then add to the end of the list
    else if(index == size)
    {
      if(isEmpty())
      {
        head = n;
        tail = n;
      }
      else
      {
        add(toAdd);
        size--;
      }
    }

    else
    {
      for(int i = 0; i < index; i++)
      {
        current = current.next;
      }

      n.next = current;
      n.prev = current.prev;

      if(current.prev != null)
      {
        current.prev.next = n;
      }
      current.prev = n;
    }

    size++;
    return true;
  }

  @Override
  public boolean add(E toAdd) throws NullPointerException, IndexOutOfBoundsException
  {
    if(toAdd == null)
    {
      throw new NullPointerException();
    }

    Node n = new Node(toAdd);

    if(isEmpty())
    {
      head = n;
      tail = n;
    }
    else
    {
      tail.next = n;
      n.prev = tail;
      tail = n;
    }

    size++;
    return true;
  }

  @Override
  public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException
  {
    if(toAdd == null)
    {
      throw new NullPointerException();
    }

    Iterator<? extends E> i = toAdd.iterator();
    while (i.hasNext())
    {
      add(i.next());
    }

    return true;
  }

  @Override
  public E get(int index) throws IndexOutOfBoundsException
  {
    if(index >= size || index < 0)
    {
      throw new IndexOutOfBoundsException();
    }

    Node current = head;
    for(int i = 0; i < index; i++)
    {
      current = current.next;
    }

    return (E) current.data;
  }

  @Override
  public E remove(int index) throws IndexOutOfBoundsException
  {
    if(isEmpty())
    {
      throw new IndexOutOfBoundsException("List empty");
    }
    if(index >= size || index < 0)
    {
      throw new IndexOutOfBoundsException();
    }

    Node current = head;

    if(size == 1)
    {
      head = null;
      tail = null;
    }

    else if(index == 0)
    {
      head.next.prev = null;
      head = head.next;
    }
    else if(index == size - 1)
    {
      tail.prev.next = null;
      tail = tail.prev;
    }
    else
    {
      for(int i = 0; i < index; i++)
      {
        current = current.next;
      }
      current.prev.next = current.next;
      current.next.prev = current.prev;
    }

    size--;
    return (E) current.data;
  }

  @Override
  public E remove(E toRemove) throws NullPointerException
  {
    Node current = head;
    int index = 0;

    while(current != null)
    {
      if(current.data.equals(toRemove))
      {
        return remove(index);
      }
      current = current.next;
      index++;
    }
    return null;
  }

  @Override
  public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException
  {
    if(index >= size || index < 0)
    {
      throw new IndexOutOfBoundsException();
    }
    if(toChange == null)
    {
      throw new NullPointerException();
    }

    Node current = head;

    for (int i = 0; i < index; i++)
    {
      current = current.next;
    }
    E oldData = (E) current.data;
    current.data = toChange;

    return oldData;
  }

  @Override
  public boolean isEmpty()
  {
    return size == 0;
  }

  @Override
  public boolean contains(E toFind) throws NullPointerException
  {
    if(toFind == null)
    {
      throw new NullPointerException();
    }

    Node current = head;

    while(current != null)
    {
      if(current.data.equals(toFind))
      {
        return true;
      }
      current = current.next;
    }

    return false;
  }

  @Override
  public E[] toArray(E[] toHold) throws NullPointerException
  {
    if(toHold == null)
    {
      throw new NullPointerException();
    }
    if(toHold.length < size || toHold.length > size)
    {
      toHold = Arrays.copyOf(toHold, size);
    }

    Node current = head;

    for (int i = 0; i < size; i++)

    {
      toHold[i] = (E) current.data;
      current = current.next;
    }

    return toHold;
  }

  @Override
  public Object[] toArray()
  {
    Object[] arr = new Object[size];

    Node current = head;

    for(int i = 0; i < size; i++)
    {
      arr[i] = current.data;
      current = current.next;
    }

    return arr;
  }

  @Override
  public Iterator<E> iterator()
  {
    return new Iterator<E>()
    {
      Node current = head;

      @Override
      public boolean hasNext()
      {
        return current != null;
      }

      @Override
      public E next() throws NoSuchElementException
      {

        if(hasNext() == false)
        {
          throw new NoSuchElementException();
        }
        E originalData = (E) current.data;
        current = current.next;

        return originalData;
      }
    };

  }
}
