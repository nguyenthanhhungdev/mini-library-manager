package ThuVien;

import Polyfill.PFArray;

public interface IBatchDataProcess<T> {
    public PFArray<String[]> toBlob();
    public PFArray<T> fromBlob(PFArray<String[]> inp);
}
