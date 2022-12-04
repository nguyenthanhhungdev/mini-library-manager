package ThuVien;

import java.time.LocalDateTime;

import Polyfill.KhoangThoiGian;
import Polyfill.StringHelper;
import Polyfill.ThoiGian;

public class Card extends AnyId implements IDataProcess<Card> {
    public Card(int id) {
        this(id, ThoiGian.now());
    }

    protected Card(int id, ThoiGian tg) {
        super(id);
        creation = expiration = tg;
    }

    public Type getType() {
        return type;
    }

    protected Card setType(Type type) {
        this.type = type;
        return this;
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
        return new String[] { String.valueOf(getId()), getCreation().toString(), getExpiration().toString(),
                String.valueOf(type.code) };
    }

    public static Card fromBlob(String[] inp) {
        int id = Integer.parseInt(inp[0]);
        ThoiGian creation = ThoiGian.parseTG(inp[1]);
        ThoiGian expiration = ThoiGian.parseTG(inp[2]);
        int type = Integer.parseInt(inp[3]);
        return new Card(id, creation).setExpiration(expiration).setType(types[type]);
    }

    public String toString() {
        return StringHelper.liner(super.toString(),
                StringHelper.itemer("Type", type.name),
                StringHelper.itemer("Price multiplier", type.multiplier),
                StringHelper.itemer("Creation", creation),
                StringHelper.itemer("Expiration", expiration));
    }

    public String toStringMinified() {
        return StringHelper.lv1Join(getId(), type.name);
    }

    private ThoiGian creation, expiration;

    public static final record Type(int code, String name, float multiplier) {
    };

    public static final Type none = new Type(0, "None", 1.2f);
    public static final Type regular = new Type(1, "Regular", 1.f);
    public static final Type pro = new Type(2, "Pro", .9f);
    public static final Type vip = new Type(3, "VIP", .8f);
    public static final Type ultimate = new Type(4, "Ultimate", .7f);
    public static final Type[] types = new Type[] { none, regular, pro, vip, ultimate };
    private Type type = none;
    public static final Card defaultCard = new Card(0, new ThoiGian(LocalDateTime.MIN))
            .setExpiration(new ThoiGian(LocalDateTime.MAX));
    public static final int blob_column = 4;
}
