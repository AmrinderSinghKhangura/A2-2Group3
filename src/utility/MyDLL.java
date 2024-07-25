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

  /**
   * DLL class to hold data and pointers to the next and previous nodes
   */
  public MyDLL()
  {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  /**
   * Size method to return the size of the list
   * @return size of the list
   */
  @Override
  public int size()
  {
    return size;
  }

  /**
   * Clear method to remove all elements from the list
   */
  @Override
  public void clear()
  {
    head = null;
    tail = null;
    size = 0;
  }

  /**
   * Add method to add an element to the list at a specific index
   * @param index where to add the element
   * @param toAdd element to add
   * @return true if added, false if not
   * @throws NullPointerException if element is null
   * @throws IndexOutOfBoundsException if index is out of bounds
   */
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

  /**
   * Add method to add an element to the end of the list
   * @param toAdd element to add
   * @return true if added, false if not
   * @throws NullPointerException if element is null
   */
  @Override
  public boolean add(E toAdd) throws NullPointerException
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

  /**
   * AddAll method to add all elements from a list to the end of the current list
   * @param toAdd list to add
   * @return true if added, false if not
   * @throws NullPointerException if list is null
   */
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

  /**
   * Get method to return the element at a specific index
   * @param index where to get the element
   * @return element at the index
   * @throws IndexOutOfBoundsException if index is out of bounds
   */
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

  /**
   * Remove method to remove the element at a specific index
   * @param index where to remove the element
   * @return element removed
   * @throws IndexOutOfBoundsException if index is out of bounds
   */
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

  /**
   * Remove method to remove a specific element from the list
   * @param toRemove element to remove
   * @return element removed
   * @throws NullPointerException if element is null
   */
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

  /**
   * Set method to change the element at a specific index
   * @param index where to change the element
   * @param toChange element to change to
   * @return the element that was changed
   * @throws NullPointerException if element is null
   * @throws IndexOutOfBoundsException if index is out of bounds
   */
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

  /**
   * IsEmpty method to check if the list is empty
   * @return true if empty, false if not
   */
  @Override
  public boolean isEmpty()
  {
    return size == 0;
  }

  /**
   * Contains method to check if the list contains a specific element
   * @param toFind element to find
   * @return true if found, false if not
   * @throws NullPointerException if element is null
   */
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

  /**
   * ToArray method to convert the list to an array and store it in given array
   * @param toHold array to hold the list
   * @return array of the list
   */
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

  /**
   * ToArray method to convert the list to an array
   * @return array of the list
   */
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

  /**
   * Iterator method to iterate through the list
   * @return iterator of the list
   */
  @Override
  public Iterator<E> iterator()
  {
    return new Iterator<E>()
    {
      Node current = head;

      /**
       * HasNext method to check if there is a next element
       * @return true if there is a next element, false if not
       */
      @Override
      public boolean hasNext()
      {
        return current != null;
      }

      /**
       * Next method to get the next element
       * @return the next element
       * @throws NoSuchElementException if there is no next element
       */
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
