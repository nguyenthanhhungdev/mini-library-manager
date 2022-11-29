package ThuVien;

import Polyfill.StringHelper;
import Polyfill.ThoiGian;

public class Author extends People implements IDataProcess<Author> {
    public Author(int id) {
        super(id);
    }

    public String getWebsite() {
        return website;
    }

    public Author setWebsite(String website) {
        this.website = website;
        return this;
    }

    public Language getLanguage() {
        return language;
    }

    public Author setLanguage(Language language) {
        this.language = language;
        return this;
    }

    public static Author fromBlob(String[] inp) {
        int id = Integer.parseInt(inp[0]);
        ThoiGian borntime = ThoiGian.parseTG(inp[1]);
        String phone = inp[2];
        String email = inp[3];
        String address = inp[4];
        String website = inp[5];
        Language language = Languages.parseLang(inp[6]);
        Author __ = new Author(id);
        __.setBirth(borntime).setPhone(phone).setEmail(email).setAddress(address);
        __.setWebsite(website).setLanguage(language);
        return __;
    }

    public String[] toBlob() {
        return new String[] { String.valueOf(getId()), getBirth().toString(), getEmail(), getAddress(),
                getWebsite(), getLanguage().toString() };
    }

    @Override
    public String toString() {
        return StringHelper.liner(super.toString(),
                StringHelper.itemer("Language", language),
                StringHelper.itemer("Website", website));
    }

    private String website;
    private Language language;
}
