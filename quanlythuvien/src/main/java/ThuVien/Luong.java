package ThuVien;

import Polyfill.StringHelper;
import Polyfill.ThoiGian;

public class Luong {
    public Luong(long hienTai) {
        this.hienTai = tuongLai = hienTai;
    }

    protected Luong(long hienTai, long tuongLai, long tongDaTra, ThoiGian modified) {
        this.hienTai = hienTai;
        this.tuongLai = tuongLai;
        this.tongDaTra = tongDaTra;
        this.modified = modified;
    }

    public long getHienTai() {
        return hienTai;
    }

    public long getTuongLai() {
        return tuongLai;
    }

    public Luong nangLuong(long tuongLai) {
        hienTai = this.tuongLai;
        this.tuongLai = tuongLai;
        modified = ThoiGian.now();
        return this;
    }

    public Luong traLuong() {
        tongDaTra += hienTai;
        return this;
    }

    public long getTongDaTra() {
        return tongDaTra;
    }

    public ThoiGian getModified() {
        return modified;
    }

    // 2500000 |3000000| 7500000 |25/11/2022 23:15:00
    // ^hientai ^tuonglai ^tongdatra ^ last modified
    public static Luong parseLuong(String inp) {
        String[] _a = StringHelper.lineSplit(inp);
        return new Luong(Long.parseLong(_a[0]), Long.parseLong(_a[1]), Long.parseLong(_a[2]),
                ThoiGian.parseTG(_a[3]));
    }

    public String toScreen() {
        return StringHelper.liner(StringHelper.itemer("Hien tai", hienTai),
                StringHelper.itemer("Tuong lai", tuongLai),
                StringHelper.itemer("Tong da tra", tongDaTra),
                StringHelper.itemer("Cap nhat", modified));
    }

    @Override
    public String toString() {
        return StringHelper.concater("|", hienTai, tuongLai, tongDaTra, modified);
    }

    private long hienTai, tuongLai, tongDaTra;
    private ThoiGian modified;
}
