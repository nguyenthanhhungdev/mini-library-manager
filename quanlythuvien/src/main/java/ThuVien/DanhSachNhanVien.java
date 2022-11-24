package ThuVien;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DanhSachNhanVien implements DocGhiFile, ThaoTacFile {
    static NhanVien[] danhSach = new NhanVien[0];

    public static NhanVien[] getDanhSach() {
        return danhSach;
    }

    public static void setDanhSach(NhanVien[] danhSach) {
        DanhSachNhanVien.danhSach = danhSach;
    }

    @Override
    public void docFile(Object o, Path path) {

    }

    @Override
    public void ghiFile(Object o, Path path) {
        o = (Object) new NhanVien();
        NhanVien nhanVien = (NhanVien) o;// Downcasting
        try (FileWriter fileWriter = new FileWriter("DanhSachNhanVien.txt", true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(nhanVien.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void them() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.nhapThongTin();
    }

    @Override
    public void sua() {

    }

    @Override
    public void xoa() {

    }
}
