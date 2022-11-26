package ThuVien;

import Polyfill.PFArray;
import Polyfill.StringHelper;

public class Manager extends StaffImpl implements IDataProcess<Manager> {
    public Manager(int id, String username) {
        super(id, username);
    }

    public void employ(Cashier employee) {
        cashiers.push_back(employee);
    }

    // Manager must do a search to find out the index to remove
    public boolean fire(int cashier_index) {
        if (cashier_index < 0 || cashier_index > cashiers.size()) {
            return false;
        }
        cashiers.erase(cashier_index);
        return true;
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
