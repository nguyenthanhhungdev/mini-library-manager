package ThuVien;

import Polyfill.PFArray;

public abstract class Management<T extends AnyId & IDataProcess<T>> implements IBatchDataProcess<T> {
    public Management() {
        instance = new PFArray<>();
        id_counter = 0;
    }

    public Management(T[] ts) {
        instance = new PFArray<>(ts);
        updateCounter();
    }

    public abstract Management<T> add(T t);

    public abstract Management<T> remove(T t);

    public abstract Management<T> edit(T t);

    public abstract Management<T> search(T t);

    public PFArray<String[]> toBatchBlob() {
        PFArray<String[]> __ = new PFArray<>(instance.size());
        instance.stream().map(T::toBlob).forEach(e -> __.push_back(e));
        return __;
    }

    protected void updateCounter() {
        id_counter = instance.stream().mapToInt(AnyId::getId).max().orElse(0);
    }

    protected PFArray<T> instance;
    protected int id_counter;
}
