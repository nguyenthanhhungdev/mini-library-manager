package ThuVien;

import java.util.stream.IntStream;

import Polyfill.PFArray;
import Polyfill.StringHelper;
import Polyfill.ThoiGian;

public class Reader extends Account implements IDataProcess<Reader> {
    public Reader(int id, String username) {
        super(id, username);
    }

    protected Reader(int id, String username, ThoiGian registration) {
        super(id, username, registration);
    }

    public Card getCard() {
        return card;
    }

    protected Reader setCard(Card card) {
        this.card = card;
        return this;
    }

    public boolean borrows(Documents documents_instance) {
        // TODO: accept user input and search to get id
        int id = 0;
        Document __ = documents_instance.getById(id);
        if (!__.borrows()) {
            return false;
        }
        borrowings.push_back(__);
        return true;
    }

    public boolean returns(Documents documents_instance) {
        // TODO: accept user input and search to get id
        int id = 0;
        IntStream.range(0, borrowings.size()).filter(i -> borrowings.at(i).getId() == id).findAny().orElse(-1);
        // check input...
        return false;
    }

    public static Reader fromBlob(String[] inp, Cards cards_instance) {
        int id = Integer.parseInt(inp[0]);
        String username = inp[1];
        String password = inp[2];
        String name = inp[3];
        ThoiGian regtime = ThoiGian.parseTG(inp[4]);
        ThoiGian borntime = ThoiGian.parseTG(inp[5]);
        String phone = inp[6];
        String email = inp[7];
        String address = inp[8];
        Card card = cards_instance.getById(Integer.parseInt(inp[9]));
        Reader __ = new Reader(id, username, regtime).setCard(card);
        __.setName(name).setBirth(borntime).setPhone(phone).setEmail(email).setAddress(address);
        __.changePassword(null, password);
        return __;
    }

    public String[] toBlob() {
        return new String[] { String.valueOf(getId()), getUsername(), getPassword(), getName(),
                getRegistration().toString(), getBirth().toString(), getPhone(), getEmail(), getAddress(),
                String.valueOf(card.getId()) };
    }

    @Override
    public String toString() {
        return StringHelper.liner(super.toString(),
                StringHelper.itemer("Username", getUsername()),
                StringHelper.itemer("Registration date", getRegistration()),
                StringHelper.itemer("Currently borrowing", StringHelper.obj2str(borrowings)));
    }

    private PFArray<Document> borrowings = new PFArray<>();
    private Card card;
}
