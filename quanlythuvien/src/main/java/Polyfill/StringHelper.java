package Polyfill;

import java.util.stream.Stream;

public class StringHelper {
    // lol why
    public static String liner(Object ...lines) {
        return concater("\n", lines);
    }
    // bruh
    public static String spacer(Object ...words) {
        return concater(" ", words);
    }
    // boomer
    public static String itemer(Object key, Object value) {
        return spacer(key + ":", value);
    }
    // it's kinda weird tho
    public static String concater(CharSequence delim, Object ...strings) {
        return String.join(delim, obj2str(strings));
    }
    // well this one is important
    public static String[] obj2str(Object ...objs) {
        return Stream.of(objs).map(Object::toString).toArray(String[]::new);
    }
}
