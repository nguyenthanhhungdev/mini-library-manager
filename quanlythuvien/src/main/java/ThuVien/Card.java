package ThuVien;

import Polyfill.KhoangThoiGian;
import Polyfill.ThoiGian;

public class Card {
    public Card() {
        this(ThoiGian.now());
    }

    protected Card(ThoiGian tg) {
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

    private ThoiGian creation, expiration;
}
