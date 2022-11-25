package ThuVien;

import Polyfill.StringHelper;

public class Language extends AnyId implements IDataProcess<Language> {
    public Language(int id, String tname, String name, String code) {
        super(id);
        this.name = name;
        this.tname = tname;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getTname() {
        return tname;
    }

    public String getCode() {
        return code;
    }

    public Language fromBlob(String[] inp) {
        int id = Integer.parseInt(inp[0]);
        String name = inp[1];
        String tname = inp[2];
        return new Language(id, code, name, tname);
    }

    public String[] toBlob() {
        return new String[] { String.valueOf(getId()), code, name, tname };
    }

    @Override
    public String toString() {
        return StringHelper.liner(super.toString(),
                StringHelper.itemer("Name", StringHelper.spacer(tname, name, code)));
    }
    
    private String code, name, tname;
}
