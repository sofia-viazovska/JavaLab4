package customcollections;

import java.util.Collection;
import java.util.Objects;

/**
 * Generic-реалізація MyList на основі двозв'язного списку вузлів.
 * Навмисно НЕ реалізує RandomAccess - доступ за індексом тут
 * потребує проходу по посиланнях (O(n)), на відміну від MyArrayList.
 *
 * Java-класи java.util.LinkedList/List не використовуються.
 *
 * @param <E> тип елементів списку
 */
public class MyLinkedList<E> implements MyList<E> {

    private static final class Node<E> {
        E value;
        Node<E> prev;
        Node<E> next;

        Node(Node<E> prev, E value, Node<E> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    @Override
    public void add(E e) {
        linkLast(e);
    }

    @Override
    public void add(int index, E element) {
        checkIndexForAdd(index);
        if (index == size) {
            linkLast(element);
        } else {
            linkBefore(element, node(index));
        }
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
        int insertAt = index;
        for (E e : c) {
            add(insertAt++, e);
        }
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return node(index).value;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        return unlink(node(index));
    }

    @Override
    public void set(int index, E element) {
        checkIndex(index);
        node(index).value = element;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        for (Node<E> n = head; n != null; n = n.next, index++) {
            if (Objects.equals(n.value, o)) {
                return index;
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
        int i = 0;
        for (Node<E> n = head; n != null; n = n.next) {
            result[i++] = n.value;
        }
        return result;
    }

    private void linkLast(E e) {
        Node<E> last = tail;
        Node<E> newNode = new Node<>(last, e, null);
        tail = newNode;
        if (last == null) {
            head = newNode;
        } else {
            last.next = newNode;
        }
        size++;
    }

    private void linkBefore(E e, Node<E> succ) {
        Node<E> pred = succ.prev;
        Node<E> newNode = new Node<>(pred, e, succ);
        succ.prev = newNode;
        if (pred == null) {
            head = newNode;
        } else {
            pred.next = newNode;
        }
        size++;
    }

    private E unlink(Node<E> n) {
        E value = n.value;
        Node<E> prev = n.prev;
        Node<E> next = n.next;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            n.prev = null;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            n.next = null;
        }

        n.value = null;
        size--;
        return value;
    }

    /** Проходить до вузла з заданим індексом, стартуючи з ближчого кінця. */
    private Node<E> node(int index) {
        if (index < (size >> 1)) {
            Node<E> n = head;
            for (int i = 0; i < index; i++) {
                n = n.next;
            }
            return n;
        } else {
            Node<E> n = tail;
            for (int i = size - 1; i > index; i--) {
                n = n.prev;
            }
            return n;
        }
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
        Node<E> n = head;
        while (n != null) {
            sb.append(n.value);
            if (n.next != null) {
                sb.append(", ");
            }
            n = n.next;
        }
        return sb.append(']').toString();
    }
}
