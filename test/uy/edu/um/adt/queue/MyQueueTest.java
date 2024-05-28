package uy.edu.um.adt.queue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uy.edu.um.adt.Exceptions.EmptyQueueException;
import uy.edu.um.adt.Exceptions.InvalidValue;
import uy.edu.um.adt.linkedlist.MyLinkedListImpl;

import static org.junit.Assert.*;

public class MyQueueTest {
    MyQueue<String> myQueue;

    @Before
    public void Base(){
        myQueue = new MyLinkedListImpl<>();
        myQueue.enqueue("Value1");
        myQueue.enqueue("Value2");
        myQueue.enqueue("Value3");
        myQueue.enqueue("Value4");
        myQueue.enqueue("Value5");
    }

    @Test
    public void enqueueTest(){
        Assert.assertTrue(myQueue.contains("Value4"));
    }

    @Test
    public void dequeueTest() throws EmptyQueueException {
        Assert.assertEquals("Value1",myQueue.dequeue());
    }

    @Test(expected = EmptyQueueException.class)
    public void dequeueEmptyQueueTest() throws EmptyQueueException {
        MyQueue<String> myOtherQueue = new MyLinkedListImpl<>();
        myOtherQueue.dequeue();
    }

    @Test
    public void containsFalseTest() {
        Assert.assertFalse(myQueue.contains("Value10"));
    }

    @Test
    public void getTest() throws InvalidValue {
        Assert.assertEquals("Value2",myQueue.get(3));
    }
    @Test(expected = InvalidValue.class)
    public void getInvalidValueTest1() throws InvalidValue {
        myQueue.get(-1);
    }
    @Test(expected = InvalidValue.class)
    public void getInvalidValueTest2() throws InvalidValue {
        myQueue.get(6);
    }
    @Test
    public void sizeTest() {
        Assert.assertEquals(5,myQueue.size());
    }

    @Test
    public void isEmptyTest() {
        Assert.assertFalse(myQueue.isEmpty());
    }
}