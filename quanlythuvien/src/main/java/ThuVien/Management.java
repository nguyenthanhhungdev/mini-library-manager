package ThuVien;

import java.util.Objects;
import java.util.stream.IntStream;

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

    public abstract T add();

    public abstract T remove();

    public abstract T edit();

    public abstract int[] search();

    public int search(int id) {
        return IntStream.range(0, instance.size()).filter(i -> instance.at(i).getId() == id).findAny().orElse(-1);
    }

    public T getById(int id) {
        return instance.stream().filter(e -> e.getId() == id).findAny().orElse(null);
    }

    @SuppressWarnings("unchecked")
    public T[] batchGetByIds(int[] ids) {
        return (T[]) IntStream.of(ids).mapToObj(e -> getById(e)).filter(Objects::nonNull).toArray();
    }

    public boolean removeById(int id) {
        int __ = search(id);
        if (__ < 0) {
            // not found
            return false;
        } else {
            instance.erase(__);
            return true;
        }

    }

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
