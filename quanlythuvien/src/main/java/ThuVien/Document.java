package ThuVien;

import java.util.logging.Logger;

import Polyfill.PFArray;
import Polyfill.StringHelper;
import Polyfill.ThoiGian;

public class Document extends AnyId {
    private static final Logger LOGGER = Logger.getLogger(Document.class.getName());

    public Document() {
        this(++id_counter);
    }

    public Document(int id) {
        super(id);
        if (id_counter < id) {
            id_counter = id;
        }
    }

    public ThoiGian getPublication() {
        return publication;
    }

    public Document setPublication(ThoiGian publication) {
        this.publication = publication;
        return this;
    }

    public PFArray<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(PFArray<Author> authors) {
        this.authors = authors;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public int getCopies() {
        return copies;
    }

    public Document changeCopies(int copies_offset) {
        if ((copies += copies_offset) < 0) {
            LOGGER.info(StringHelper.spacer("Document", getId(), "has been purged"));
            copies = 0;
        }
        if (borrowed > copies) {
            LOGGER.info(StringHelper.spacer("Document", getId(), "has borrowings larger than copies", borrowed, ">",
                    copies));
        }
        return this;
    }

    public boolean borrowable() {
        return borrowed < copies;
    }

    public boolean borrows() {
        if (!borrowable())
            return false;
        borrowed++;
        return true;
    }

    public void returns() {
        if (--borrowed < 0) {
            borrowed = 0;
        }
    }

    private PFArray<Author> authors;
    private int copies = 0;
    private int borrowed = 0;
    private ThoiGian publication;
    private Language language;
    private static int id_counter = 0;

}