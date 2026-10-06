package customcollections;

import java.util.Arrays;
import java.util.List;

/**
 * Демонстрація Custom Collections 2.0: generics + wildcard у addAll.
 */
public class GenericsDemo {

    public static void main(String[] args) {
        // 1. Generics: список типізований, приведення типів при get() не потрібне
        MyList<String> words = new MyArrayList<>();
        words.add("generics");
        words.add("wildcard");
        String first = words.get(0); // без (String)
        System.out.println("Перше слово: " + first + ", список: " + words);

        // words.add(42); // не скомпілюється - ось у чому користь generics

        // 2. Wildcard: Collection<? extends E> дозволяє додати колекцію ПІДТИПІВ
        MyList<Number> numbers = new MyLinkedList<>();
        List<Integer> ints = Arrays.asList(1, 2, 3);
        List<Double> doubles = Arrays.asList(1.5, 2.5);

        numbers.addAll(ints);    // List<Integer> -> MyList<Number>
        numbers.addAll(doubles); // List<Double>  -> MyList<Number>
        System.out.println("Числа: " + numbers + " (розмір " + numbers.size() + ")");

        // 3. Той самий інтерфейс - різні реалізації
        MyList<Integer> array = new MyArrayList<>();
        array.addAll(ints);
        array.add(1, 99);
        System.out.println("MyArrayList: " + array + ", indexOf(99) = " + array.indexOf(99));
    }
}
