package ThuVien;

import java.util.logging.Logger;
import java.util.stream.IntStream;

import Polyfill.PFArray;
import Polyfill.ThoiGian;

public class Documents extends Management<Document> {
    private static final Logger LOGGER = Logger.getLogger(Documents.class.getName());

    public Documents() {
        super();
    }

    public Documents(Document[] r) {
        super(r);
    }

    public Documents(PFArray<String[]> blob) {
        blob.stream().forEach(e -> instance.push_back(switch (Integer.parseInt(e[0])) {
            case 1 -> Newspaper.fromBlob(e);
            case 2 -> NativeBook.fromBlob(e);
            case 3 -> ForeignNontranslatedBook.fromBlob(e);
            case 4 -> ForeignTranslatedBook.fromBlob(e);
            default -> {
                LOGGER.severe(String.format("File read Document Blob type out of range"));
                yield null;
            }
        }));
        updateCounter();
    }

    private Document acccessInpDoc() {
        Document document = null;
        System.out.println("Nhap ten sach: ");
        document.setName(Global.scanner.nextLine());

        System.out.println("Nhap so luong tac gia: ");
        for (int i = 0; i < Integer.parseInt(Global.scanner.nextLine()); i++) {
            Authors authors = new Authors();
            System.out.println("Nhap ma tac gia: ");
            Author author = new Author(Integer.parseInt(Global.scanner.nextLine()));
            authors.instance.at(search(author.getId()));
            document.setAuthors((Author[]) instance.stream().toArray());
        }

        System.out.println("Nhap so luong ban sao: ");
        document.setCopies(Integer.parseInt(Global.scanner.nextLine()));

        System.out.println("Nhap thoi gian xuat ban: ");
        document.setPublication(ThoiGian.parseTG(Global.scanner.nextLine()));

        instance.push_back(document);

        return document;
    }

    public int menuEdit() {
        System.out.println("1. Ten  sach");
        System.out.println("2. Tac gia");
        System.out.println("3. Nam xuat ban");
        System.out.println("4. So luong ban sao");
        return Integer.parseInt(Global.scanner.nextLine());
    }

    @Override
    public Document add() {
        Document document = acccessInpDoc();
        instance.push_back(document);
        return document;
    }

    @Override
    public Document remove() {
        Document document = acccessInpDoc();
        int index = search(document.getId());
        if (index != -1) {
            instance.erase(index);
        }

        return document;
    }

    @Override
    public Document edit() {
        Document document = add();
        int index = search(document.getId());
        if (index != -1) {

            document = instance.at(index);

            switch (menuEdit()) {
                case 1 -> document.setName(Global.scanner.nextLine());
                case 2 -> {
                    System.out.println("Nhap so luong tac gia: ");
                    Author[] authors = new Author[Integer.parseInt(Global.scanner.nextLine())];
                    IntStream.range(0, authors.length).forEach(i -> {
                        System.out.println("Nhap ma tac gia: ");
                        Author author = new Author(Integer.parseInt(Global.scanner.nextLine()));
                        Authors authors1 = new Authors();
                        authors[i] = authors1.instance.at(search(author.getId()));
                    });

                    document.setAuthors(authors);
                }
                case 3 -> document.setPublication(ThoiGian.parseTG(Global.scanner.nextLine()));
                case 4 -> document.setCopies(Integer.parseInt(Global.scanner.nextLine()));
            }
        }

        return document;
    }

    @Override
    public int[] search() {
        // TODO Auto-generated method stub
        return null;
    }

    private PFArray<Document> instance;

    public static final class Type {
        public static final int MAGAZINE = 1;
        public static final int NATIVE_BOOK = 2;
        public static final int FOREIGN_TRANSLATED_BOOK = 3;
        public static final int FOREIGN_NONTRANSLATED_BOOK = 4;
    }

    public static class Instance {
        public static final Newspaper newspaper = new Newspaper(0);
        public static final NativeBook nativeBook = new NativeBook(0);
        public static final ForeignNontranslatedBook foreignNontranslatedBook = new ForeignNontranslatedBook(0);
        public static final ForeignTranslatedBook foreigntranslatedBook = new ForeignTranslatedBook(0);
    }
}
