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
        int id = Integer.parseInt(StringHelper.acceptLine("Nhap id hoa don ao (duoc tao boi doc gia)"));
        int n = virtuals.stream().mapToInt(e -> e.getId()).filter(e -> e == id).findAny().orElse(-1);
        if (n == -1) {
            System.out.println("Khong tim thay hoa don ao");
        } else {
            try {
                System.out.println("Tim thay hoa don ao");
                virtuals.at(n).toString();
                HoaDon __ = new HoaDon(genNextId(), virtuals.at(n));
                String tg = StringHelper.acceptLine("Nhap ngay hoan tra (mac dinh 7 ngay)");
                if (StringHelper.isNullOrBlank(tg)) {
                    __.setDeadline(ThoiGian.now().modNgay(7));
                } else {
                    __.setDeadline(ThoiGian.parseTG(tg));
                }
                System.out.println("Xac nhan thanh toan:");
                int m = StringHelper.acceptInput("Da thu tien", "Chay tron roi");
                if (m == 1) {
                    instance.push_back(__);
                    virtuals.erase(n);
                }
                return __;
            } catch (Exception e) {
                LOGGER.log(Level.WARNING, "Input error", e);
                throw e;
            }
        }
        return null;
    }

    @Override
    public HoaDon remove() {
        throw new UnsupportedOperationException("Khong cho phep xoa hoa don");
    }

    @Override
    public HoaDon edit() {
        HoaDon __ = null;
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
                    System.out.println(hd[m].toString());
                    __ = hd[m].edit();
                }
                case 2 -> {
                    id = Integer.parseInt(StringHelper.acceptLine("Nhap ID hoa don"));
                    __ = instance.stream()
                            .filter(e -> e.getId() == id).findAny().orElse(null);
                    if (__ == null) {
                        System.out.println("Khong tim thay hoa don");
                    } else {
                        System.out.println("Dang thuc hien edit hoa don");
                        System.out.println(__.toString());
                        __.edit();
                    }
                }
                default -> {
                    System.out.println("Huy edit hoa don");
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Input error", e);
            throw e;
        }
        return __;
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
        return new HoaDons(inp);
    }

    private PFArray<VirtualHoaDon> virtuals;
    private int id_virtuals_counter = 0;
}
