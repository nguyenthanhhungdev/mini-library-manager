package ThuVien;

public class NhanVien extends ConNguoi implements TraCuuTaiLieu, MuonTaiLieu {
    private long maNhanVien;
    private DanhSachTaiLieu danhSachTaiLieu;
    private DanhSachThe danhSachThe;
    private DanhSachTaiLieuDaMuon danhSachTaiLieuDaMuon;
    private DanhSachTaiLieuDKMuon danhSachTaiLieuDKMuon;

    public NhanVien(long maNhanVien, String hoTen, String ngaySinh, String diaChi) {
        super(hoTen, ngaySinh, diaChi);
        this.maNhanVien = maNhanVien;
    }

    public NhanVien() {

    }

    public long getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = Long.parseLong(maNhanVien);
    }

    @Override
    public String toString() {
        return maNhanVien + "#" + getHoTen() + "#" + getNgaySinh().toString() + "#" + getDiaChi();
    }

    /*
     * Kiểm tra sách trước khi cho mượn
     * kiễm tra xem sách đã cho mượn chưa thông qua thuộc tính trạng thái
     */
    public boolean kiemTraSach() {
        return true;
    }

    /*
     * Kiểm tra thẻ khi cho mượn sách
     * Kiểm tra thẻ có trong danhSachThe hay không thông qua thuộc tính maThe
     * Kiểm tra thẻ có phù hợp hay không qua thuộc tính ngayHetHan trong
     * Nếu đã hết hạn thì gia hạn thẻ
     */
    public boolean kiemTraThe() {
        return true;
    }

    /*
     * Nhập dữ liệu cho độc giả
     * Tạo ra thẻ mới chứa thong6 tin độc giả
     */
    public void lapThe() {

    }

    /*
     * Gia hạn thẻ tìm thông tin thẻ cần gia hạn trong file
     * Thay đổi ngày lập thẻ và ngày hết hạn
     */
    public The giaHanThe() {
        return null;
    }

    @Override
    public void traCuu() {

    }

    @Override
    public void choMuon() {

    }

    @Override
    public void nhapThongTin() {

    }

}
