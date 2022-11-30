package ThuVien;

import Polyfill.StringHelper;
import Polyfill.ThoiGian;

public class Manager extends StaffImpl implements IDataProcess<Manager>, IDashboard {
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

    public int dashboard() {
        while (true) {
            System.out.println("Dang dang nhap voi tu cach Quan Ly");
            System.out.println(this.toString());
            int n = StringHelper.acceptInput("Thue them nhan vien", "Duoi viec nhan vien", "Chinh sua nhan vien",
                    "Dang xuat");
            if (n <= 0) {
                System.out.println("Unexpected input");
                break;
            }
            switch (n) {
                case 1 -> {
                    Global.cashiers.add();
                }
                case 2 -> {
                    Global.cashiers.remove();
                }
                case 3 -> {
                    Global.cashiers.edit();
                }
                case 4 -> {
                    System.out.println("Se dang xuat");
                    return 0;
                }
            }
        }
        return 1;
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
