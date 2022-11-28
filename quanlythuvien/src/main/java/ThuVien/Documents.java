package ThuVien;

import java.util.logging.Logger;

import Polyfill.PFArray;

public class Documents extends Management<Document> {
    private static final Logger LOGGER = Logger.getLogger(Documents.class.getName());

    public Documents(Authors authors_instance) {
        super();
        this.authors_instance = authors_instance;
    }

    public Documents(Authors authors_instance, Document[] r) {
        super(r);
        this.authors_instance = authors_instance;
    }

    public Documents(PFArray<String[]> blob, Authors authors_instance) {
        this(authors_instance);
        blob.stream().forEach(e -> instance.push_back(switch (Integer.parseInt(e[0])) {
            case 1 -> Newspaper.fromBlob(e, authors_instance);
            case 2 -> NativeBook.fromBlob(e, authors_instance);
            case 3 -> ForeignNontranslatedBook.fromBlob(e, authors_instance);
            case 4 -> ForeignTranslatedBook.fromBlob(e, authors_instance);
            default -> {
                LOGGER.severe(String.format("File read Document Blob type out of range"));
                yield null;
            }
        }));
        updateCounter();
    }

    @Override
    public Document add() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Document remove() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Document edit() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int[] search() {
        // TODO Auto-generated method stub
        return null;
    }

    private PFArray<Document> instance;
    private Authors authors_instance;

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
