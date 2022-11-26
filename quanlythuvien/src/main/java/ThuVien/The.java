package ThuVien;

import Polyfill.PFFileReader;
import Polyfill.ThoiGian;

import java.nio.file.Path;
import java.util.Scanner;

public class The {
    private DocGia docGia;
    private LoaiThe loaiThe;
    public ThoiGian ngayLapthe;
    public ThoiGian ngayHethan;
    private String maThe;

    PFFileReader fileReader = new PFFileReader("data\\List_The.csv");


    public void setMaThe(String maThe) {
        this.maThe = maThe;
    }

    public String getMaThe() {
        return maThe;
    }

    static Scanner sc = new Scanner(System.in);

    public The(DocGia docGia, LoaiThe loaiThe, ThoiGian ngayLapthe, ThoiGian ngayHethan, String maThe) {
        this.docGia = docGia;
        this.loaiThe = loaiThe;
        this.ngayLapthe = ngayLapthe;
        this.ngayHethan = ngayHethan;
        this.maThe = maThe;
    }

    public The() {
    }

    public LoaiThe getLoaiThe() {
        return loaiThe;
    }

    public int menuChonthe() {
        System.out.println("1.Doanh Nhan");
        System.out.println("2.The Thuong");
        return Integer.parseInt(sc.nextLine());
    }

    public void setLoaiThe(LoaiThe LoaiThe) {
        int chon;
        chon = menuChonthe();

        switch (chon) {
            case 1 -> {
                loaiThe = LoaiThe.DoanhNhan;
                break;
            }
            case 2 -> {
                loaiThe = LoaiThe.ThongThuong;
                break;
            }
        }
    }

    public DocGia getDocGia() {
        return docGia;
    }

    public void setDocGia(DocGia docGia) {
        this.docGia = docGia;
    }

    public ThoiGian getNgayHethan() {
        return ngayHethan;
    }

    public void setNgayHethan() {
        this.ngayHethan = this.ngayLapthe.modNgay(365);
    }

    public ThoiGian getNgayLapthe() {
        return ngayLapthe;
    }

    public void setNgayLapthe() {
        this.ngayLapthe = ThoiGian.now();
    }

    public String inLoaiThe()
    {
        if (loaiThe.equals(LoaiThe.DoanhNhan)) {
            return "the Doanh Nhan";
        } else {
            return "the Thong Thuong";
        }
    }
    @Override
    public String toString() {
        return "Thong tin the: " +
                "docGia=" + docGia +
                ", loaiThe=" +
                inLoaiThe()
        +
                ", maThe=" + maThe +
                ", ngayLapthe=" + ngayLapthe +
                ", ngayHethan=" + ngayHethan
        ;
    }

        public void nhap () {
            System.out.println("Nhap thong tin the: ");
            System.out.println("nhap ma the: ");
            setMaThe(sc.nextLine());
            setNgayLapthe();
            setNgayHethan();
            System.out.println("thoi gian thanh lap the: " + ThoiGian.now().toString());
            System.out.println("loai the: ");
            setLoaiThe(loaiThe);
        }

    }

