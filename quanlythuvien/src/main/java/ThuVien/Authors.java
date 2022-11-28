package ThuVien;

import Polyfill.PFArray;

public class Authors extends Management<Author> {
    public Authors() {
        super();
    }

    public Authors(Author[] r) {
        super(r);
    }

    public Authors(PFArray<String[]> blob) {
        this();
        blob.stream().forEach(e -> instance.push_back(Author.fromBlob(e)));
        updateCounter();
    }

    public Author add() {
        // TODO: accept input
        Author __ = new Author(++id_counter);
        // .setAbc(...)
        instance.push_back(__);
        return __;
    }

    public Author remove() {
        // TODO:accept input
        // abc = search()
        // if length == 1 instance.erase()...
        return null;
    }

    public Author edit() {
        // TODO:accept inpiut
        // abc = search()
        // if length == 1 instance[i].setAbc.setXyz
        return null;
    }

    public int[] search() {
        // TODO:accept input
        return new int[0];

    }
    // public PFArray<String[]> toBatchBlob() {}; already implemented

    public static Authors fromBatchBlob(PFArray<String[]> inp) {
        return new Authors(inp);
    }
}
