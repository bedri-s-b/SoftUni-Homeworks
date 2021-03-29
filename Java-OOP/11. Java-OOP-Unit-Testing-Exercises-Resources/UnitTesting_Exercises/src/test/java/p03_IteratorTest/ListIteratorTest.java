package p03_IteratorTest;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class ListIteratorTest {
    private ListIterator listIterator;

    @Before
    public void setUp() throws OperationNotSupportedException {
    this.listIterator = new ListIterator("A","B","C");
    }

    @Test
    public void when_createNewInstanceWithConstructorWithOneOreMoreElements_then_expectPossibleCreated() {
        boolean check = listIterator.hasNext();
        listIterator.move();
        assertTrue(check);
        check = listIterator.hasNext();
        listIterator.move();
        assertTrue(check);
        check = listIterator.hasNext();
        assertFalse(check);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void when_createNewInstanceWithConstructorWithZeroElement_then_expectException() throws OperationNotSupportedException {
        this.listIterator = new ListIterator(null);
    }

    
}