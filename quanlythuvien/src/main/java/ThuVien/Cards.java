package ThuVien;

import Polyfill.PFArray;

public class Cards extends Management<Card> {
    public Cards() {
        super();
    }

    public Cards(Card[] r) {
        super(r);
    }

    public Cards(PFArray<String[]> blob) {
        this();
        blob.stream().forEach(e -> instance.push_back(Card.fromBlob(e)));
        updateCounter();
    }

    @Override
    public Card add() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Card remove() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Card edit() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int[] search() {
        // TODO Auto-generated method stub
        return null;
    }

    public static final float regular = 1.f;
    public static final float pro = .9f;
    public static final float vip = .75f;
    public static final float ultimate = .5f;
}
