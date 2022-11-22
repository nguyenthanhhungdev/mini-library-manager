package ThuVien;

import Polyfill.ThoiGian;

import java.util.Scanner;

public class TacGia {
    static Scanner scanner = new Scanner(System.in);
    private String hoTen;
    private long ma;
    private ThoiGian ngaySinh;

    public TacGia(String hoTen, String ma, ThoiGian ngaySinh) {
        this.hoTen = hoTen;
        this.ma = Long.parseLong(ma);
        this.ngaySinh = ngaySinh;
    }

    public TacGia() {
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        String regex = "[A-Za-z]";
        do {
            if (hoTen.matches(regex)) {
                this.hoTen = hoTen;
            } else {
                System.out.println("Nhap sai ten tac gia, nhap lai");
                hoTen = scanner.nextLine();
            }
        } while (!hoTen.matches(regex));
    }

    public long getMa() {
        return ma;
    }

    public void setMa(String ma) {
        String regex = "[1-9]";
        do {
            if (ma.matches(regex)) {
                this.ma = Long.parseLong(ma);
            } else {
                System.out.println("Nhap sai ma, nhap lai");
                ma = scanner.nextLine();
            }
        } while (!ma.matches(regex));
    }

    public ThoiGian getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh() {
        System.out.println("Nhap ngay: ");
        int ngay = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhap thang: ");
        int thang = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhap nam: ");
        int nam = Integer.parseInt(scanner.nextLine());
        this.ngaySinh = new ThoiGian(ngay, thang, nam);
    }

    public void nhapThongTin() {
        System.out.println("Nhap ma tac gia: ");
        setMa(scanner.nextLine());
        System.out.println("Nhap ten tac gia: ");
        setHoTen(scanner.nextLine());
        System.out.println("Nhap ngay sinh tac gia: ");
        setNgaySinh();
    }
}
