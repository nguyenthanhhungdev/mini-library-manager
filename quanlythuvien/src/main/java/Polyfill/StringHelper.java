package Polyfill;

import java.util.stream.Stream;

public final class StringHelper {
    public static boolean isNullOrBlank(String str) {
        return str == null || str.isBlank();
    }

    // this project's general single field seperator
    public static String[] lv1Split(String line) {
        return splitThenTrim("|", line);
    }

    public static String lv1Join(Object... words) {
        return concater("|", words);
    }

    public static String[] lv2Split(String line) {
        return splitThenTrim("\\", line);
    }

    public static String lv2Join(Object... words) {
        return concater("\\", words);
    }

    public static String[] splitThenTrim(String sep, String line) {
        return Stream.of(line.split(sep)).map(String::trim).toArray(String[]::new);
    }

    // lol why
    public static String liner(Object... lines) {
        return concater("\n", lines);
    }

    // bruh
    public static String spacer(Object... words) {
        return concater(" ", words);
    }

    // boomer
    public static String itemer(Object key, Object value) {
        return spacer(key + ":", value);
    }

    // it's kinda weird tho
    public static String concater(String delim, Object... strings) {
        try {
            return String.join(delim, obj2str(strings));
        } catch (NullPointerException e) {
            return "";
        }
    }

    // well this one is important
    public static String[] obj2str(Object... objs) {
        return Stream.of(objs).map(Object::toString).toArray(String[]::new);
    }
}
