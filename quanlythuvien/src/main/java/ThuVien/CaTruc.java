package ThuVien;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import Polyfill.StringHelper;

public class CaTruc {
    public CaTruc() {
    }

    public CaTruc setCaTrucNgay(int thu, int gio1, int phut1, int giay1, int gio2, int phut2, int giay2) {
        return setCaTrucNgay(thu, LocalTime.of(gio1, phut1, giay1), LocalTime.of(gio2, phut2, giay2));
    }

    public CaTruc setCaTrucNgay(int thu, LocalTime t1, LocalTime t2) {
        indexCheck(thu);
        instance[thu] = t1.compareTo(t2) < 0 ? new CaTrucNgay(t1, t2) : new CaTrucNgay(t2, t1);
        return this;
    }

    public CaTruc setCaTrucNgay(int thu, CaTrucNgay ctn) {
        indexCheck(thu);
        instance[thu] = ctn;
        return this;
    }

    public record CaTrucNgay(LocalTime gioLam, LocalTime gioVe) {
        public static CaTrucNgay parseCaTrucNgay(String inp) {
            String[] __ = StringHelper.splitThenTrim("~", inp);
            return new CaTrucNgay(LocalTime.parse(__[0], formatter), LocalTime.parse(__[1], formatter));
        }

        public String toScreen() {
            return StringHelper.itemer("TG truc", this.toString());
        }

        @Override
        public String toString() {
            return StringHelper.concater(" ~ ", gioLam, gioVe);
        }

        public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    }

    private void indexCheck(int index) {
        if (index < 0 || index > instance.length - 1) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }

    // 10:00:00~12:00:00|17:00:00~21:45:00|...
    // ^ monday | ^ tuesday ...
    public static CaTruc parseCaTruc(String inp) {
        CaTruc __ = new CaTruc();
        String[] _a = StringHelper.lv1Split(inp);
        IntStream.range(0, 7).filter(i -> !StringHelper.isNullOrBlank(_a[i]))
                .forEach(i -> __.setCaTrucNgay(i, CaTrucNgay.parseCaTrucNgay(_a[i])));
        return __;
    }

    public String toScreen() {
        return IntStream.range(0, 7).filter(i -> instance[i] == null)
                .mapToObj(i -> StringHelper.itemer(names[i], instance[i])).collect(Collectors.joining("\n"));
    }

    @Override
    public String toString() {
        return Stream.of(instance).map(e -> e == null ? "" : e.toString()).collect(Collectors.joining("|"));
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
