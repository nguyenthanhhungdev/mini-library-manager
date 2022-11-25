package ThuVien;

public class LaoCong extends StaffImpl {
    public LaoCong(int id, String username) {
        super(id, username);
    }

    protected long calcSocialCredit() {
        return getPureLuong();
    }
}
