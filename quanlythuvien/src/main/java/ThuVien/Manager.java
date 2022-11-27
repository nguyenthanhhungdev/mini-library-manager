package ThuVien;

import Polyfill.PFArray;
import Polyfill.StringHelper;

public class Manager extends StaffImpl implements IDataProcess<Manager> {
    public Manager(int id, String username) {
        super(id, username);
    }
    // thue them nhan vien
    public Cashier employ(Cashiers cashiers_instance) {
        // TODO: accept user input
        Cashier __ = cashiers_instance.add();
        cashiers.push_back(__);
        return __;
    }
    // duoi viec nhan vien
    public boolean fire(Cashiers cashiers_instance) {
        // TODO: accept user input
        Cashier __ = cashiers_instance.remove();
        // find in this cashiers and delete
        return false;
    }

    public long calcSocialCredit() {
        return getPureLuong() / 500 + cashiers.size() * cashierBonus;
    }

    public String[] toBlob() {
        return new String[] { String.valueOf(getId()), getUsername(), getPassword(), getName(),
                getRegistration().toString(), getBirth().toString(), getPhone(), getEmail(), getAddress(),
                getTruc().toString(), getLuong().toString(),
                StringHelper.lv1Join(cashiers.stream().mapToInt(e -> e.getId())) };
    }

    // TODO: manager methods
    private PFArray<Cashier> cashiers = new PFArray<>();
    private static final long cashierBonus = 5000;
}
