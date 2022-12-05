package ThuVien;

import java.util.Random;
import java.util.Scanner;

import Polyfill.StringHelper;

public class Global {
    public static Authors authors;
    public static Cards cards;
    public static Readers readers;
    public static Documents documents;
    public static Cashiers cashiers;
    public static Managers managers;
    public static HoaDons hoadons;
    public static Owner trandan;
    public static final int ratePerDay = 5000;
    public static final Scanner scanner = new Scanner(System.in);
    public static final Random random = new Random();

    public static void identityLookup() {
        System.out.println("Select an identity to lookup:");
        Management<?> identity = null;
        int[] foundPos = switch (StringHelper.acceptInput("Authors", "Documents", "Readers", "Cashiers", "Managers",
                "HoaDons")) {
            case 1 -> {
                identity = authors;
                yield authors.search();
            }
            case 2 -> {
                identity = documents;
                yield documents.search();
            }
            case 3 -> {
                identity = readers;
                yield readers.search();
            }
            case 4 -> {
                identity = cashiers;
                yield cashiers.search();
            }
            case 5 -> {
                identity = managers;
                yield managers.search();
            }
            case 6 -> {
                identity = hoadons;
                yield hoadons.search();
            }
            default -> null;
        };
        if (identity != null) {
            for (int i : foundPos) {
                System.out.println(identity.instance.at(i));
            }
        }
    }
}
