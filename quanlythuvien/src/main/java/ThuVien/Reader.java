package ThuVien;

import Polyfill.StringHelper;
import Polyfill.ThoiGian;

public class Reader extends Account implements IDataProcess<Reader>, IDashboard {
    public Reader(int id, String username) {
        super(id, username);
    }

    protected Reader(int id, String username, ThoiGian registration) {
        super(id, username, registration);
    }

    public Card getCard() {
        return card;
    }

    protected Reader setCard(Card card) {
        this.card = card;
        return this;
    }

    public int dashboard() {
        while (true) {
            System.out.println("Dang dang nhap voi tu cach Doc Gia");
            System.out.println(this.toString());
            int n = StringHelper.acceptInput("Muon", "Tra", "Xem hoa don", "Dang xuat");
            if (n <= 0) {
                System.out.println("Unexpected input");
                break;
            }
            switch (n) {
                case 1 -> {
                    VirtualHoaDon vhd = Global.hoadons.createVirtual(null);
                    while (true) {
                        int dn = Integer.parseInt(StringHelper.acceptLine("Nhap id tai lieu"));
                        if (dn == 0) {
                            break;
                        }
                        dn = Global.documents.search(dn);
                        if (dn == -1) {
                            System.out.println("Khong tim thay tai lieu");
                            continue;
                        }
                        System.out.println("Tim thay tai lieu");
                        Document d = Global.documents.getById(dn);
                        System.out.println(d.toString());
                        if (!vhd.addBorrows(d)) {
                            System.out.println("Tai lieu khong kha dung cho muon");
                        }
                    }
                    System.out.println("Xem lai danh sach chuan bi muon");
                    System.out.println(vhd.toString());
                    if (StringHelper.acceptInput("Ok", "Bo") == 1) {
                        Global.hoadons.acceptVirtual(vhd);
                        System.out.println("Dem id hoa don ao cho thu ngan de tiep tuc");
                    }
                }
                case 2 -> {
                    System.out.println("Chua co chuc nang tu tra, hay dem id hoa don len cho thu ngan");
                }
                case 3 -> {
                    Global.hoadons.instance.stream().filter(e -> e.getCreator().getId() == this.getId())
                            .forEach(e -> System.out.println(e.toString()));
                }
                case 4 -> {
                    System.out.println("Se dang xuat");
                    return 0;
                }
            }
        }
        return 1;
    }

    public static Reader fromBlob(String[] inp) {
        int id = Integer.parseInt(inp[0]);
        String username = inp[1];
        String password = inp[2];
        String name = inp[3];
        ThoiGian regtime = ThoiGian.parseTG(inp[4]);
        ThoiGian borntime = ThoiGian.parseTG(inp[5]);
        String phone = inp[6];
        String email = inp[7];
        String address = inp[8];
        Card card = Global.cards.getById(Integer.parseInt(inp[9]));
        Reader __ = new Reader(id, username, regtime).setCard(card);
        __.setName(name).setBirth(borntime).setPhone(phone).setEmail(email).setAddress(address);
        __.changePassword(null, password);
        return __;
    }

    public String[] toBlob() {
        return new String[] { String.valueOf(getId()), getUsername(), getPassword(), getName(),
                getRegistration().toString(), getBirth().toString(), getPhone(), getEmail(), getAddress(),
                String.valueOf(card.getId()) };
    }

    @Override
    public String toString() {
        return StringHelper.liner(super.toString(),
                StringHelper.itemer("Username", getUsername()),
                StringHelper.itemer("Registration date", getRegistration()),
                StringHelper.itemer("Card", getCard()));
    }

    private Card card;
}
