package ThuVien;

import Polyfill.StringHelper;

public class AnyId {
    public int getId() {
        return id;
    }

    public AnyId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return StringHelper.itemer("ID", id);
    }

    private int id;
}
