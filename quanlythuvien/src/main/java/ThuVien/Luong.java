package ThuVien;

import Polyfill.StringHelper;
import Polyfill.ThoiGian;

public class Luong {
    public Luong(long hienTai) {
        this.hienTai = tuongLai = hienTai;
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

    @Override
    public String toString() {
        return StringHelper.liner(StringHelper.itemer("Hien tai", hienTai),
                StringHelper.itemer("Tuong lai", tuongLai),
                StringHelper.itemer("Tong da tra", tongDaTra),
                StringHelper.itemer("Cap nhat", modified));
    }

    private long hienTai, tuongLai, tongDaTra;
    private ThoiGian modified;

}
