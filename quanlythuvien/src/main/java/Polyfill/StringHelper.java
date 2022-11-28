package Polyfill;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.sound.sampled.SourceDataLine;

import ThuVien.Global;

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

    public static String[] iter2str(Iterable<Object> iter) {
        return StreamSupport.stream(iter.spliterator(), false).map(Object::toString).toArray(String[]::new);
    }

    public static int acceptInput(String... lines) {
        flushScanner();
        IntStream.range(0, lines.length).forEach(i -> System.out.println(StringHelper.concater(". ", i, lines[i])));
        int n = Integer.parseInt(acceptLine("Number input"));
        if (n < 1 || n > lines.length) {
            n = -1;
        }
        flushScanner();
        return n;
    }

    public static String acceptLine(String prompt) {
        flushScanner();
        System.out.println(prompt + ": ");
        return Global.scanner.nextLine().trim();
    }

    public static void flushScanner() {
        while (Global.scanner.hasNextLine()) {
            Global.scanner.nextLine();
        }
    }
}
