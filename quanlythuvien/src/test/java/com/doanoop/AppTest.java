package com.doanoop;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import Polyfill.Array;

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
        Array<Integer> array = new Array<Integer>();
        List<String> strs = new ArrayList<String>();
        array.push_back(1);
        array.push_back(2);
        array.push_front(3);
        array.push_front(4);
        array.insert(1, 5);
        for(int i: array) {
            strs.add(Integer.toString(i));
        }
        strs.add(Integer.toString(array.length()));
        strs.add(Integer.toString(array.capacity()));
        assertTrue(String.join(" ", strs).equals("4 5 3 1 2 5 8"));
    }
}
