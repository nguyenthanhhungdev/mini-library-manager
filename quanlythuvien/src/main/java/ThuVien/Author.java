package ThuVien;

import Polyfill.StringHelper;

public class Author extends People {
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

    @Override
    public String toString() {
        return StringHelper.liner(super.toString(),
                StringHelper.itemer("Language", language),
                StringHelper.itemer("Website", website));
    }

    private String website;
    private Language language;
}
