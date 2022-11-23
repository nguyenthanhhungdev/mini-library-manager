package ThuVien;

import java.util.Locale;
import java.util.Scanner;

public class TaiLieu {
    static Scanner scanner = new Scanner(System.in);
    private long ma;
    private String ten;
    private int soLuongBanSao;
    private boolean trangThai;
    private TacGia tacGia;
    private int namXuatBan;

    public long getMa() {
        return ma;
    }

    public void setMa(String ma) {
        String regex = "[1-9]";
        do {
            if (ma.matches(regex)) {
                this.ma = Long.parseLong(ma);
            } else {
                System.out.println("Nhap sai ma sach, nhap lai");
                ma = scanner.nextLine();
            }
        } while (!ma.matches(regex));
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        String regex = "[A-Za-z]";
        do {
            if (ten.matches(regex)) {
                this.ten = ten;
            } else {
                System.out.println("Nhap sai ten, nhap lai");
                ten = scanner.nextLine();
            }
        } while (!ten.matches(regex));
    }

    public int getSoLuongBanSao() {
        return soLuongBanSao;
    }

    public void setSoLuongBanSao(String soLuongBanSao) {
        String regex = "[1-9]";
        do {
            if (soLuongBanSao.matches(regex)) {
                this.soLuongBanSao = Integer.parseInt(soLuongBanSao);
            } else {
                System.out.println("Nhap sai so luong ban sao, nhap lai");
                soLuongBanSao = scanner.nextLine();
            }
        } while (!soLuongBanSao.matches(regex));
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        String regex = "[A-Za-z]";
        do {
            if (trangThai.matches(regex)) {
                if ((trangThai.toLowerCase(Locale.ROOT).equals("da muon"))) {
                    this.trangThai = false;
                } else if (trangThai.toLowerCase(Locale.ROOT).equals("chua muon")) {
                    this.trangThai = true;
                }
            } else {
                System.out.println("Nhap sai trang thai, nhap lai");
                trangThai = scanner.nextLine();
            }
        } while (!trangThai.matches(regex));
    }

    public TacGia getTacGia() {
        return tacGia;
    }

    public void setTacGia(TacGia tacGia) {
        this.tacGia = tacGia;
    }

    public int getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(int namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    public TaiLieu(long ma, String ten, int soLuongBanSao, boolean trangThai, TacGia tacGia, int namXuatBan) {
        this.ma = ma;
        this.ten = ten;
        this.soLuongBanSao = soLuongBanSao;
        this.trangThai = trangThai;
        this.tacGia = tacGia;
        this.namXuatBan = namXuatBan;
    }

    public TaiLieu() {
    }

    public void nhapThongTinTaiLieu() {
        System.out.println("Nhap ma tai lieu: ");
        setMa(scanner.nextLine());
        System.out.println("Nhap ten sach: ");
        setTen(scanner.nextLine());
        System.out.println("Nhap ten tac gia: ");
        tacGia.nhapThongTin();
    }

}
