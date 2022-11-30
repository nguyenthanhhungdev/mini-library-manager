package ThuVien;

import Polyfill.StringHelper;
import Polyfill.ThoiGian;

public class Cashier extends StaffImpl implements IDataProcess<Cashier>, IDashboard {
    public Cashier(int id, String username) {
        super(id, username);
    }

    protected Cashier(int id, String username, ThoiGian registration) {
        super(id, username, registration);
    }

    protected long calcSocialCredit() {
        return getPureLuong() / 1000 + completionCount * completionBonus;
    }

    protected Cashier resetCompletionCount() {
        completionCount = 0;
        return this;
    }

    private void cashDone() {
        ++completionCount;
    }

    public int getCompletionCount() {
        return completionCount;
    }

    protected Cashier setCompletionCount(int completionCount) {
        this.completionCount = completionCount;
        return this;
    }
    public int dashboard() {
        //TODO: menu thu ngan
        return 1;
    }
    public static Cashier fromBlob(String[] inp) {
        int id = Integer.parseInt(inp[0]);
        String username = inp[1];
        String password = inp[2];
        String name = inp[3];
        ThoiGian regtime = ThoiGian.parseTG(inp[4]);
        ThoiGian borntime = ThoiGian.parseTG(inp[5]);
        String phone = inp[6];
        String email = inp[7];
        String address = inp[8];
        CaTruc catruc = CaTruc.parseCaTruc(inp[9]); // someone worked very hard so this could happen
        Luong luong = Luong.parseLuong(inp[10]);
        int completion = Integer.parseInt(inp[11]);
        Cashier __ = new Cashier(id, username, regtime);
        __.changePassword(null, password);
        __.setName(name).setBirth(borntime).setPhone(phone).setEmail(email).setAddress(address);
        __.setTruc(catruc).setLuong(luong);
        __.setCompletionCount(completion);
        return __;
    }

    public String[] toBlob() {
        return new String[] { String.valueOf(getId()), getUsername(), getPassword(), getName(),
                getRegistration().toString(), getBirth().toString(), getPhone(), getEmail(), getAddress(),
                getTruc().toString(), getLuong().toString(), String.valueOf(getCompletionCount()) };
    }

    public String toString() {
        return StringHelper.liner(super.toString(),
                StringHelper.itemer("Completition count", getCompletionCount()));
    }

    private int completionCount = 0;
    // moi hoa don thanh toan dc them 5 tram
    private static final long completionBonus = 500;
}
