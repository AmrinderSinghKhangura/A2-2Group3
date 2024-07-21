
package Interfaces;

import java.io.Serializable;
import java.util.Arrays;
import java.util.NoSuchElementException;


/**
 *
 * @author anna
 */
public class MyList<E> implements ListADT<E>, Serializable {
    private static final long serialVersionUID = 1L;

    private static final int DEFAULT_CAPACITY = 10;
    private E[] elements;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayListADT() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        if (toAdd == null) throw new NullPointerException("Cannot add null elements to the list");
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = toAdd;
        size++;
        return true;
    }

    @Override
    public boolean add(E toAdd) throws NullPointerException {
        if (toAdd == null) throw new NullPointerException("Cannot add null elements to the list");
        ensureCapacity();
        elements[size++] = toAdd;
        return true;
    }

    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        if (toAdd == null) throw new NullPointerException("Cannot add null collection to the list");
        boolean modified = false;
        Iterator<? extends E> it = toAdd.iterator();
        while (it.hasNext()) {
            add(it.next());
            modified = true;
        }
        return modified;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        return elements[index];
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        E oldValue = elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null; // clear to let GC do its work
        return oldValue;
    }

    @Override
    public E remove(E toRemove) throws NullPointerException {
        if (toRemove == null) throw new NullPointerException("Cannot remove null elements from the list");
        for (int index = 0; index < size; index++) {
            if (toRemove.equals(elements[index])) {
                remove(index);
                return toRemove;
            }
        }
        return null;
    }

    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        if (toChange == null) throw new NullPointerException("Cannot set null elements in the list");
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        E oldValue = elements[index];
        elements[index] = toChange;
        return oldValue;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null) throw new NullPointerException("Cannot check null elements in the list");
        return indexOf(toFind) >= 0;
    }

    private int indexOf(E toFind) {
        for (int i = 0; i < size; i++) {
            if (toFind.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

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

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

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

    private void ensureCapacity() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, size * 2);
        }
    }
}
