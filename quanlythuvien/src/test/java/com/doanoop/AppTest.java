package com.doanoop;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import Polyfill.PFArray;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    @Test
    public void polyfillArrayTest() {
        PFArray<Integer> pfarray = new PFArray<Integer>();
        List<String> strs = new ArrayList<String>();
        pfarray.push_back(1);
        pfarray.push_back(2);
        pfarray.push_front(3);
        pfarray.push_front(4);
        pfarray.insert(1, 5);
        for(int i: pfarray) {
            strs.add(Integer.toString(i));
        }
        strs.add(Integer.toString(pfarray.length()));
        strs.add(Integer.toString(pfarray.capacity()));
        assertTrue(String.join(" ", strs).equals("4 5 3 1 2 5 8"));
        strs.clear();;
        pfarray.pop_back();
        pfarray.pop_front();
        pfarray.erase(1);
        for(int i: pfarray) {
            strs.add(Integer.toString(i));
        }
        strs.add(Integer.toString(pfarray.length()));
        strs.add(Integer.toString(pfarray.capacity()));
        assertTrue(String.join(" ", strs).equals("5 1 2 8"));

    }
}
