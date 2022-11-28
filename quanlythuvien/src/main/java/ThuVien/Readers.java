package ThuVien;

import Polyfill.PFArray;

public class Readers extends Management<Reader> {
    public Readers(Cards cards_instance) {
        super();
        this.cards_instance = cards_instance;
    }

    public Readers(PFArray<String[]> blob, Cards cards_instance) {
        this(cards_instance);
        blob.stream().forEach(e -> instance.push_back(Reader.fromBlob(e, cards_instance)));
        updateCounter();
    }

    public Reader add() {
        // TODO: accept input
        Reader __ = new Reader(++id_counter, null);
        // .setAbc(...)
        instance.push_back(__);
        return null;
    }

    public Reader remove() {
        // TODO:accept input
        // abc = search()
        // if length == 1 instance.erase()...
        return null;
    }

    public Reader edit() {
        // TODO:accept inpiut
        // abc = search()
        // if length == 1 instance[i].setAbc.setXyz
        return null;
    }

    public int[] search() {
        // TODO:accept input
        return null;
    }
    // public PFArray<String[]> toBatchBlob() {}; already implemented

    public static Readers fromBatchBlob(PFArray<String[]> inp, Cards cards_instance) {
        return new Readers(inp, cards_instance);
    }

    private Cards cards_instance;
}
