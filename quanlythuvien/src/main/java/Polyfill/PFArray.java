package Polyfill;
import java.util.Arrays;
import java.util.Iterator;

public class PFArray<T> implements Iterable<T> {
    public PFArray() {
        this(1);
    }

    public PFArray(int initialCapacity) {
        if (initialCapacity < 1) {
            throw new RuntimeException("This implementation requires initialCapacity of at least 1");
        }
        size = 0;
        reserve(initialCapacity);
    }

    public PFArray(T[] primitiveArray) {
        elements = primitiveArray.clone();
        size = elements.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return index < size && elements[index] != null;
            }

            @Override
            public T next() {
                return at(index++);
            }

            private int index = 0;
        };
    }

    public T at(int index) {
        indexCheck(index);
        return (T) elements[index];
    }

    public void set(int index, T element) {
        indexCheck(index);
        elements[index] = element;
    }

    public void insert(int index, T element) {
        indexCheck(index);
        updateSize(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index - 1);
        set(index, element);
    }

    public T erase(int index) {
        indexCheck(index);
        T ret = at(index);
        for (int i = index; i < size - 1; i++) {
            set(i, at(i + 1));
        }
        updateSize(size - 1);
        return ret;
    }

    public void push_front(T element) {
        insert(0, element);
    }

    public T pop_front() {
        return erase(0);
    }

    public void push_back(T element) {
        updateSize(size + 1);
        set(size - 1, element);
    }

    public T pop_back() {
        return erase(size - 1);
    }

    public T front() {
        return at(0);
    }

    public T back() {
        return at(size - 1);
    }

    public boolean empty() {
        return size == 0;
    }

    public void resize(int newSize) {
        updateSize(newSize);
    }

    public void clear() {
        updateSize(0);
    }

    public int capacity() {
        return elements.length;
    }

    public void reserve(int reservedCapacity) {
        if (reservedCapacity > capacity()) {
            T[] newArray = (T[]) new Object[reservedCapacity];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    private void indexCheck(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }

    private void updateSize(int newSize) {
        if (newSize > size && newSize > capacity()) {
            if (capacity() >= maxElements) {
                throw new RuntimeException("Max elements limit reached");
            }
            long newCapacity = Math.round(Math.ceil(capacity() * growthFactor));
            elements = Arrays.copyOf(elements, newCapacity > maxElements ? maxElements : (int) newCapacity);
        }
        size = newSize;
    }
    
    public int length() {
        return size;
    }
    private int size = 0;
    private static final float growthFactor = 2;
    private static final int maxElements = 2_000_000_000;
    private T elements[] = (T[]) new Object[0];
}
