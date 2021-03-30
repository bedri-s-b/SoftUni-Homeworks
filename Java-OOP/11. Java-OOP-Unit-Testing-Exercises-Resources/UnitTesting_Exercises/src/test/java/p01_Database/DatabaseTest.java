package p01_Database;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class DatabaseTest {
    final static Integer[] ELEMENTS = new Integer[]{2, 4, 6, 8};
    private Database database;

    @Before
    public void setUp() throws OperationNotSupportedException {
        this.database = new Database(ELEMENTS);
    }

    @Test
    public void when_correctElementsArePassed_then_createDatabaseInstance() throws OperationNotSupportedException {
    
        assertEquals(ELEMENTS.length, database.getElements().length);
        assertArrayEquals(ELEMENTS, database.getElements());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void when_sizeOfElementsIsLongOfSixteen_then_expectThrownExceptions() throws OperationNotSupportedException {

        Integer[] elements = new Integer[17];
        this.database = new Database(elements);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void when_elementsLessThenOne_then_expectExceptions() throws OperationNotSupportedException {
        this.database = new Database();
    }

    //add

    @Test
    public void when_elementPassedToAdd_then_elementAddOnLastPosition() throws OperationNotSupportedException {
        this.database.add(9);
        int expectLengthOfData = database.getElements().length;
        Integer[] elements = new Integer[]{2, 4, 6, 8, 9};
        assertEquals(ELEMENTS.length + 1, expectLengthOfData);
        assertArrayEquals(elements, database.getElements());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void when_elementIsEqualsNull_expectException() throws OperationNotSupportedException {
        this.database.add(null);
    }

    //remove

    @Test
    public void when_elementRemoveFromData_then_expectLastElementRemove() throws OperationNotSupportedException {
        this.database.remove();
        assertEquals(ELEMENTS.length - 1, database.getElements().length);
        int expectElem = ELEMENTS[ELEMENTS.length - 2];
        int lasElement = database.getElements()[database.getElements().length - 1];
        assertEquals(expectElem, lasElement);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void when_elementsIsEmpty_expectThrownException() throws OperationNotSupportedException {
        this.database = new Database(new Integer[]{1});
        database.remove();
        database.remove();
    }
}
