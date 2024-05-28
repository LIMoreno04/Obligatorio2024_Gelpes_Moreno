package uy.edu.um.adt.hash;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uy.edu.um.adt.Exceptions.InvalidValue;

public class MyMyClosedHashImplTest {
    private MyClosedHashImpl<Object,Object> myHash;

    @Before
    public void Base(){
        myHash = new MyClosedHashImpl<>();
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
    public void putResizeTest() {
        myHash.put(11,"value11");
        myHash.put(12,"value12");
        Assert.assertEquals(22, myHash.getSize()); //El size original es 11
        Assert.assertTrue(myHash.contains(1));
    }

    @Test
    public void putSameHashValueTest() throws InvalidValue {
        myHash.put("ola","valueX");
        myHash.put("alo","valueY");
        Assert.assertEquals("valueX",myHash.find("ola"));
        Assert.assertEquals("valueY",myHash.find("alo"));
    }


    @Test(expected = InvalidValue.class)
    public void findInvalidValueTest() throws InvalidValue {
        myHash.find(11);
    }

    @Test
    public void containsFalseTest(){
        Assert.assertFalse("No existe el elemento", myHash.contains(11));
    }

    @Test
    public void removeTest() throws InvalidValue {
        myHash.remove(1);
        Assert.assertFalse(myHash.contains(1));
    }

    @Test(expected = InvalidValue.class)
    public void removeInvalidValueTest() throws InvalidValue {
        myHash.remove(20);
    }
}