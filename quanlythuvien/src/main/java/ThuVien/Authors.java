package ThuVien;

import Polyfill.PFArray;
import Polyfill.StringHelper;
import Polyfill.ThoiGian;

import static ThuVien.DangNhap.scanner;

public class Authors extends Management<Author> {
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

    private Author accessInpAuthor() {
        Author author = new Author(++id_counter);
        System.out.println("Nhap ten tac gia: ");
        author.setName(scanner.nextLine());
        System.out.println("Nhap ngo ngu: ");
        author.setLanguage(Languages.parseLang(scanner.nextLine()));
        System.out.println("Nhap web: ");
        author.setWebsite(scanner.nextLine());
        System.out.println("Nhap ngay sinh: ");
        author.setBirth(ThoiGian.parseTG(scanner.nextLine()));
        author.setAddress(scanner.nextLine());
        System.out.println("Nhap email: ");
        author.setEmail(scanner.nextLine());
        System.out.println("Nhap so dien thoai: ");
        author.setPhone(scanner.nextLine());
        return author;
    }

    public int menuEdit() {
        return StringHelper.acceptInput("Ten tac gia", "Ngon ngu", "Website", "Ngay sinh", "email", "Dia chi",
                "Dien thoai");
    }

    public Author add() {
        // TODO: accept input
        Author __ = accessInpAuthor();
        instance.push_back(__);
        return __;
    }

    public Author remove() {
        Author author = accessInpAuthor();
        int index = search(author.getId());
        if (index != -1) {
            instance.erase(index);
        }
        return author;
    }

    public Author edit() {
        // TODO:accept inpiut
        // abc = search()
        // if length == 1 instance[i].setAbc.setXyz
        Author author = accessInpAuthor();
        int index = search(author.getId());
        if (index != -1) {
            author = instance.at(index);

            switch (menuEdit()) {
                case 1 -> author.setName(scanner.nextLine());
                case 2 -> author.setLanguage(Languages.parseLang(scanner.nextLine()));
                case 3 -> author.setWebsite(scanner.nextLine());
                case 4 -> author.setBirth(ThoiGian.parseTG(scanner.nextLine()));
                case 5 -> author.setEmail(scanner.nextLine());
                case 6 -> author.setAddress(scanner.nextLine());
                case 7 -> author.setPhone(scanner.nextLine());
            }
        }
        return author;
    }

    public int[] search() {
        return null;
    }
    // public PFArray<String[]> toBatchBlob() {}; already implemented

    public static Authors fromBatchBlob(PFArray<String[]> inp) {
        return new Authors(inp);
    }
}
