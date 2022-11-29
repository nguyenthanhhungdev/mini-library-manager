package ThuVien;

import Polyfill.PFArray;
import Polyfill.StringHelper;
import Polyfill.ThoiGian;

import java.util.logging.Level;
import java.util.logging.Logger;

import static ThuVien.DangNhap.scanner;

public class Readers extends Management<Reader> {
    private static final Logger LOGGER = Logger.getLogger(Readers.class.getName());

    public Readers(Cards cards_instance) {
        super();
        this.cards_instance = cards_instance;
    }

    public Readers(PFArray<String[]> blob, Cards cards_instance) {
        this(cards_instance);
        blob.stream().forEach(e -> instance.push_back(Reader.fromBlob(e, cards_instance)));
        updateCounter();
    }

    public int menuEdit() {
        return StringHelper.acceptInput("Ten", "Ngay sinh", "Dia chi", "So dien thoai", "Email");
    }

    public Reader add() {
        // TODO: accept input

        Reader reader = new Reader(genNextId(), StringHelper.acceptLine("Nhap ten doc gia: "));

        reader.setName(StringHelper.acceptLine("Nhap ten tac gia: "));

        reader.setBirth(ThoiGian.parseTG(StringHelper.acceptLine("Nhap ngay sinh: ")));

        reader.setAddress(StringHelper.acceptLine("Nhap dia chi: "));

        reader.setPhone(StringHelper.acceptLine("Nhap so dien thoai: "));

        reader.setEmail(StringHelper.acceptLine("Nhap email: "));

        return reader;
    }

    private int promptSearch() {
        int n;
        try {
            n = search(Integer.parseInt(StringHelper.acceptLine("Nhap ma doc gia: ")));
            if (n == -1) {
                System.out.println("Tim kiem khong co ket qua: ");
            } else {
                System.out.println("Tim thay doc gia: ");
                instance.at(n).toString();
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Input error", e);
            throw e;
        }

        return n;
    }

    public Reader remove() {
        Reader reader = null;
        int n;
        try {
            n = promptSearch();
            if (n == -1) {
                System.out.println("Tim kiem that bai, remove doc gia that bai");
            } else {
                System.out.println("Xac nhan xoa doc gia: ");
                instance.at(n).toString();
                int m = StringHelper.acceptInput("Co", "Suy nghi lai");
                if (m == 1) {
                    reader = instance.erase(n);
                    System.out.println("Da xoa tac gia: ");
                    reader.toString();
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Co loi xay ra, remove doc gia khong thanh cong", e);
            throw e;
        }
        return reader;
    }

    public Reader edit() {
        Reader reader = null;
        int n;
        try {
            n = promptSearch();
            if (n == -1) {
                System.out.println("Tim kiem that bai, edit doc giakhong thanh cong ");
            } else {
                int m;
                do {
                    reader = instance.at(n);
                    System.out.println("Dang thao tac voi doc gia: ");
                    reader.toString();
                    switch (m = menuEdit())
                    {
                        case 1 -> reader.setName(StringHelper.acceptLine("Nhap ten doc gia: "));
                        case 2 -> reader.setBirth(ThoiGian.parseTG(StringHelper.acceptLine("Nhap ngya sinh doc gia: ")));
                        case 3 -> reader.setAddress(StringHelper.acceptLine("Nhap dia chi tac gia: "));
                        case 4 -> reader.setPhone(StringHelper.acceptLine("Nhap so dien thoai: "));
                        case 5 -> reader.setEmail(StringHelper.acceptLine("Nhap email: "));
                        default -> {
                            m = -1;
                            System.out.println("Ket thuc edit doc gia");
                        }
                    }
                } while (m < 0);
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Co xay ra loi, edit doc gia that bai", e);
        }
        return reader;
    }

    public int[] search() {
        throw new UnsupportedOperationException("Chuc nang nay chua duoc code do khong du thoi gian");
    }
    // public PFArray<String[]> toBatchBlob() {}; already implemented

    public static Readers fromBatchBlob(PFArray<String[]> inp, Cards cards_instance) {
        return new Readers(inp, cards_instance);
    }

    private Cards cards_instance;
}
