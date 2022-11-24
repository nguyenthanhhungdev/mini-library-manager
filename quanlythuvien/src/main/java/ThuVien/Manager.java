package ThuVien;

import Polyfill.PFArray;

public class Manager extends Employee {
    public Manager(String username, String password) {
        this(++id_counter, username, password);
    }

    public Manager(long id, String username, String password) {
        super(id, username, password);
    }

    public void employ(Employee employee) {
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

    // TODO: manager methods
    private PFArray<Employee> managing = new PFArray<>();
    private static long id_counter = 0;
}
