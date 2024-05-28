package uy.edu.um.adt.doublelinkedlist;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uy.edu.um.adt.Exceptions.InvalidValue;
import uy.edu.um.adt.linkedlist.MyList;

import static org.junit.Assert.*;

public class MyCircularLinkedListImplTest {
    MyCircularLinkedListImpl<String> myList;

    @Before
    public void Base(){
        myList = new MyCircularLinkedListImpl<>();
        myList.add("value1");
        myList.add("value2");
        myList.add("value3");
        myList.add("value4");
        myList.add("value5");
    }

    @Test
    public void sizeTest(){
        Assert.assertEquals(5,myList.size());
    }
    @Test
    public void addTest() {
        Assert.assertTrue(myList.contains("value2"));
    }

    @Test
    public void addFirstTest() {
        myList.addFirst("value0");
        Assert.assertEquals(myList.getFirst(),"value0");
    }

    @Test
    public void removeTest() throws InvalidValue {
        myList.remove("value3");
        Assert.assertFalse(myList.contains("value3"));
    }
    @Test(expected = InvalidValue.class)
    public void removeInvalidValueTest() throws InvalidValue {
        myList.remove("value6");
    }

}