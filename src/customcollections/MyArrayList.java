package customcollections;

import java.util.Collection;
import java.util.Objects;
import java.util.RandomAccess;

/**
 * Generic-реалізація MyList на основі масиву, що динамічно розширюється.
 *
 * Внутрішнє сховище лишається "сирим" Object[] - у Java не можна
 * створити масив параметризованого типу (new E[n] не компілюється,
 * бо в рантаймі інформація про E стирається - type erasure). Тому
 * елементи зберігаються як Object[], а назовні (у get/remove) повертаються
 * через (E) - unchecked-приведення типу, яке тут безпечне, бо ми самі
 * повністю контролюємо, що саме кладеться в масив (тільки через add/set).
 *
 * Java-класи java.util.ArrayList/List не використовуються.
 *
 * @param <E> тип елементів списку
 */
public class MyArrayList<E> implements MyList<E>, RandomAccess {

    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY = new Object[0];

    private Object[] elements;
    private int size;

    public MyArrayList() {
        elements = EMPTY;
        size = 0;
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Ємність не може бути від'ємною: " + initialCapacity);
        }
        elements = initialCapacity == 0 ? EMPTY : new Object[initialCapacity];
        size = 0;
    }

    @Override
    public void add(E e) {
        ensureCapacity(size + 1);
        elements[size++] = e;
    }

    @Override
    public void add(int index, E element) {
        checkIndexForAdd(index);
        ensureCapacity(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public void addAll(Collection<? extends E> c) {
        addAll(size, c);
    }

    @Override
    public void addAll(int index, Collection<? extends E> c) {
        checkIndexForAdd(index);
        if (c == null || c.isEmpty()) {
            return;
        }
        Object[] newItems = c.toArray();
        ensureCapacity(size + newItems.length);
        System.arraycopy(elements, index, elements, index + newItems.length, size - index);
        System.arraycopy(newItems, 0, elements, index, newItems.length);
        size += newItems.length;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }

    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        checkIndex(index);
        E removed = (E) elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null; // прибираємо посилання, щоб не заважати GC
        return removed;
    }

    @Override
    public void set(int index, E element) {
        checkIndex(index);
        elements[index] = element;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        System.arraycopy(elements, 0, result, 0, size);
        return result;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity <= elements.length) {
            return;
        }
        int oldCapacity = elements.length == 0 ? DEFAULT_CAPACITY : elements.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1); // ріст у 1.5 рази
        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
        Object[] newElements = new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        return sb.append(']').toString();
    }
}
