package ThuVien;

import Polyfill.StringHelper;

public abstract class StaffImpl extends Account {
    StaffImpl(int id, String username) {
        super(id, username);
    }

    public long getPureLuong() {
        return luong.getHienTai();
    }

    @Override
    public String toString() {
        return StringHelper.liner(super.toString(),
                StringHelper.itemer("Ca truc", truc),
                StringHelper.itemer("Luong", luong));
    }

    protected abstract long traLuong();

    protected CaTruc truc;
    protected Luong luong;
}
