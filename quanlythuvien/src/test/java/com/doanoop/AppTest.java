package com.doanoop;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import Polyfill.PFArray;
import ThuVien.ThoiGian;

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

    @Test
    public void thoiGianTest() {
        ThoiGian thoigian1 = new ThoiGian(18, 11, 2022);
        ThoiGian thoigian2 = new ThoiGian(18, 11, 2022, 2, 7, 10);
        ThoiGian thoigian3 = new ThoiGian("18/11/2022 02:07:10");
        assertTrue("18/11/2022 00:00:00".equals(thoigian1.toString()));
        assertTrue("18/11/2022 02:07:10".equals(thoigian2.toString()));
        assertTrue("18/11/2022 02:07:10".equals(thoigian3.toString()));
        thoigian1 = thoigian1.modNgay(20);
        thoigian2 = thoigian2.modNgay(-20);
        assertTrue("08/12/2022 00:00:00".equals(thoigian1.toString()));
        assertTrue("29/10/2022 02:07:10".equals(thoigian2.toString()));
        assertTrue(thoigian2.compareTo(thoigian1) < 0);
        assertTrue(thoigian3.compareTo(thoigian2) > 0);
        ThoiGian thoigian4 = new ThoiGian(LocalDateTime.of(2022, 10, 29, 2, 7, 10));
        assertTrue(thoigian4.compareTo(thoigian2) == 0);
    }
}
