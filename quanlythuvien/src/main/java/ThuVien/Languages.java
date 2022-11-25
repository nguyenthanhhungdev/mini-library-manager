package ThuVien;

import java.util.stream.Stream;

public class Languages {
    public static final Language[] list = new Language[] {
        new Language(1, "Vietnamese", "Tieng Viet", "VN"),
        new Language(2, "English", "Tieng Anh", "US"),
        new Language(3, "France", "Tieng Phap", "FR"),
        new Language(4, "Cheems", "Tieng Cheems", "CH")};
    public static final Language unknown = new Language(0, "Unknown", "Tieng Thanh Hoa", "TH");
    // public static final Language vn = new Language(1, "Vietnamese", "Tieng Viet", "VN");
    // public static final Language en = new Language(2, "English", "Tieng Anh", "EN");
    // public static final Language fr = new Language(3, "France", "Tieng Phap", "FR");
    // public static final Language ch = new Language(4, "Cheems", "Chmua Hme", "CH");
    public static Language parseLang(String...names) {
        return Stream.of(list).filter(l -> {
            for (String n: names) {
                if (n.equals(l.getCode()) || n.equals(l.getName()) || n.equals(l.getTname())) {
                    return true;
                }
            }
            return false;
        }).findAny().orElse(unknown);
    }
}
