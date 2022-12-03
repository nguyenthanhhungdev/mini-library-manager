package ThuVien;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

import Polyfill.KhoangThoiGian;
import Polyfill.PFArray;
import Polyfill.StringHelper;
import Polyfill.ThoiGian;

public class Cards extends Management<Card> {
    private static final Logger LOGGER = Logger.getLogger(Cards.class.getName());

    public Cards() {
        super();
    }

    public Cards(Card[] r) {
        super(r);
    }

    public Cards(PFArray<String[]> blob) {
        this();
        blob.stream().forEach(e -> instance.push_back(Card.fromBlob(e)));
        updateCounter();
    }

    @Override
    public Card add() {
        int userInp = StringHelper.acceptInput("regular", "pro", "vip", "ultimate");
        if (userInp <= 0) {
            LOGGER.warning("Unexpected input");
            return null;
        }
        Card card = new Card(genNextId(), ThoiGian.now());
        switch (userInp) {
            case 1 -> {
                card.setType(Card.regular);
                card.setExpiration(card.getCreation().modNgay(180));
            }
            case 2 -> {
                card.setType(Card.pro);
                card.setExpiration(card.getCreation().modNgay(240));
            }
            case 3 -> {
                card.setType(Card.vip);
                card.setExpiration(card.getCreation().modNgay(300));
            }
            case 4 -> {
                card.setType(Card.ultimate);
                card.setExpiration(card.getCreation().modNgay(365));
            }
        }
        return card;
    }

    private int promptSearch() {
        int n = search(Integer.parseInt(StringHelper.acceptLine("Nhap ma the: ")));
        if (n == -1) {
            System.out.println("Tim kiem khong co ket qua: ");
        } else {
            System.out.println("Tim thay the: ");
            System.out.println(instance.at(n).toString());
        }
        return n;
    }

    @Override
    public Card remove() {
        int n;
        Card card = null;
        try {
            n = promptSearch();
            if (n == -1) {
                System.out.println("Ti kiem that bai, remove the that bai");
            } else {
                System.out.println("Xac nhan xoa the: ");
                System.out.println(instance.at(n).toString());
                int m = StringHelper.acceptInput("Co", "Khong");
                if (m == 1) {
                    card = instance.erase(n);
                    System.out.println("Da xoa the: ");
                    System.out.println(card.toString());
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Co loi xay ra, remove the that bai", e);
            throw e;
        }

        return card;
    }

    @Override
    public Card edit() {
        int n;
        Card card = null;
        try {
            n = promptSearch();
            if (n == -1) {
                System.out.println("Tim kiem that bai, edit the khong thanh cong");
            } else {
                int m;
                do {
                    card = instance.at(n);
                    System.out.println("Dang thao tac edit the: ");
                    System.out.println(card.toString());
                    System.out.println("Chon thao tac: ");
                    switch (m = StringHelper.acceptInput("Doi loai the", "Gia han the")) {
                        case 1 -> {
                            int a = StringHelper.acceptInput("regular", "pro", "vip", "ultimate");
                            switch (a) {
                                case 1 -> {
                                    card.setType(Card.regular);
                                }
                                case 2 -> {
                                    card.setType(Card.pro);
                                }
                                case 3 -> {
                                    card.setType(Card.vip);
                                }
                                case 4 -> {
                                    card.setType(Card.ultimate);
                                }
                            }
                        }
                        case 2 -> {
                            int days = StringHelper.acceptKey("So ngay gia han");
                            card.extendExpiration(new KhoangThoiGian(Duration.ofDays(days)));
                        }
                        default -> {
                            m = -1;
                            System.out.println("Ket thuc edit the");
                        }
                    }
                } while (m >= 0);
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Coi loi xay ra, edit the that bai", e);
            throw e;
        }

        return card;
    }

    @Override
    public int[] search() {
        throw new UnsupportedOperationException("Chuc nang chua dc code do ko du thoi gian");
    }

    public static Cards fromBatchBlob(PFArray<String[]> inp) {
        if (inp.size() < 1) {
            LOGGER.warning("No entries");
        } else {
            LOGGER.info(String.format("Batching %d x %d blob", inp.size(), inp.at(0).length));
        }
        return new Cards(inp);
    }
}
