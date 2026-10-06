package customcollections;

import java.util.Collection;

/**
 * Custom Collections 2.0: узагальнений (generic) інтерфейс списку
 * (аналог java.util.List, без успадкування від нього).
 *
 * Порівняно з версією без generics (hard task 2), addAll тепер приймає
 * Collection<? extends E> замість Object[] - це саме той wildcard,
 * який explicitly вимагався за умовою (приклад з java.util.List):
 * boolean addAll(Collection<? extends E> c).
 *
 * @param <E> тип елементів списку
 */
public interface MyList<E> {

    /** Додає елемент в кінець списку. */
    void add(E e);

    /** Додає елемент у вказане місце списку. */
    void add(int index, E element);

    /** Додає всі елементи колекції в кінець списку. */
    void addAll(Collection<? extends E> c);

    /** Додає всі елементи колекції у вказане місце списку. */
    void addAll(int index, Collection<? extends E> c);

    /** Повертає елемент за індексом. */
    E get(int index);

    /** Видаляє елемент за індексом і повертає його. */
    E remove(int index);

    /** Змінює значення елемента за індексом. */
    void set(int index, E element);

    /**
     * Пошук індексу першого входження елемента за значенням (equals). -1, якщо не знайдено.
     * Параметр навмисно лишається Object (а не E) - так само зроблено і в
     * java.util.List: це дозволяє викликати indexOf з будь-яким об'єктом
     * (наприклад null або значенням стороннього типу), не змушуючи виклик
     * ще на етапі компіляції відповідати типу E.
     */
    int indexOf(Object o);

    /** Розмір списку. */
    int size();

    /** Перетворює список в масив об'єктів. */
    Object[] toArray();
}
