package ThuVien;

import Polyfill.PFArray;

public class Manager extends StaffImpl implements IManagement {
    public Manager(int id, String username) {
        super(id, username);
    }

    public void employ(Cashier employee) {
        managing.push_back(employee);
    }

    // Manager must do a search to find out the index to remove
    public boolean fire(int managing_index) {
        if (managing_index < 0 || managing_index > managing.size()) {
            return false;
        }
        managing.erase(managing_index);
        return true;
    }
    public void fromBlob(String[] cashier) {
        int id = Integer.parseInt(cashier[0]);
        String username = cashier[1];
        String password = cashier[2];

    }
    // TODO: manager methods
    private PFArray<Cashier> managing = new PFArray<>();
    private static int id_counter = 0;
}
