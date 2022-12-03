package ThuVien;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import Polyfill.PFArray;
import Polyfill.StringHelper;
import Polyfill.ThoiGian;

public class HoaDons extends Management<HoaDon> {
    private static final Logger LOGGER = Logger.getLogger(HoaDons.class.getName());

    public HoaDons() {
        super();
    }

    public HoaDons(HoaDon[] r) {
        super(r);
    }

    public HoaDons(PFArray<String[]> blob) {
        this();
        blob.stream().forEach(e -> instance.push_back(HoaDon.fromBlob(e)));
        updateCounter();
    }

    @Override
    public HoaDon add() {
        int id = StringHelper.acceptKey("Nhap id hoa don ao (duoc tao boi doc gia)");
        if (id == -1) {
            return null;
        }
        int pos = virtuals.stream().mapToInt(e -> e.getId()).filter(e -> e == id).findAny().orElse(-1);
        if (pos == -1) {
            System.out.println("Khong tim thay hoa don ao");
            return null;
        }
        System.out.println("Tim thay hoa don ao");
        virtuals.at(pos).toString();
        HoaDon toAdd = new HoaDon(genNextId(), virtuals.at(pos));
        String ngayTra = StringHelper.acceptLine("Nhap ngay hoan tra (mac dinh 7 ngay)");
        if (StringHelper.isNullOrBlank(ngayTra)) {
            toAdd.setDeadline(ThoiGian.now().modNgay(7));
        } else {
            toAdd.setDeadline(ThoiGian.parseTG(ngayTra));
        }
        System.out.println("Xac nhan thanh toan:");
        int m = StringHelper.acceptInput("Da thu tien", "Chay tron roi");
        if (m == 1) {
            instance.push_back(toAdd);
            virtuals.erase(pos);
            System.out.println("Thanh toan da xac nhan");
            return toAdd;
        }
        System.out.println("Thanh toan huy bo");
        return toAdd;
    }

    @Override
    public HoaDon remove() {
        throw new UnsupportedOperationException("Khong cho phep xoa hoa don");
    }

    @Override
    public HoaDon edit() {
        HoaDon toEdit = null;
        try {
            int id;
            switch (StringHelper.acceptInput("ID doc gia", "ID hoa don")) {
                case 1 -> {
                    id = Global.readers.promptSearch();
                    // trung id doc gia va hoa don van con tai lieu chua tra
                    HoaDon[] hd = instance.stream()
                            .filter(e -> e.getCreator().getId() == id && e.getHoldings().size() > 0)
                            .toArray(HoaDon[]::new);
                    int m = 0;
                    if (hd.length > 1) {
                        System.out.println("Chon trong cac hoa don");
                        if ((m = StringHelper.acceptInput(StringHelper.obj2str((Object) hd))) < 1) {
                            throw new IllegalArgumentException("Input error");
                        }
                    }
                    System.out.println("Dang thuc hien edit hoa don");
                    System.out.println(hd[m - 1].toString());
                    toEdit = hd[m - 1].edit();
                }
                case 2 -> {
                    id = Integer.parseInt(StringHelper.acceptLine("Nhap ID hoa don"));
                    toEdit = instance.stream()
                            .filter(e -> e.getId() == id).findAny().orElse(null);
                    if (toEdit == null) {
                        System.out.println("Khong tim thay hoa don");
                    } else {
                        System.out.println("Dang thuc hien edit hoa don");
                        System.out.println(toEdit.toString());
                        toEdit.edit();
                    }
                }
                default -> {
                    System.out.println("Huy edit hoa don");
                }
            }
        } catch (RuntimeException e) {
            LOGGER.log(Level.WARNING, "Likely input parse error in HoaDons::add", e);
            LOGGER.info("The editing operation is cancelled");
        }
        return toEdit;
    }

    public int[] search() {
        throw new UnsupportedOperationException("Chuc nang chua duoc code do khong du thoi gian");
    }

    public VirtualHoaDon createVirtual(Reader r) {
        return new VirtualHoaDon(++id_virtuals_counter, r);
    }

    public HoaDons acceptVirtual(VirtualHoaDon vhd) {
        virtuals.push_back(vhd);
        return this;
    }

    protected VirtualHoaDon removeVirtualById(int id) {
        int n = virtuals.stream().mapToInt(e -> e.getId()).filter(e -> e == id).findAny().orElse(-1);
        if (n == -1) {
            return null;
        } else {
            return virtuals.erase(n);
        }
    }

    public HoaDon confirmVirtual(int virtual_id, ThoiGian deadline) {
        int vhdn = IntStream.range(0, virtuals.size()).filter(e -> virtuals.at(e).getId() == virtual_id).findAny()
                .orElse(-1);
        if (vhdn == -1) {
            LOGGER.warning("Virtual HoaDon id not found");
            return null;
        }
        HoaDon hd = new HoaDon(genNextId(), virtuals.at(vhdn));
        hd.setDeadline(deadline);
        instance.push_back(hd);
        virtuals.erase(vhdn);
        return hd;
    }

    public HoaDon confirmVirtual(int virtual_id) {
        return confirmVirtual(virtual_id, ThoiGian.now());
    }

    public static HoaDons fromBatchBlob(PFArray<String[]> inp) {
        if (inp.size() < 1) {
            LOGGER.warning("No entries");
        } else {
            LOGGER.info(String.format("Batching %d x %d blob", inp.size(), inp.at(0).length));
        }
        return new HoaDons(inp);
    }

    private PFArray<VirtualHoaDon> virtuals;
    private int id_virtuals_counter = 0;
}
