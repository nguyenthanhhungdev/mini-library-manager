package ThuVien;

import java.time.LocalTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import Polyfill.StringHelper;

public class CaTruc {
    public CaTruc() {}
    public CaTruc setCaTrucNgay(int thu, int gio1, int phut1, int giay1, int gio2, int phut2, int giay2) {
        indexCheck(thu);
        LocalTime start = LocalTime.of(gio1, phut1, giay1);
        LocalTime end = LocalTime.of(gio2, phut2, giay2);
        instance[thu] = start.compareTo(end) < 0 ? new CaTrucNgay(start, end) : new CaTrucNgay(end, start);
        return this;
    }
    public record CaTrucNgay(LocalTime gioLam, LocalTime gioVe) {
        @Override
        public String toString() {
            return StringHelper.itemer("TG truc", StringHelper.concater(" - ", gioLam, gioVe));
        }
    }
    private void indexCheck(int index) {
        if (index < 0 || index > instance.length - 1) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }
    @Override
    public String toString() {
        return IntStream.range(0, 7).filter(i -> instance[i] == null)
            .mapToObj(i -> StringHelper.itemer(names[i], instance[i])).collect(Collectors.joining("\n"));
    }
    private CaTrucNgay[] instance = new CaTrucNgay[6];
    private static final String[] names = new String[] {
        "Thu 2", "Thu 3", "Thu 4", "Thu 5", "Thu 6", "Thu 7", "Chu Nhat"
    };
    public static final int Thu2 = 0;
    public static final int Thu3 = 1;
    public static final int Thu4 = 2;
    public static final int Thu5 = 3;
    public static final int Thu6 = 4;
    public static final int Thu7 = 5;
    public static final int ChuNhat = 6;
}
