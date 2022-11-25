package ThuVien;

public interface IManagement {
    public void add(People p);
    public void remove(People p);
    public People[] search(People p);
    public void edit(People p);
}
