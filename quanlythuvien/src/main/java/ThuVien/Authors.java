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

    public Authors add(Author r) {
        // TODO: accept input
        Author __ = new Author(++id_counter);
        // .setAbc(...)
        instance.push_back(__);
        return this;
    }
    public Authors remove() {
        //TODO:accept input
        // abc = search()
        // if length == 1 instance.erase()...
    }
    public Authors edit() {
        //TODO:accept inpiut
        // abc = search()
        // if length == 1 instance[i].setAbc.setXyz
    }
    public int[] search() {
        // TODO:accept input

    }
    // public PFArray<String[]> toBatchBlob() {}; already implemented

    public static Authors fromBatchBlob(PFArray<String[]> inp) {
        return new Authors(inp);
    }
}
