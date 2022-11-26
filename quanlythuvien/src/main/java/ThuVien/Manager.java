package ThuVien;

import Polyfill.PFArray;

public class Manager extends StaffImpl {
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
    public void fromBlob(String[] cashier) {
        int id = Integer.parseInt(cashier[0]);
        String username = cashier[1];
        String password = cashier[2];

    }
    // TODO: manager methods
    private PFArray<Cashier> cashiers = new PFArray<>();
    private static final long cashierBonus = 5000;
}
