package ThuVien;

import java.util.logging.Level;
import java.util.logging.Logger;

import Polyfill.PFFileReader;
import Polyfill.PFFileWriter;
import Polyfill.StringHelper;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static int runApp(String[] args) {
        int logLevel = Integer.parseInt(args[0]);
        switch (logLevel) {
            case 1 -> LOGGER.setLevel(Level.ALL);
            case 2 -> LOGGER.setLevel(Level.WARNING);
            case 3 -> LOGGER.setLevel(Level.FINE);
            case 4 -> LOGGER.setLevel(Level.OFF);
            default -> LOGGER.setLevel(Level.SEVERE);
        }
        load();
        mainMenu();
        return 0;
    }

    public static int mainMenu() {
        while (true) {
            System.out.println("Select an entry to log in with");
            int n = StringHelper.acceptInput("Doc gia", "Nhan vien", "Quan li", "CEO", "Save data", "Quit");
            if (n == 6) {
                return 0;
            }
            switch (n) {
                case 1 -> Global.readers.login();
                case 2 -> Global.cashiers.login();
                case 3 -> Global.managers.login();
                case 4 -> Global.trandan.login();
                case 5 -> save();
            }
            return 1;
        }

    }

    public static int load() {
        try {
            Global.cards = new Cards(new PFFileReader("data", "List_The.csv").read());
            Global.authors = new Authors(new PFFileReader("data", "List_TacGia.csv").read());
            Global.documents = new Documents(new PFFileReader("data", "List_TaiLieu.csv").read());
            Global.readers = new Readers(new PFFileReader("data", "List_DocGia.csv").read());
            Global.cashiers = new Cashiers(new PFFileReader("data", "List_NhanVien.csv").read());
            Global.managers = new Managers(new PFFileReader("data", "List_QuanLi.csv").read());
            LOGGER.info("Loaded without errors");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Load data error", e);
            throw e;
        }
        return 0;
    }

    public static int save() {
        try {
            new PFFileWriter("data", "List_The.csv").write(Global.cards.toBatchBlob());
            new PFFileWriter("data", "List_TacGia.csv").write(Global.authors.toBatchBlob());
            new PFFileWriter("data", "List_TaiLieu.csv").write(Global.documents.toBatchBlob());
            new PFFileWriter("data", "List_DocGia.csv").write(Global.readers.toBatchBlob());
            new PFFileWriter("data", "List_NhanVien.csv").write(Global.cashiers.toBatchBlob());
            new PFFileWriter("data", "List_QuanLi.csv").write(Global.managers.toBatchBlob());
            LOGGER.info("Saved without errors");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Save data error", e);
            // throw e;
            return 1;
        }
        return 0;
    }
}
