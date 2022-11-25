package ThuVien;

public interface IDataProcess<T> {
    public String[] toBlob();

    public T fromBlob(String[] inp);
}
