package ThuVien;

import Polyfill.PFArray;
import Polyfill.ThoiGian;

import static ThuVien.DangNhap.scanner;

public class Readers extends Management<Reader> {
    public Readers() {
        super();
    }

    public Readers(Reader[] r) {
        super(r);
    }

    public Readers(PFArray<String[]> blob, Cards cards_instance) {
        this();
        blob.stream().forEach(e -> instance.push_back(Reader.fromBlob(e, cards_instance)));
        updateCounter();
    }

    public int menuEdit() {
        System.out.println("1. Ten");
        System.out.println("2. Ngay sinh");
        System.out.println("3. Dia chi");
        System.out.println("4. So dien thoai");
        System.out.println("5. Email");
        return Integer.parseInt(scanner.nextLine());
    }

    private Reader accessInpReader() {
        System.out.println("Nhap ten: ");
        String name = scanner.nextLine();
        System.out.println("Nhap ma: ");
        int id = Integer.parseInt(scanner.nextLine());
        Reader reader = new Reader(id, name);

        System.out.println("Nhap ten: ");
        reader.setName(scanner.nextLine());

        System.out.println("Nhap ngay sinh: ");
        reader.setBirth(ThoiGian.parseTG(scanner.nextLine()));

        System.out.println("Nhap dia chi: ");
        reader.setAddress(scanner.nextLine());

        System.out.println("Nhap so dien thoai: ");
        reader.setPhone(scanner.nextLine());

        System.out.println("Nhap email: ");
        reader.setEmail(scanner.nextLine());

        return reader;
    }
    public Reader add() {
        // TODO: accept input

        Reader __ = accessInpReader();
        // .setAbc(...)
        instance.push_back(__);
        return null;
    }
    public Reader remove() {
        //TODO:accept input
        // abc = search()
        // if length == 1 instance.erase()...
        return null;
    }
    public Reader edit() {
        //TODO:accept inpiut
        // abc = search()
        // if length == 1 instance[i].setAbc.setXyz
        return null;
    }
    public int[] search() {
        // TODO:accept input
        return null;
    }
    // public PFArray<String[]> toBatchBlob() {}; already implemented

    public static Readers fromBatchBlob(PFArray<String[]> inp, Cards cards_instance) {
        return new Readers(inp, cards_instance);
    }
}
