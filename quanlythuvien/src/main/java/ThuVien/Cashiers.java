package ThuVien;

import Polyfill.PFArray;

public class Cashiers extends Management<Cashier> {
    public Cashiers() {
        super();
    }

    public Cashiers(Cashier[] r) {
        super(r);
    }

    public Cashiers(PFArray<String[]> blob) {
        this();
        blob.stream().forEach(e -> instance.push_back(Cashier.fromBlob(e)));
        updateCounter();
    }

    @Override
    public Cashier add() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Cashier remove() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Cashier edit() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int[] search() {
        // TODO Auto-generated method stub
        return null;
    }

    
}
