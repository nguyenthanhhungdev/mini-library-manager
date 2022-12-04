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
        Global.trandan = new Owner();
        load();
        int ret = mainMenu();
        LOGGER.info("Exiting application with menu code " + ret);
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
                default -> loginList[n - 1].login();
            }
        }
        return 1;
    }

    public static int load() {
        try {
            Global.cards = Cards
                    .fromBatchBlob(PFTrim(new PFFileReader("quanlythuvien", "data", "List_The.csv").read()));
            Global.authors = Authors
                    .fromBatchBlob(PFTrim(new PFFileReader("quanlythuvien", "data", "List_TacGia.csv").read()));
            Global.documents = Documents
                    .fromBatchBlob(PFTrim(new PFFileReader("quanlythuvien", "data", "List_TaiLieu.csv").read()));
            Global.readers = Readers
                    .fromBatchBlob(PFTrim(new PFFileReader("quanlythuvien", "data", "List_DocGia.csv").read()));
            Global.cashiers = Cashiers
                    .fromBatchBlob(PFTrim(new PFFileReader("quanlythuvien", "data", "List_NhanVien.csv").read()));
            Global.managers = Managers
                    .fromBatchBlob(PFTrim(new PFFileReader("quanlythuvien", "data", "List_QuanLi.csv").read()));
            Global.hoadons = HoaDons
                    .fromBatchBlob(PFTrim(new PFFileReader("quanlythuvien", "data", "List_HoaDon.csv").read()));
            LOGGER.info("Loaded without errors");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Load data error", e);
            throw e;
        } finally {
            loginList = new ILogin[] { Global.readers, Global.cashiers, Global.managers, Global.trandan };
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
            new PFFileWriter("quanlythuvien", "data", "List_HoaDon_data.csv").write(Global.hoadons.toBatchBlob());
            LOGGER.info("Saved without errors");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Save data error", e);
            // throw e;
            return 1;
        }
        return 0;
    }

    public static PFArray<String[]> PFTrim(PFArray<String[]> inp) {
        while (StringHelper.isNullOrBlank(inp.back()[0])) {
            inp.pop_back();
        }
        inp.pop_front();
        return inp;
    }

    public static ILogin[] loginList;
}
