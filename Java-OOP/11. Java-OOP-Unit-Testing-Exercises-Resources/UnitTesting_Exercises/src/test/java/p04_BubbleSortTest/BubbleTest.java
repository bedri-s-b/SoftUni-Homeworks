package p04_BubbleSortTest;

import org.junit.Test;

import static org.junit.Assert.*;

public class BubbleTest {

    @Test
    public void when_notSortArrayPassedToSort_then_arraySorted() {
        int[] array = new int[]{1, 0, 5, 6, 62, -26};
        Bubble.sort(array);
        int[] expected = new int[]{-26, 0, 1, 5, 6, 62};
        assertEquals(array.length,expected.length);
        assertArrayEquals(expected,array);
    }

    @Test
    public void when_arraysIsSorted_then_sameArray(){
        int[] array = new int[]{1,2,3,4,5};
        int[] expected = new int[]{1,2,3,4,5};
        assertEquals(array.length,expected.length);
        assertArrayEquals(expected,array);
    }

}