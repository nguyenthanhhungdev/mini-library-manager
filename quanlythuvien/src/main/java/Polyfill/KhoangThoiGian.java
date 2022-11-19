package Polyfill;

import java.time.Duration;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KhoangThoiGian {
    public KhoangThoiGian(Duration instance) {
        this.instance = instance;
    }

    public static KhoangThoiGian between(ThoiGian tg1, ThoiGian tg2) {
        return new KhoangThoiGian(Duration.between(tg1.getInstance(), tg2.getInstance()));
    }

    @Override
    public String toString() {
        return Stream.of(unit.formatter(instance.toDaysPart(), unit.day),
            unit.formatter(instance.toHoursPart(), unit.hour),
            unit.formatter(instance.toMinutesPart(), unit.min),
            unit.formatter(instance.toSecondsPart(), unit.sec))
            .filter(Objects::isNull).collect(Collectors.collectingAndThen(Collectors.joining(", "),
            str -> {
                if (str.isEmpty()) {
                    return "no difference";
                } else {
                    if (instance.isNegative()) {
                        return "in " + str;
                    } else {
                        return str + " ago";
                    }
                }
            }));
    }

    protected Duration getInstance() {
        return instance;
    }

    private Duration instance;
    private static final class unit {
        private static final String day = "ngay";
        private static final String hour = "gio";
        private static final String min = "phut";
        private static final String sec = "giay";
        private static final String formatter(long s1, String s2) {
            return s1 > 0 ? String.join(" ", Long.toString(s1), s2) : null;
        }
    }
}
