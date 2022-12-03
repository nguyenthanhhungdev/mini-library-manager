package ThuVien;

import java.util.logging.Level;
import java.util.logging.Logger;

import Polyfill.PFArray;
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
            if (n == -1) {
                break;
            }
            switch (n) {
                case 5 -> save();
                default -> loginList[n].login();
            }
        }
        return 1;
    }

    public static int load() {
        try {
            PFArray<String[]> cards = new PFFileReader("quanlythuvien", "data", "List_The.csv").read();
            cards.pop_front();
            Global.cards = new Cards(cards);
            PFArray<String[]> authors = new PFFileReader("quanlythuvien", "data", "List_TacGia.csv").read();
            authors.pop_front();
            Global.authors = new Authors(authors);
            PFArray<String[]> documents = new PFFileReader("quanlythuvien", "data", "List_TaiLieu.csv").read();
            documents.pop_front();
            Global.documents = new Documents(documents);
            PFArray<String[]> readers = new PFFileReader("quanlythuvien", "data", "List_DocGia.csv").read();
            readers.pop_front();
            Global.readers = new Readers(readers);
            PFArray<String[]> cashiers = new PFFileReader("quanlythuvien", "data", "List_NhanVien.csv").read();
            cashiers.pop_front();
            Global.cashiers = new Cashiers(cashiers);
            PFArray<String[]> managers = new PFFileReader("quanlythuvien", "data", "List_QuanLi.csv").read();
            managers.pop_front();
            Global.managers = new Managers(managers);
            LOGGER.info("Loaded without errors");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Load data error", e);
            throw e;
        }
        return 0;
    }

    public static int save() {
        try {
            new PFFileWriter("quanlythuvien", "data", "List_The_data.csv").write(Global.cards.toBatchBlob());
            new PFFileWriter("quanlythuvien", "data", "List_TacGia_data.csv").write(Global.authors.toBatchBlob());
            new PFFileWriter("quanlythuvien", "data", "List_TaiLieu_data.csv").write(Global.documents.toBatchBlob());
            new PFFileWriter("quanlythuvien", "data", "List_DocGia_data.csv").write(Global.readers.toBatchBlob());
            new PFFileWriter("quanlythuvien", "data", "List_NhanVien_data.csv").write(Global.cashiers.toBatchBlob());
            new PFFileWriter("quanlythuvien", "data", "List_QuanLi_data.csv").write(Global.managers.toBatchBlob());
            LOGGER.info("Saved without errors");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Save data error", e);
            // throw e;
            return 1;
        }
        return 0;
    }

    public static final ILogin[] loginList = new ILogin[] { Global.readers, Global.cashiers, Global.managers,
            Global.trandan };
}
