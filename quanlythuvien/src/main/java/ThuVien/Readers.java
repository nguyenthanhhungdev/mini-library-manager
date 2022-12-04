package ThuVien;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import Polyfill.PFArray;
import Polyfill.StringHelper;
import Polyfill.ThoiGian;

public class Readers extends Management<Reader> implements ILogin {
    private static final Logger LOGGER = Logger.getLogger(Readers.class.getName());

    public Readers() {
        super();
    }

    public Readers(PFArray<String[]> blob) {
        this();
        blob.stream().forEach(e -> instance.push_back(Reader.fromBlob(e)));
        updateCounter();
    }

    public int menuEdit() {
        return StringHelper.acceptInput("Ten", "Ngay sinh", "Dia chi", "So dien thoai", "Email");
    }

    public Reader add() {
        try {
            String username = StringHelper.acceptLine("Ten tai khoan");
            String password = StringHelper.acceptLine("Mat khau");
            String name = StringHelper.acceptLine("Ten");
            ThoiGian birth = ThoiGian.parseTG(StringHelper.acceptLine("Ngay sinh"));
            String phone = StringHelper.acceptLine("So dien thoai");
            String email = StringHelper.acceptLine("Email");
            String address = StringHelper.acceptLine("Dia chi");
            Reader toAdd = new Reader(genNextId(), username);
            toAdd.changePassword(null, password);
            toAdd.setName(name);
            toAdd.setBirth(birth);
            toAdd.setPhone(phone);
            toAdd.setEmail(email);
            toAdd.setAddress(address);
            instance.push_back(toAdd);
            return toAdd;
        } catch (RuntimeException e) {
            LOGGER.log(Level.WARNING, "Likely input parse error in Managers::add", e);
            LOGGER.info("The adding operation is cancelled");
            LOGGER.fine(String.format("Id counter is %d", currentIdCount()));
            return null;
        }
    }

    public int promptSearch() {
        int n;
        try {
            n = search(Integer.parseInt(StringHelper.acceptLine("Nhap ma doc gia: ")));
            if (n == -1) {
                System.out.println("Tim kiem khong co ket qua: ");
            } else {
                System.out.println("Tim thay doc gia: ");
                System.out.println(instance.at(n).toString());
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Input error", e);
            throw e;
        }

        return n;
    }

    public Reader remove() {
        Reader reader = null;
        int n;
        try {
            n = promptSearch();
            if (n == -1) {
                System.out.println("Tim kiem that bai, remove doc gia that bai");
            } else {
                System.out.println("Xac nhan xoa doc gia: ");
                System.out.println(instance.at(n).toString());
                int m = StringHelper.acceptInput("Co", "Suy nghi lai");
                if (m == 1) {
                    reader = instance.erase(n);
                    System.out.println("Da xoa tac gia: ");
                    System.out.println(reader.toString());
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Co loi xay ra, remove doc gia khong thanh cong", e);
            throw e;
        }
        return reader;
    }

    public Reader edit() {
        Reader reader = null;
        int n;
        try {
            n = promptSearch();
            if (n == -1) {
                System.out.println("Tim kiem that bai, edit doc giakhong thanh cong ");
            } else {
                int m;
                do {
                    reader = instance.at(n);
                    System.out.println("Dang thao tac voi doc gia: ");
                    System.out.println(reader.toString());
                    switch (m = menuEdit()) {
                        case 1 -> reader.setName(StringHelper.acceptLine("Nhap ten doc gia: "));
                        case 2 ->
                            reader.setBirth(ThoiGian.parseTG(StringHelper.acceptLine("Nhap ngya sinh doc gia: ")));
                        case 3 -> reader.setAddress(StringHelper.acceptLine("Nhap dia chi tac gia: "));
                        case 4 -> reader.setPhone(StringHelper.acceptLine("Nhap so dien thoai: "));
                        case 5 -> reader.setEmail(StringHelper.acceptLine("Nhap email: "));
                        default -> {
                            m = -1;
                            System.out.println("Ket thuc edit doc gia");
                        }
                    }
                } while (m >= 0);
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Co xay ra loi, edit doc gia that bai", e);
        }
        return reader;
    }

    public int login() {
        String username = StringHelper.acceptLine("Nhap ten tai khoan (Doc gia)");
        int found = IntStream.range(0, instance.size())
                .filter(e -> username.equalsIgnoreCase(instance.at(e).getUsername())).findAny().orElse(-1);
        if (found == -1) {
            System.out.println("Khong tim thay ten dang nhap (docgia)");
            return -1;
        }
        System.out.println("Tim thay doc gia");
        System.out.println(instance.at(found).toStringMinified());
        String password = StringHelper.acceptLine("Nhap mat khau");
        if (!instance.at(found).checkPassword(password)) {
            System.out.println("Sai mat khau");
            return -1;
        }
        System.out.println("Mat khau chinh xac");
        instance.at(found).dashboard();
        return found;
    }

    @Override
    public int[] search() {
        String query = StringHelper.acceptLine("Nhap ten doc gia");
        String[] entries = query.toLowerCase().split(" ");
        return IntStream.range(0, instance.size()).filter(i -> {
            String[] names = instance.at(i).getName().toLowerCase().split(" ");
            for (int j = 0; j < names.length; j++) {
                for (int k = 0; k < entries.length; k++) {
                    if (names[j].startsWith(entries[k])) {
                        return true;
                    }
                }
            }
            return false;
        }).toArray();
    }

    public static Readers fromBatchBlob(PFArray<String[]> inp) {
        if (inp.size() < 1) {
            LOGGER.warning("No entries");
        } else {
            LOGGER.info(String.format("Batching %d x %d blob", inp.size(), Reader.blob_column));
        }
        return new Readers(inp);
    }
}
