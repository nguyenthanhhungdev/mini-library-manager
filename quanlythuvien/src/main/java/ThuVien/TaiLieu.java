package ThuVien;
public class TaiLieu {
    private long ma;
    private String ten;
    private int soLuongBanSao;
    private boolean trangThai;
    private TacGia tacGia;
    private int namXuatBan;

    public long getMa() {
        return ma;
    }

    public void setMa(long ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getSoLuongBanSao() {
        return soLuongBanSao;
    }

    public void setSoLuongBanSao(int soLuongBanSao) {
        this.soLuongBanSao = soLuongBanSao;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public TacGia getTacGia() {
        return tacGia;
    }

    public void setTacGia(TacGia tacGia) {
        this.tacGia = tacGia;
    }

    public int getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(int namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    public TaiLieu(long ma, String ten, int soLuongBanSao, boolean trangThai, TacGia tacGia, int namXuatBan) {
        this.ma = ma;
        this.ten = ten;
        this.soLuongBanSao = soLuongBanSao;
        this.trangThai = trangThai;
        this.tacGia = tacGia;
        this.namXuatBan = namXuatBan;
    }

    public TaiLieu() {
    }


}
