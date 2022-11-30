package ThuVien;

import Polyfill.PFArray;
import Polyfill.StringHelper;
import Polyfill.ThoiGian;

import java.util.logging.Level;
import java.util.logging.Logger;

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
        Card card = new Card(genNextId(), ThoiGian.now());
        int userInp = StringHelper.acceptInput("regular", "pro", "vip", "ultimate");
        switch (userInp) {
            case 1 -> {
                card.setPrice_multiplier(regular);
                card.setExpiration(card.getCreation().modNgay(180));
            }
            case 2 -> {
                card.setPrice_multiplier(pro);
                card.setExpiration(card.getCreation().modNgay(240));
            }
            case 3 -> {
                card.setPrice_multiplier(vip);
                card.setExpiration(card.getCreation().modNgay(300));
            }
            case 4 -> {
                card.setPrice_multiplier(ultimate);
                card.setExpiration(card.getCreation().modNgay(365));
            }
        }
        return card;
    }

    private int promptSearch() {
        int n;
        try {
            n = search(Integer.parseInt(StringHelper.acceptLine("Nhap ma the: ")));
            if (n == -1) {
                System.out.println("Tim kiem khong co ket qua: ");
            } else {
                System.out.println("Tim thay the: ");
                System.out.println(instance.at(n).toString());
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Input error", e);
            throw e;
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
                    switch (m = StringHelper.acceptInput("Loai the", "Thoat")) {
                        case 1 -> {
                            int a = StringHelper.acceptInput("regular", "pro", "vip", "ultimate");
                            switch (a) {
                                case 1 -> {
                                    card.setPrice_multiplier(regular);
                                    card.setExpiration(card.getCreation().modNgay(180));
                                }
                                case 2 -> {
                                    card.setPrice_multiplier(pro);
                                    card.setExpiration(card.getCreation().modNgay(240));
                                }
                                case 3 -> {
                                    card.setPrice_multiplier(vip);
                                    card.setExpiration(card.getCreation().modNgay(300));
                                }
                                case 4 -> {
                                    card.setPrice_multiplier(ultimate);
                                    card.setExpiration(card.getCreation().modNgay(365));
                                }
                            }
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
        return new Cards(inp);
    }

    public static final float regular = 1.f;
    public static final float pro = .9f;
    public static final float vip = .75f;
    public static final float ultimate = .5f;
}
