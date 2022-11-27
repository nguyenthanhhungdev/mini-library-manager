package ThuVien;

import Polyfill.KhoangThoiGian;
import Polyfill.ThoiGian;

public class Card extends AnyId implements IDataProcess<Card> {
    public Card(int id) {
        this(id, ThoiGian.now());
    }

    protected Card(int id, ThoiGian tg) {
        super(id);
        creation = expiration = tg;
    }

    public ThoiGian getCreation() {
        return creation;
    }

    public ThoiGian getExpiration() {
        return expiration;
    }

    public Card extendExpiration(KhoangThoiGian ktg) {
        expiration = expiration.plusKhoangThoiGian(ktg);
        return this;
    }

    protected Card setExpiration(ThoiGian tg) {
        expiration = tg;
        return this;
    }

    public String[] toBlob() {
        return new String[] { String.valueOf(getId()), getCreation().toString(), getExpiration().toString() };
    }

    public static Card fromBlob(String[] inp) {
        int id = Integer.parseInt(inp[0]);
        ThoiGian creation = ThoiGian.parseTG(inp[1]);
        ThoiGian expiration = ThoiGian.parseTG(inp[2]);
        return new Card(id, creation).setExpiration(expiration);
    }

    private ThoiGian creation, expiration;
}
