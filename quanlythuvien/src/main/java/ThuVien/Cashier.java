package ThuVien;

public class Cashier extends StaffImpl {
    public Cashier(int id, String username) {
        super(id, username);
    }

    protected long traLuong() {
        long luong = getPureLuong() + completionCount * completionBonus;
        completionCount = 0;
        return luong;
    }

    private void cashDone() {
        ++completionCount;
    }

    private int completionCount = 0;
    // moi hoa don thanh toan dc them 5 nghin
    private static long completionBonus = 5000;
}
