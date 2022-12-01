package ThuVien;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import Polyfill.PFArray;
import Polyfill.StringHelper;
import Polyfill.ThoiGian;

public class Managers extends Management<Manager> implements ILogin {
    private static final Logger LOGGER = Logger.getLogger(Managers.class.getName());

    public Managers() {
        super();
    }

    public Managers(Manager[] manager) {
        super(manager);
    }

    public Managers(PFArray<String[]> blob) {
        this();
        blob.stream().forEach(e -> instance.push_back(Manager.fromBlob(e)));
        updateCounter();
    }

    @Override
    public Manager add() {
        String username = StringHelper.acceptLine("Ten tai khoan");
        Manager __ = new Manager(genNextId(), username);
        __.changePassword(null, StringHelper.acceptLine("Mat khau"));
        __.setName(StringHelper.acceptLine("Ten"));
        __.setBirth(ThoiGian.parseTG(StringHelper.acceptLine("Ngay sinh")));
        __.setPhone(StringHelper.acceptLine("So dien thoai"));
        __.setEmail(StringHelper.acceptLine("Email"));
        __.setAddress(StringHelper.acceptLine("Dia chi"));
        __.setTruc(CaTruc.parseCaTruc(StringHelper.acceptLine("Ca truc")));
        String luong = StringHelper.acceptLine("Luong khoi dau");
        __.setLuong(StringHelper.isNullOrBlank(luong) ? new Luong() : new Luong(Long.parseLong(luong)));
        instance.push_back(__);
        return __;
    }

    private int promptSearch() {
        int n;
        try {
            n = search(Integer.parseInt(StringHelper.acceptLine("Nhap id (quan ly)")));
            if (n == -1) {
                System.out.println("Tim kiem khong co ket qua");
            } else {
                System.out.println("Tim thay quan ly:");
                instance.at(n).toString();
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Input error", e);
            throw e;
        }
        return n;
    }

    @Override
    public Manager remove() {
        int n;
        Manager __ = null;
        try {
            n = promptSearch();
            if (n == -1) {
                System.out.println("Tim kiem that bai, remove quan ly that bai");
            } else {
                System.out.println("Xac nhan xoa quan ly:");
                instance.at(n).toString();
                int m = StringHelper.acceptInput("Co", "Suy nghi lai");
                if (m == 1) {
                    __ = instance.erase(n);
                    System.out.println("Da xoa quan ly");
                    System.out.println(__.toString());
                }
            }

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Co loi xay ra, remove quan ly that bai", e);
        }
        return __;
    }

    @Override
    public Manager edit() {
        int n;
        Manager __ = null;
        try {
            n = promptSearch();
            if (n == -1) {
                System.out.println("Tim kiem that bai, edit quan ly that bai");
            } else {
                int m;
                do {
                    __ = instance.at(n);
                    System.out.println("Dang thao tac edit quan ly:");
                    System.out.println(__.toString());
                    System.out.println("Chon thao tac");
                    switch (m = StringHelper.acceptInput("Ten", "Ngay sinh", "So dien thoai", "Email", "Dia chi",
                            "Ca truc", "Thay doi mat khau")) {
                        case 1:
                            __.setName(StringHelper.acceptLine("Nhap ten"));
                            break;
                        case 2:
                            __.setBirth(ThoiGian.parseTG(StringHelper.acceptLine("Nhap ngay sinh")));
                            break;
                        case 3:
                            __.setPhone(StringHelper.acceptLine("Nhap sdt"));
                            break;
                        case 4:
                            __.setEmail(StringHelper.acceptLine("Nhap email"));
                            break;
                        case 5:
                            __.setAddress(StringHelper.acceptLine("Nhap dia chi"));
                            break;
                        case 6:
                            __.setTruc(CaTruc.parseCaTruc(StringHelper.acceptLine("Nhap ca truc")));
                            break;
                        case 7:
                            String old = StringHelper.acceptLine("Nhap mat khau cu");
                            if (__.changePassword(old, StringHelper.acceptLine("Nhap mat khau moi")) == true) {
                                System.out.println("Thay doi mat khau thanh cong");
                            } else {
                                System.out.println("Mat khau cu sai");
                            }
                            break;
                        case -1:
                        default:
                            m = -1;
                            System.out.println("Ket thuc edit quan ly");
                            break;
                    }
                } while (m > 0);
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Co loi xay ra, edit quan ly that bai", e);
        }
        return __;
    }

    public int login() {
        String username = StringHelper.acceptLine("Nhap ten tai khoan (quan ly)");
        int found = IntStream.range(0, instance.size())
                .filter(e -> username.equalsIgnoreCase(instance.at(e).getUsername())).findAny().orElse(-1);
        if (found == -1) {
            System.out.println("Khong tim thay ten dang nhap (quan ly)");
            return -1;
        }
        System.out.println("Tim thay quan ly");
        System.out.println(instance.at(found).toString());
        String password = StringHelper.acceptLine("Nhap mat khau");
        if (!instance.at(found).checkPassword(password)) {
            System.out.println("Sai mat khau");
            return -1;
        }
        System.out.println("Mat khau chinh xac");
        return found;
    }

    @Override
    public int[] search() {
        throw new UnsupportedOperationException("Chuc nang chua dc code do ko du thoi gian");
    }

    public static Managers fromBatchBlob(PFArray<String[]> inp) {
        return new Managers(inp);
    }
}
