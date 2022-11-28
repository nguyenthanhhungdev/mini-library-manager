package ThuVien;

import java.util.logging.Level;
import java.util.logging.Logger;

import Polyfill.PFArray;
import Polyfill.StringHelper;
import Polyfill.ThoiGian;

public class Cashiers extends Management<Cashier> {
    private static final Logger LOGGER = Logger.getLogger(Cashiers.class.getName());

    public Cashiers() {
        super();
    }

    public Cashiers(Cashier[] r) {
        super(r);
    }

    public Cashiers(PFArray<String[]> blob) {
        this();
        blob.stream().forEach(e -> instance.push_back(Cashier.fromBlob(e)));
        updateCounter();
    }

    @Override
    public Cashier add() {
        String username = StringHelper.acceptLine("Ten tai khoan");
        Cashier __ = new Cashier(genNextId(), username);
        __.changePassword(null, StringHelper.acceptLine("Mat khau"));
        __.setName(StringHelper.acceptLine("Ten"));
        __.setBirth(ThoiGian.parseTG(StringHelper.acceptLine("Ngay sinh")));
        __.setPhone(StringHelper.acceptLine("So dien thoai"));
        __.setEmail(StringHelper.acceptLine("Email"));
        __.setAddress(StringHelper.acceptLine("Dia chi"));
        __.setTruc(CaTruc.parseCaTruc(StringHelper.acceptLine("Ca truc")));
        String luong = StringHelper.acceptLine("Luong khoi dau");
        __.setLuong(StringHelper.isNullOrBlank(luong) ? new Luong() : new Luong(Long.parseLong(luong)));
        return __;
    }

    private int promptSearch() {
        int n;
        try {
            n = search(Integer.parseInt(StringHelper.acceptLine("Nhap id (thu ngan)")));
            if (n == -1) {
                System.out.println("Tim kiem khong co ket qua");
            } else {
                System.out.println("Tim thay thu ngan:");
                instance.at(n).toString();
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Input error", e);
            throw e;
        }
        return n;
    }

    @Override
    public Cashier remove() {
        int n;
        Cashier __ = null;
        try {
            n = promptSearch();
            if (n == -1) {
                System.out.println("Tim kiem that bai, remove thu ngan that bai");
            } else {
                System.out.println("Xac nhan xoa thu ngan:");
                instance.at(n).toString();
                int m = StringHelper.acceptInput("Co", "Suy nghi lai");
                if (m == 1) {
                    __ = instance.erase(n);
                    System.out.println("Da xoa thu ngan");
                    __.toString();
                }
            }

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Co loi xay ra, remove thu ngan that bai", e);
        }
        return __;
    }

    @Override
    public Cashier edit() {
        int n;
        Cashier __ = null;
        try {
            n = promptSearch();
            if (n == -1) {
                System.out.println("Tim kiem that bai, edit thu ngan that bai");
            } else {
                int m;
                do {
                    __ = instance.at(n);
                    System.out.println("Dang thao tac edit thu ngan:");
                    __.toString();
                    System.out.println("Chon thao tac");
                    switch (m = StringHelper.acceptInput("Ten", "Ngay sinh", "So dien thoai", "Email", "Dia chi", "Ca truc")) {
                        case 1:
                            __.setName(StringHelper.acceptLine("Nhap ten"));
                            break;
                        case 2:
                            __.setBirth(ThoiGian.parseTG(StringHelper.acceptLine("Nhap ngay sinh")));
                            break;
                        case 3:
                            __.setPhone(StringHelper.acceptLine("Nhap sdt"));
                            break;
                        case 4:
                            __.setEmail(StringHelper.acceptLine("Nhap email"));
                            break;
                        case 5:
                            __.setAddress(StringHelper.acceptLine("Nhap dia chi"));
                            break;
                        case 6:
                            __.setTruc(CaTruc.parseCaTruc(StringHelper.acceptLine("Nhap ca truc")));
                            break;
                        case -1:
                        default:
                            m = -1;
                            System.out.println("Ket thuc edit thu ngan");
                            break;
                    }
                } while (m > 0);
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Co loi xay ra, edit thu ngan that bai", e);
        }
        return __;
    }

    @Override
    public int[] search() {
        throw new UnsupportedOperationException("Chuc nang chua dc code do ko du thoi gian");
    }

}
