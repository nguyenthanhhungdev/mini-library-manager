
package ThuVien;
import java.util.Scanner;

public class Ngay {
    Scanner scanner = new Scanner(System.in);
    private int ngay;
    private int thang;
    private int nam;

    public Ngay(int ngay, int thang, int nam) {
        this.ngay = ngay;
        this.thang = thang;
        this.nam = nam;
    }

    public Ngay() {
    }

    public int getNgay() {
        return ngay;
    }

    boolean laNamNhuan(int nam) {
        if ((nam % 4 == 0 && nam % 1000 != 0) || nam % 400 == 0)
            return true;
        return false;
    }

    public void setNgay(int ngay) {
        switch (thang) {
            case 2:
                do {
                    if (laNamNhuan(nam) || ngay <= 29)
                        this.ngay = ngay;
                    else {
                        System.out.println("Nhap ngay it hon 29 ngay");
                        ngay = Integer.parseInt(scanner.nextLine());
                    }
                    if (!laNamNhuan(nam) || ngay <= 28)
                        this.ngay = ngay;
                    else {
                        System.out.println("Nhap ngay it hon 29 ngay");
                        ngay = Integer.parseInt(scanner.nextLine());
                    }
                } while (ngay >= 29 || ngay >= 28);
                break;
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                do {
                    if (ngay <= 31)
                        this.ngay = ngay;
                    else {
                        System.out.println("Nhap ngay it hon 31");
                        ngay = Integer.parseInt(scanner.nextLine());
                    }
                } while (ngay >= 31);
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                do {
                    if (ngay <= 30)
                        this.ngay = ngay;
                    else {
                        System.out.println("Nhap ngay it hon 31");
                        ngay = Integer.parseInt(scanner.nextLine());
                    }
                } while (ngay >= 30);
                break;
        }

    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        do {
            if (thang <= 12 && thang >= 1)
                this.thang = thang;
            else {
                System.out.println("Nhap lai thang: ");
                thang = Integer.parseInt(scanner.nextLine());
            }
        } while (thang >= 12 && thang <= 1);

    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        do {
            if (nam <= 0)
                this.nam = nam;
            else {
                System.out.println("Nhap lai nam");
                nam = Integer.parseInt(scanner.nextLine());
            }
        } while (nam <= 0);
    }

    @Override
    public String toString() {
        return ngay + "/" + thang + "/" + nam;
    }

    /*
    * Chuyển từ chuỗi sang ngày tháng năm*/
    static public Ngay toNgay(String ngaySinh)
    {
        String[] strings = ngaySinh.split("/");
        Ngay ngayThangNam = new Ngay(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
        return ngayThangNam;
    }
}
