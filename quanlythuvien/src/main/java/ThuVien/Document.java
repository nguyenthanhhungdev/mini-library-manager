package ThuVien;

import java.util.logging.Logger;
import java.util.stream.Stream;

import Polyfill.StringHelper;
import Polyfill.ThoiGian;

public class Document extends AnyId implements IDataProcess<Document> {
    private static final Logger LOGGER = Logger.getLogger(Document.class.getName());

    public Document(int id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public Document setName(String name) {
        this.name = name;
        return this;
    }

    public ThoiGian getPublication() {
        return publication;
    }

    public Document setPublication(ThoiGian publication) {
        this.publication = publication;
        return this;
    }

    public Author[] getAuthors() {
        return authors;
    }

    public Document setAuthors(Author[] authors) {
        this.authors = authors;
        return this;
    }

    public Language getLanguage() {
        return language;
    }

    public Document setLanguage(Language language) {
        this.language = language;
        return this;
    }

    public int getCopies() {
        return copies;
    }

    protected Document setCopies(int copies) {
        this.copies = copies;
        return this;
    }

    public int getBorrowed() {
        return borrowed;
    }

    protected Document setBorrowed(int borrowed) {
        this.borrowed = borrowed;
        return this;
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

    public String[] toBlob() {
        return new String[] { String.valueOf(getId()), name, String.valueOf(copies), String.valueOf(borrowed),
                language.toString(), publication.toString(),
                StringHelper.lv1Join(Stream.of(authors).mapToInt(e -> e.getId())) };
    }

    public static Document fromBlob(String[] inp, Authors authors_instance) {
        int id = Integer.parseInt(inp[0]);
        String name = inp[1];
        int copies = Integer.parseInt(inp[2]);
        int borrowed = Integer.parseInt(inp[3]);
        Language language = Languages.parseLang(inp[4]);
        ThoiGian publication = ThoiGian.parseTG(inp[5]);
        Author[] authors = authors_instance.batchGetByIds(Stream.of(StringHelper.lv1Split(inp[5]))
                .mapToInt(Integer::parseInt).toArray());
        return new Document(id).setName(name).setCopies(copies).setBorrowed(borrowed).setPublication(publication)
                .setLanguage(language).setAuthors(authors);
    }

    @Override
    public String toString() {
        return StringHelper.liner(super.toString(),
                StringHelper.itemer("Name", name),
                StringHelper.itemer("Authors", StringHelper.obj2str((Object)authors)),
                StringHelper.itemer("Publication", publication),
                StringHelper.itemer("Language", language),
                StringHelper.itemer("Copies", copies),
                StringHelper.itemer("Borrowed", borrowed));
    }

    private Author[] authors;
    private int copies = 0;
    private int borrowed = 0;
    private ThoiGian publication;
    private Language language;
    private String name;
}