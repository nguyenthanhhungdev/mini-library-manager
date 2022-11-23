package ThuVien;

public class NhaXuatBan {
    private String ten;
    private String ma;

    public NhaXuatBan(String ten, String ma) {
        this.ten = ten;
        this.ma = ma;
    }

    public NhaXuatBan() {
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }
}
