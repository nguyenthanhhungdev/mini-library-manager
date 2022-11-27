package ThuVien;

import Polyfill.PFArray;

public class Documents extends Management<Document> {
    public Documents() {
    }

    @Override
    public Document add() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Document remove() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Document edit() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int[] search() {
        // TODO Auto-generated method stub
        return null;
    }

    private PFArray<Document> instance;

    public static final class Type {
        public static final int BOOK = 1;
        public static final int MAGAZINE = 2;
        public static final int NATIVE_BOOK = 3;
        public static final int FOREIGN_TRANSLATED_BOOK = 4;
        public static final int FOREIGN_BOOK = 5;
    }
}
