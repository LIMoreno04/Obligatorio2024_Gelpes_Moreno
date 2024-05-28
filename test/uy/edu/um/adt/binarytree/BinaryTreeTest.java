package uy.edu.um.adt.binarytree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uy.edu.um.adt.Exceptions.AlreadyExistingValue;
import uy.edu.um.adt.Exceptions.EmptyTree;
import uy.edu.um.adt.Exceptions.InvalidValue;

import static org.junit.Assert.*;

public class BinaryTreeTest<T extends Comparable<T>> {
    SearchBinaryTreeImpl<Integer> myTree;

    @Before
    public void Base() throws AlreadyExistingValue, EmptyTree {
        myTree = new SearchBinaryTreeImpl<>();
        myTree.add(20);
        myTree.add(15);
        myTree.add(25);
        myTree.add(18);
        myTree.add(10);
        myTree.add(23);
        myTree.add(30);
    }


    @Test(expected = AlreadyExistingValue.class)
    public void addAlreadyexistingValueTest() throws AlreadyExistingValue, EmptyTree {
        myTree.add(20);
    }


    @Test
    public void removeTest() throws EmptyTree {
        myTree.remove(10);
        Assert.assertFalse(myTree.contains(10));
    }

    @Test(expected = EmptyTree.class)
    public void containsInEmptyTreeTest() throws EmptyTree {
        SearchBinaryTreeImpl<Integer> myOtherTree = new SearchBinaryTreeImpl<>();
        myOtherTree.contains(10);
    }

    @Test
    public void findTest() throws EmptyTree {
        Assert.assertEquals(10,(int)myTree.find(10));
    }

    @Test(expected = EmptyTree.class)
    public void findInEmptyTreeTest() throws EmptyTree {
        SearchBinaryTreeImpl<Integer> myOtherTree = new SearchBinaryTreeImpl<>();
        myOtherTree.find(50);
    }

    @Test
    public void preOrderTest() {
        Assert.assertEquals("[20, 15, 10, 18, 25, 23, 30]",myTree.preOrder().toString());
    }

    @Test
    public void posOrderTest() {
        Assert.assertEquals("[15, 10, 18, 25, 23, 30, 20]",myTree.posOrder().toString());

    }

    @Test
    public void inOrderTest() {
        Assert.assertEquals("[10, 15, 18, 20, 23, 25, 30]",myTree.inOrder().toString());
    }
}