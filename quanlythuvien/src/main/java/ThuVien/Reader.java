package ThuVien;

import Polyfill.PFArray;
import Polyfill.StringHelper;
import Polyfill.ThoiGian;

public class Reader extends People {
    public Reader(String username, String password) {
        this(++id_counter, username, password);
    }

    public Reader(long id, String username, String password) {
        super(id);
        if (id_counter < id) {
            id_counter = id;
        }
        this.username = username;
        this.password = password;
        this.registration = ThoiGian.now();
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public ThoiGian getRegistration() {
        return registration;
    }

    public boolean borrows(Document document) {
        if (!document.borrows()) {
            return false;
        }
        borrowings.push_back(document);
        return true;
    }

    // Employee must do a search to find out the index to remove
    public boolean returns(int borrowings_index) {
        if (borrowings_index < 0 || borrowings_index > borrowings.size()) {
            return false;
        }
        borrowings.erase(borrowings_index).returns();
        return true;
    }

    @Override
    public String toString() {
        return StringHelper.liner(super.toString(),
                StringHelper.itemer("Username", username),
                StringHelper.itemer("Registration date", registration),
                StringHelper.itemer("Currently borrowing", StringHelper.obj2str(borrowings)));
    }

    private String username, password;
    private ThoiGian registration;
    private static long id_counter = 0;

    private PFArray<Document> borrowings = new PFArray<>();
}
