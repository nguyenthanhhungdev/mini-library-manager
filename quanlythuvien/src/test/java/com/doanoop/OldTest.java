package com.doanoop;

import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import ThuVien.DangNhap;
import ThuVien.DocGia;
import ThuVien.NhanVien;

public class OldTest {
    @Test
    public void dangNhapTest()
    {
        Path currentPath = Paths.get("data");

        NhanVien nhanVien = new NhanVien();
        DangNhap dangNhap = new DangNhap(nhanVien, 1L, "lien");
        dangNhap.docFile(nhanVien, currentPath);
        assertTrue(dangNhap.isDangNhapThanhCong());

        DocGia docGia = new DocGia();
        DangNhap dangNhap1 = new DangNhap(docGia, 1L, "cong");
        dangNhap1.docFile(docGia, currentPath);
        assertTrue(dangNhap1.isDangNhapThanhCong());
    }
}
