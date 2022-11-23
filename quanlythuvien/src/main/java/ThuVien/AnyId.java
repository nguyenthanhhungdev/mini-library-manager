package ThuVien;

import Polyfill.StringHelper;

public class AnyId {
    protected long getId() {
        return id;
    }

    public AnyId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return StringHelper.itemer("ID", id);
    }

    private long id;
}
