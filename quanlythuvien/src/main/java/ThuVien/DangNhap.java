package ThuVien;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class DangNhap implements DocGhiFile {
    private ConNguoi nguoiDung;
    private long maSoThe;
    private String matKhau;

    private boolean dangNhapThanhCong;

    public DangNhap(ConNguoi nguoiDung, long maSoThe, String matKhau) {
        this.nguoiDung = nguoiDung;
        this.maSoThe = maSoThe;
        this.matKhau = matKhau;
    }

    public DangNhap() {
    }

    public long getMaSoThe() {
        return maSoThe;
    }

    public void setMaSoThe(long maSoThe) {
        this.maSoThe = maSoThe;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public boolean isDangNhapThanhCong() {
        return dangNhapThanhCong;
    }

    static Scanner scanner = new Scanner(System.in);

    public int menuDangNhap() {
        System.out.println("Ban la: ");
        System.out.println("1. Nhan vien");
        System.out.println("2. Doc Gia");
        return Integer.parseInt(scanner.nextLine());
    }

    public void menuNhapTT() {
        System.out.println("Nhap ma so the: ");
        try {
            setMaSoThe(Long.parseLong(scanner.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("Nhap Sai");
        }
        System.out.println("Nhap mat khau: ");
        try {
            setMatKhau(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Nhap Sai");
        }
    }

    public int menuNhapSai() {
        System.out.println("Nhap sai ma so hoac mat khau");
        System.out.println("1.Nhap Lai");
        System.out.println("0.Thoat");
        return Integer.parseInt(scanner.nextLine());
    }

    public void dangNhap(Path path) {
        switch (menuDangNhap()) {
            case 1 -> {
                this.nguoiDung = (NhanVien) new NhanVien();
            }

            case 2 -> {
                this.nguoiDung = (DocGia) new DocGia();
            }
            default -> {
                System.out.println("Nhap Sai");
            }
        }
        while (!this.dangNhapThanhCong) {
            menuNhapTT();
            docFile(this.nguoiDung, path);
            if (this.dangNhapThanhCong) {
                break;
            } else if (menuNhapSai() == 0) {
                break;
            }
        }
    }

    public boolean kiemTraMaSo(long maSo) {
        return this.maSoThe == maSo;
    }

    public boolean kiemTraMatKhau(String matKhau) {
        return this.matKhau.equals(matKhau);
    }

    /*
     * Dùng docFile để đọc mã số thẻ và mật khẩu người dùng
     */
    @Override
    public void docFile(Object o, Path path) {
        FileReader fileReader = null;
        try {
            if (o instanceof NhanVien) {
                String s = path.toAbsolutePath().toString() + "\\List_NhanVien.csv";
                fileReader = new FileReader(s);
            } else if (o instanceof DocGia) {
                String s = path.toAbsolutePath().toString() + "\\List_DocGia.csv";
                fileReader = new FileReader(s);
            }

            CSVReader csvReader = new CSVReader(fileReader);
            try {
                String nextLine[];
                csvReader.readNext();
                while ((nextLine = csvReader.readNext()) != null) {
                    if (kiemTraMaSo(Long.parseLong(nextLine[0])) && kiemTraMatKhau(nextLine[2])) {
                        this.dangNhapThanhCong = true;
                        csvReader.close();
                        fileReader.close();
                        break;
                    }
                }
            } catch (IOException | CsvValidationException e) {
                System.out.println("Xay ra loi");
                System.exit(1);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File khong ton tai");
            System.exit(1);
        }
    }

    @Override
    public void ghiFile(Object o, Path path) {
    }

    @Override
    public String toString() {
        if (this.dangNhapThanhCong)
            return "Dang nhap thanh cong";
        else
            return "Dang nhap khong thanh cong";
    }
}
