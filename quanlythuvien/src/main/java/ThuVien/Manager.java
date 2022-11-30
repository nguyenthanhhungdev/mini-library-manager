package ThuVien;

import Polyfill.ThoiGian;

public class Manager extends StaffImpl implements IDataProcess<Manager> {
    public Manager(int id, String username) {
        this(id, username, ThoiGian.now());
    }

    protected Manager(int id, String username, ThoiGian regtime) {
        super(id, username, regtime);
    }

    // thue them nhan vien
    public Cashier employ() {
        return Global.cashiers.add();
    }

    // duoi viec nhan vien
    public Cashier fire() {
        return Global.cashiers.remove();
    }

    public long calcSocialCredit() {
        return getPureLuong() / 500 + Global.cashiers.size() * cashierBonus;
    }

    public String[] toBlob() {
        return new String[] { String.valueOf(getId()), getUsername(), getPassword(), getName(),
                getRegistration().toString(), getBirth().toString(), getPhone(), getEmail(), getAddress(),
                getTruc().toString(), getLuong().toString() };
    }

    public static Manager fromBlob(String[] inp) {
        int id = Integer.parseInt(inp[0]);
        String username = inp[1];
        String password = inp[2];
        String name = inp[3];
        ThoiGian regtime = ThoiGian.parseTG(inp[4]);
        ThoiGian borntime = ThoiGian.parseTG(inp[5]);
        String phone = inp[6];
        String email = inp[7];
        String address = inp[8];
        CaTruc catruc = CaTruc.parseCaTruc(inp[9]);
        Luong luong = Luong.parseLuong(inp[10]);
        Manager __ = new Manager(id, username, regtime);
        __.changePassword(null, password);
        __.setName(name).setBirth(borntime).setPhone(phone).setEmail(email).setAddress(address);
        __.setTruc(catruc).setLuong(luong);
        return __;
    }

    private static final long cashierBonus = 500;
}
