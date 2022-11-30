package ThuVien;

import Polyfill.PFArray;
import Polyfill.StringHelper;
import Polyfill.ThoiGian;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Authors extends Management<Author> {
    private static final Logger LOGGER = Logger.getLogger(Authors.class.getName());

    public Authors() {
        super();
    }

    public Authors(Author[] r) {
        super(r);
    }

    public Authors(PFArray<String[]> blob) {
        this();
        blob.stream().forEach(e -> instance.push_back(Author.fromBlob(e)));
        updateCounter();
    }

    public Author add() {
        Author author = new Author(genNextId());
        author.setName(StringHelper.acceptLine("Nhap ten tac gia: "));
        author.setLanguage(Languages.parseLang(StringHelper.acceptLine("Nhap ngon ngu: ")));
        author.setWebsite(StringHelper.acceptLine("Nhap trang web"));
        author.setBirth(ThoiGian.parseTG(StringHelper.acceptLine("Nhap ngay sinh")));
        author.setAddress(StringHelper.acceptLine("Nhap dia chi: "));
        author.setEmail(StringHelper.acceptLine("Nhap email: "));
        author.setPhone(StringHelper.acceptLine("Nhap so dien thoai: "));
        instance.push_back(author);
        return author;
    }

    public int promptSearch() {
        int n;
        try {
            n = search(Integer.parseInt(StringHelper.acceptLine("Nhap id tac gia: ")));
            if (n == -1) {
                System.out.println("Tim kiem khong co ket qua: ");
            } else {
                System.out.println("Tim thay tac gia: ");
                System.out.println(instance.at(n).toString());
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Input error", e);
            throw e;
        }

        return n;
    }

    public Author remove() {
        Author author = null;
        int n;
        try {
            n = promptSearch();
            if (n == -1) {
                System.out.println("Tim kiem that bai, remove tac gia that bai");
            } else {
                System.out.println("Xac nhan xoa tac gia: ");
                System.out.println(instance.at(n).toString());
                int m = StringHelper.acceptInput("Co", "Suy nghi lai");
                if (m == 1) {
                    author = instance.erase(n);
                    System.out.println("Da xoa tac gia: ");
                    System.out.println(author.toString());
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Co loi xay ra, remove tac gia khong thanh cong", e);
            throw e;
        }
        return author;
    }

    public int menuEdit() {
        return StringHelper.acceptInput("Ten tac gia", "Ngon ngu", "Website", "Ngay sinh", "email", "Dia chi",
                "Dien thoai");
    }

    public Author edit() {
        // TODO:accept inpiut
        // abc = search()
        // if length == 1 instance[i].setAbc.setXyz
        Author author = null;
        int n;
        try {
            n = promptSearch();
            if (n == -1) {
                System.out.println("Tim kiem that bai, edit tac gia that bai");
            } else {
                int m;
                do {
                    author = instance.at(n);
                    System.out.println("Dang thao tac tac gia: ");
                    System.out.println(author.toString());
                    System.out.println("Chon thao tac");
                    switch (m = menuEdit()) {
                        case 1 -> author.setName(Global.scanner.nextLine());
                        case 2 -> author.setLanguage(Languages.parseLang(Global.scanner.nextLine()));
                        case 3 -> author.setWebsite(Global.scanner.nextLine());
                        case 4 -> author.setBirth(ThoiGian.parseTG(Global.scanner.nextLine()));
                        case 5 -> author.setEmail(Global.scanner.nextLine());
                        case 6 -> author.setAddress(Global.scanner.nextLine());
                        case 7 -> author.setPhone(Global.scanner.nextLine());
                        default -> {
                            m = -1;
                            System.out.println("Ket thuc edit tac gia");
                        }
                    }
                } while (m > 0);
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Co loi xay ra, edit doc gia that bai", e);
            throw e;
        }
        return author;
    }

    public int[] search() {
        throw new UnsupportedOperationException("Chuc nang chua duoc code do khong du thoi gian");
    }
    // public PFArray<String[]> toBatchBlob() {}; already implemented

    public static Authors fromBatchBlob(PFArray<String[]> inp) {
        return new Authors(inp);
    }
}
