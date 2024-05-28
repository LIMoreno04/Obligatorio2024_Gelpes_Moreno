package uy.edu.um.adt.hash;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uy.edu.um.adt.Exceptions.InvalidValue;

public class MyChainHashTest {
    private MyChainedHashImpl<Object,Object> myHash;

    @Before
    public void Base(){
        myHash = new MyChainedHashImpl<>();
        myHash.put(1,"value1");
        myHash.put(2,"value2");
        myHash.put(3,"value3");
        myHash.put(4,"value4");
        myHash.put(5,"value5");
        myHash.put(6,"value6");
        myHash.put(7,"value7");
        myHash.put(8,"value8");
        myHash.put(9,"value9");
        myHash.put(10,"value10");
    }

    @Test
    public void containsTrueTest() {
        Assert.assertTrue(myHash.contains(7));
    }
    @Test
    public void containsFalseTest() {
        Assert.assertFalse(myHash.contains(15));
    }

    @Test
    public void findTest() throws InvalidValue{
        myHash.put("ola","value11");
        Assert.assertEquals("value9",myHash.find(9));
        Assert.assertEquals("value11",myHash.find("ola"));
    }
    @Test(expected = InvalidValue.class)
    public void findInvalidValueTest() throws InvalidValue{
        myHash.find("ola");
    }

    @Test
    public void collisionTest() throws InvalidValue {
        myHash.put("ola","value11");
        myHash.put("alo","value12");
        Assert.assertEquals("value11",myHash.find("ola"));
        Assert.assertEquals("value12",myHash.find("alo"));
    }
    @Test
    public void removeTest() throws InvalidValue {
        myHash.remove(4);
        Assert.assertFalse(myHash.contains(4));
    }

    @Test(expected = InvalidValue.class)
    public void removeInvalidValueTest() throws InvalidValue{
        myHash.remove(14);
    }

    @Test
    public void sizeTest(){
        Assert.assertEquals(11,myHash.getSize());
    }

    @Test
    public void resizeTest() throws InvalidValue{
        myHash.resize(14);
        Assert.assertEquals(14,myHash.getSize());
        Assert.assertTrue(myHash.contains(1));
        Assert.assertTrue(myHash.contains(4));
        Assert.assertTrue(myHash.contains(10));
    }

}
