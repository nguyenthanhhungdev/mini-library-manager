package ThuVien;

public class SachNuocNgoai extends TaiLieu {
    private NgonNgu ngonNgu;
    private String tenNguoiDich;

    public NgonNgu getNgonNgu() {
        return ngonNgu;
    }

    public void setNgonNgu(NgonNgu ngonNgu) {
        this.ngonNgu = ngonNgu;
    }

    public String getTenNguoiDich() {
        return tenNguoiDich;
    }

    public void setTenNguoiDich(String tenNguoiDich) {
        this.tenNguoiDich = tenNguoiDich;
    }

    public SachNuocNgoai(long ma, String ten, int soLuongBanSao, boolean trangThai, TacGia tacGia, int namXuatBan,
            NgonNgu ngonNgu, String tenNguoiDich) {
        super(ma, ten, soLuongBanSao, trangThai, tacGia, namXuatBan);
        this.ngonNgu = ngonNgu;
        this.tenNguoiDich = tenNguoiDich;
    }

    public SachNuocNgoai(NgonNgu ngonNgu, String tenNguoiDich) {
        this.ngonNgu = ngonNgu;
        this.tenNguoiDich = tenNguoiDich;
    }

    public SachNuocNgoai() {
    }
}
