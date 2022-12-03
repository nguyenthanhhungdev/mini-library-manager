package ThuVien;

import Polyfill.StringHelper;
import Polyfill.ThoiGian;

public class Cashier extends StaffImpl implements IDataProcess<Cashier>, IDashboard {
    public Cashier(int id, String username) {
        super(id, username);
    }

    protected Cashier(int id, String username, ThoiGian registration) {
        super(id, username, registration);
    }

    protected long calcSocialCredit() {
        return getPureLuong() / 1000 + completionCount * completionBonus;
    }

    protected Cashier resetCompletionCount() {
        completionCount = 0;
        return this;
    }

    private void cashDone() {
        ++completionCount;
    }

    public int getCompletionCount() {
        return completionCount;
    }

    protected Cashier setCompletionCount(int completionCount) {
        this.completionCount = completionCount;
        return this;
    }

    private void menuDocGia() {
        int choice = StringHelper.acceptInput("Them doc gia", "Xoa doc gia thanh vien", "Chinh sua doc gia");
        switch (choice) {
            case 1 -> {
                Global.readers.add();
            }
            case 2 -> {
                Global.readers.remove();
            }
            case 3 -> {
                Global.readers.edit();
            }
        }
    }

    private void menuTacGia() {
        int choice = StringHelper.acceptInput("Them tac gia", "Xoa tac gia", "Chinh sua tac gia");
        switch (choice) {
            case 1 -> {
                Global.authors.add();
            }
            case 2 -> {
                Global.authors.remove();
            }
            case 3 -> {
                Global.authors.edit();
            }
        }
    }

    private void menuTaiLieu() {
        int choice = StringHelper.acceptInput("Them tai lieu", "Xoa tai lieu", "Sua tai lieu");
        switch (choice) {
            case 1 -> {
                Global.documents.add();
            }
            case 2 -> {
                Global.documents.remove();
            }
            case 3 -> {
                Global.documents.edit();
            }
        }
    }

    public int dashboard() {
        while (true) {
            System.out.println("Dang nhap voi tu canh Thu Ngan");
            System.out.println(this.toString());
            System.out.println("Thao tac voi: ");
            int n = StringHelper.acceptInput("Doc gia", "Tac gia", "Tai lieu", "Cho muon sach", "Nhan tra sach",
                    "Dang xuat", "Tim kiem");
            if (n <= 0) {
                System.out.println("Unexpected input");
                break;
            }
            switch (n) {
                case 1 -> {
                    menuDocGia();
                }
                case 2 -> {
                    menuTacGia();
                }
                case 3 -> {
                    menuTaiLieu();
                }
                case 4 -> {
                    if (Global.hoadons.add() != null) {
                        cashDone();
                    }
                }
                case 5 -> {
                    if (Global.hoadons.edit() != null) {
                        cashDone();
                    }
                }
                case 6 -> {
                    System.out.println("Se dang xuat");
                    return 0;
                }
                case 7 -> {
                    Global.identityLookup();
                }
            }
        }
        return 1;
    }

    public static Cashier fromBlob(String[] inp) {
        int id = Integer.parseInt(inp[0]);
        String username = inp[1];
        String password = inp[2];
        String name = inp[3];
        ThoiGian regtime = ThoiGian.parseTG(inp[4]);
        ThoiGian borntime = ThoiGian.parseTG(inp[5]);
        String phone = inp[6];
        String email = inp[7];
        String address = inp[8];
        CaTruc catruc = CaTruc.parseCaTruc(inp[9]); // someone worked very hard so this could happen
        Luong luong = Luong.parseLuong(inp[10]);
        int completion = Integer.parseInt(inp[11]);
        Cashier toRet = new Cashier(id, username, regtime);
        toRet.changePassword(null, password);
        toRet.setName(name).setBirth(borntime).setPhone(phone).setEmail(email).setAddress(address);
        toRet.setTruc(catruc).setLuong(luong);
        toRet.setCompletionCount(completion);
        return toRet;
    }

    public String[] toBlob() {
        return new String[] { String.valueOf(getId()), getUsername(), getPassword(), getName(),
                getRegistration().toString(), getBirth().toString(), getPhone(), getEmail(), getAddress(),
                getTruc().toString(), getLuong().toString(), String.valueOf(getCompletionCount()) };
    }

    public String toString() {
        return StringHelper.liner(super.toString(),
                StringHelper.itemer("Completition count", getCompletionCount()));
    }

    private int completionCount = 0;
    // moi hoa don thanh toan dc them 5 tram
    private static final long completionBonus = 500;
}
