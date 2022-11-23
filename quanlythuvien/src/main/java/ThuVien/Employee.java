package ThuVien;

public class Employee extends Reader {
    public Employee(String username, String password) {
        this(++id_counter, username, password);
    }

    public Employee(long id, String username, String password) {
        super(id, username, password);
    }

    public long getWage() {
        return wage;
    }

    public void setWage(long wage) {
        this.wage = wage;
    }
    // TODO: employee methods
    private long wage;
    private static long id_counter = 0;
}
