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

    private Cashier promptSearch() {
        try {
            int n = search(Integer.parseInt(StringHelper.acceptLine("Nhap id (thu ngan)")));
            if (n == -1) {
                System.out.println("Tim kiem khong co ket qua");
                return null;
            } else {
                return instance.at(n);
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Input error", e);
            throw e;
        }
    }

    @Override
    public Cashier remove() {

        return null;
    }

    @Override
    public Cashier edit() {
        try {
            int n = search(Integer.parseInt(StringHelper.acceptLine("Nhap id (thu ngan)")));
            if (n == -1) {
                System.out.println("Tim kiem khong co ket qua");
                return null;
            } else {
                return instance.at(n);
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Input error", e);
            throw e;
        }
    }

    @Override
    public int[] search() {
        // try {
        // String input = StringHelper.acceptLine("ID");
        // if(!StringHelper.isNullOrBlank(input)) {
        // return new int[] {search(Integer.parseInt(input))};
        // }
        // // con tiep
        // } catch (Exception e) {
        // LOGGER.log(Level.WARNING, "Input error", e);
        // throw e;
        // }

        return null;
    }

}
