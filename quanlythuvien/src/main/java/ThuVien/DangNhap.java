package ThuVien;
import java.io.*;
import java.util.Scanner;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class DangNhap implements DocGhiFile {
    private ConNguoi nguoiDung;
    private long maSoThe;
    private String matKhau;

    private boolean dangNhapThanhCong;

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

    private int luaChonNguoiDung = -1;

    public int getLuaChonNguoiDung() {
        return luaChonNguoiDung;
    }

    public int menuDangNhap() {
        System.out.println("Ban la: ");
        System.out.println("1. Nhan vien");
        System.out.println("2. Doc Gia");
        return luaChonNguoiDung = Integer.parseInt(scanner.nextLine());
    }

    public void menuNhapTT() {
        System.out.println("Nhap ma so the: ");
        try {
            setMaSoThe(Long.parseLong(scanner.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("Nhap Sai");
            return;
        }
        System.out.println("Nhap mat khau: ");
        try {
            setMatKhau(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Nhap Sai");
            return;
        }
    }

    public int menuNhapSai() {
        System.out.println("Nhap sai ma so hoac mat khau");
        System.out.println("1.Nhap Lai");
        System.out.println("0.Thoat");
        return luaChonNguoiDung = Integer.parseInt(scanner.nextLine());
    }

    public void dangNhap() {
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
            docFile(this.nguoiDung);
            if (this.dangNhapThanhCong)
                break;
            else if (menuNhapSai() == 1)
                continue;
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
     * */
    @Override
    public void docFile(Object o) {
        FileReader fileReader = null;
        try {
            if (o instanceof NhanVien)
                fileReader = new FileReader("data/DanhSachNhanVien.csv");
            else if (o instanceof DocGia)
                fileReader = new FileReader("data/DanhSachDocGia.csv");

            CSVReader csvReader = new CSVReader(fileReader);
            try {
                String nextLine[];
                while ((nextLine = csvReader.readNext()) != null) {
                    if (kiemTraMaSo(Long.parseLong(nextLine[0])) && kiemTraMatKhau(nextLine[4])) {
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

    /*
     * Dùng ghiFile để thay đổi mật khẩu
     * Ghi mật khẩu mới vào file*/
    @Override
    public void ghiFile(Object o) {
        try {
            FileWriter fileWriter = new FileWriter("quanlythuvien/data/DanhSachNhanVien.csv");
            CSVWriter csvWriter = new CSVWriter(fileWriter);
        } catch (IOException e)
        {
            System.out.println("Khong tim thay file");
        }
    }


}
