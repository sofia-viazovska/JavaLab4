package customcollections;

import org.junit.Test;

import java.util.Arrays;
import java.util.RandomAccess;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class MyArrayListTest {

    @Test
    public void implementsRandomAccessMarker() {
        assertTrue(new MyArrayList<String>() instanceof RandomAccess);
    }

    @Test
    public void addAndGet() {
        MyList<String> list = new MyArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        assertEquals(3, list.size());
        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
        assertEquals("c", list.get(2));
    }

    @Test
    public void addAtIndexShiftsElements() {
        MyList<String> list = new MyArrayList<>();
        list.add("a");
        list.add("c");
        list.add(1, "b"); // a, c -> a, b, c

        assertEquals(3, list.size());
        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
        assertEquals("c", list.get(2));
    }

    @Test
    public void addAllAtEnd() {
        MyList<String> list = new MyArrayList<>();
        list.add("a");
        list.addAll(Arrays.asList("b", "c", "d"));

        assertArrayEquals(new Object[]{"a", "b", "c", "d"}, list.toArray());
    }

    @Test
    public void addAllAtIndex() {
        MyList<String> list = new MyArrayList<>();
        list.add("a");
        list.add("d");
        list.addAll(1, Arrays.asList("b", "c")); // a, d -> a, b, c, d

        assertArrayEquals(new Object[]{"a", "b", "c", "d"}, list.toArray());
    }

    @Test
    public void removeShiftsElementsAndReturnsRemoved() {
        MyList<String> list = new MyArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        String removed = list.remove(1);

        assertEquals("b", removed);
        assertEquals(2, list.size());
        assertArrayEquals(new Object[]{"a", "c"}, list.toArray());
    }

    @Test
    public void set() {
        MyList<String> list = new MyArrayList<>();
        list.add("a");
        list.add("b");
        list.set(1, "B");

        assertEquals("B", list.get(1));
        assertEquals(2, list.size());
    }

    @Test
    public void indexOfFindsFirstMatchOrMinusOne() {
        MyList<String> list = new MyArrayList<>();
        list.add("a");
        list.add("b");
        list.add("a");

        assertEquals(0, list.indexOf("a"));
        assertEquals(-1, list.indexOf("z"));
    }

    @Test
    public void growsBeyondDefaultCapacity() {
        MyList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        assertEquals(100, list.size());
        for (int i = 0; i < 100; i++) {
            assertEquals(Integer.valueOf(i), list.get(i));
        }
    }

    @Test
    public void getOutOfBoundsThrows() {
        MyList<String> list = new MyArrayList<>();
        list.add("a");
        try {
            list.get(5);
            fail("Очікувався IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException expected) {
            // ок
        }
    }
}
