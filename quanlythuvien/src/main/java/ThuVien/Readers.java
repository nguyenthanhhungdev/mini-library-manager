package ThuVien;

import Polyfill.PFArray;

public class Readers extends Management<Reader> {
    public Readers() {
        super();
    }

    public Readers(Reader[] r) {
        super(r);
    }

    public Readers(PFArray<String[]> blob) {
        this();
        blob.stream().forEach(e -> instance.push_back(Reader.fromBlob(e)));
        updateCounter();
    }

    public Readers add(Reader r) {
        // TODO: accept input
        Reader __ = new Reader(++id_counter, null)
        // .setAbc(...)
        instance.push_back(__);
        return this;
    }
    public Readers remove() {
        //TODO:accept input
        // abc = search()
        // if length == 1 instance.erase()...
    }
    public Readers edit() {
        //TODO:accept inpiut
        // abc = search()
        // if length == 1 instance[i].setAbc.setXyz
    }
    public Reader[] search() {
        // TODO:accept input

    }
    // public PFArray<String[]> toBatchBlob() {}; already implemented

    public static Readers fromBatchBlob(PFArray<String[]> inp) {
        return new Readers(inp);
    }
}
