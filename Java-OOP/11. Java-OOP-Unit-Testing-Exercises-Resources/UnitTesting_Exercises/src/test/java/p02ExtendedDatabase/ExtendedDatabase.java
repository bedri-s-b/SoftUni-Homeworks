package p02ExtendedDatabase;

import org.junit.Before;
import org.junit.Test;
import p02_ExtendedDatabase.Database;
import p02_ExtendedDatabase.Person;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ExtendedDatabase {
    final static Person[] PEOPLE = new Person[]{
            new Person(1, "A"),
            new Person(2, "B"),
            new Person(3, "C")
    };
    private Database database;

    @Before
    public void setUp() throws OperationNotSupportedException {
        this.database = new Database(PEOPLE);
    }

    @Test
    public void when_correctElementsArePassed_then_createDatabaseInstance() throws OperationNotSupportedException {

        assertEquals(PEOPLE.length, database.getElements().length);
        assertArrayEquals(PEOPLE, database.getElements());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void when_sizeOfElementsIsLongOfSixteen_then_expectThrownExceptions() throws OperationNotSupportedException {
        this.database = new Database(new Person[17]);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void when_elementsLessThenOne_then_expectExceptions() throws OperationNotSupportedException {
        this.database = new Database();
    }

    //add

    @Test
    public void when_elementPassedToAdd_then_elementAddOnLastPosition() throws OperationNotSupportedException {
        Person person = new Person(4, "D");
        database.add(person);
        assertEquals(PEOPLE.length + 1,database.getElements().length);
        assertEquals(person.getId(),database.getElements()[database.getElements().length - 1].getId());
        assertEquals(person.getUsername(),database.getElements()[database.getElements().length - 1].getUsername());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void when_elementIsEqualsNull_expectException() throws OperationNotSupportedException {
        this.database.add(null);
    }

    //remove

    @Test
    public void when_elementRemoveFromData_then_expectLastElementRemove() throws OperationNotSupportedException {
        this.database.remove();
        assertEquals(PEOPLE.length - 1, database.getElements().length);
        int expectId = PEOPLE[PEOPLE.length - 2].getId();
        int lastId = database.getElements()[database.getElements().length - 1].getId();
        assertEquals(expectId, lastId);


        String expectName = PEOPLE[PEOPLE.length - 2].getUsername();
        String lastName = database.getElements()[database.getElements().length - 1].getUsername();

        assertEquals(expectName,lastName);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void when_elementsIsEmpty_expectThrownException() throws OperationNotSupportedException {
        this.database = new Database(new Person(14,"V"));
        database.remove();
        database.remove();
    }
}
