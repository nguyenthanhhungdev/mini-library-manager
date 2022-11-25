package ThuVien;

import Polyfill.StringHelper;

public class Language extends AnyId {
    public Language(String name, String tname) {
        this(++id_counter, name, tname);
    }

    public Language(int id, String name, String tname) {
        super(id);
        this.name = name;
        this.tname = tname;
    }

    public String getName() {
        return name;
    }

    public String getTname() {
        return tname;
    }

    @Override
    public String toString() {
        return StringHelper.liner(super.toString(),
                StringHelper.itemer("Name", StringHelper.spacer(tname, name)));
    }

    private String name, tname;
    private static int id_counter = 0;
}
