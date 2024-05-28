package uy.edu.um.adt.stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uy.edu.um.adt.Exceptions.EmptyStackException;
import uy.edu.um.adt.linkedlist.MyLinkedListImpl;

import static org.junit.Assert.*;

public class MyStackTest {
    MyStack<String> myStack;

    @Before
    public void Base(){
        myStack = new MyLinkedListImpl<>();
        myStack.push("value1");
        myStack.push("value2");
        myStack.push("value3");
        myStack.push("value4");
    }

    @Test
    public void pushTest() {
        Assert.assertEquals("value4",myStack.peek());
    }

    @Test
    public void popTest() throws EmptyStackException {
        Assert.assertEquals("value4",myStack.pop());
    }

    @Test(expected = EmptyStackException.class)
    public void popInEmptyStackTest() throws EmptyStackException{
        MyStack<String> myOtherStack = new MyLinkedListImpl<>();
        myOtherStack.pop();
    }

    @Test
    public void peekInEmptyStackTest() {
        MyStack<String> myOtherStack = new MyLinkedListImpl<>();
        Assert.assertNull(myOtherStack.peek());
    }

    @Test
    public void sizeTest() {
        Assert.assertEquals(4,myStack.size());
    }

    @Test
    public void isEmptyFalseTest() {
        Assert.assertFalse(myStack.isEmpty());
    }

    @Test
    public void isEmptyTrueTest() {
        MyStack<String> myOtherStack = new MyLinkedListImpl<>();
        Assert.assertTrue(myOtherStack.isEmpty());
    }
}