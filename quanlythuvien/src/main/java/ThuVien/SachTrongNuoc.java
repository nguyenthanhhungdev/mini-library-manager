package ThuVien;

public class SachTrongNuoc extends TaiLieu {
    private NhaXuatBan nhaXuatBan;

    public NhaXuatBan getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(NhaXuatBan nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public SachTrongNuoc(long ma, String ten, int soLuongBanSao, boolean trangThai, TacGia tacGia, int namXuatBan,
            NhaXuatBan nhaXuatBan) {
        super(ma, ten, soLuongBanSao, trangThai, tacGia, namXuatBan);
        this.nhaXuatBan = nhaXuatBan;
    }

    public SachTrongNuoc(NhaXuatBan nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public SachTrongNuoc() {
    }
}
