package ThuVien;

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

    public Reader fromBlob(String[] inp) {
        int id = Integer.parseInt(inp[0]);
        String username = inp[1];
        String password = inp[2];
        String name = inp[3];
        ThoiGian regtime = new ThoiGian(inp[4]);
        ThoiGian borntime = new ThoiGian(inp[5]);
        String phone = inp[6];
        String email = inp[7];
        String address = inp[8];
        Reader __ = new Reader(id, username, regtime);
        __.setName(name).setBirth(borntime).setPhone(phone).setEmail(email).setAddress(address);
        __.changePassword(null, password);
        return __;
    }

    public String[] toBlob() {
        return new String[] { String.valueOf(getId()), getUsername(), getPassword(), getName(),
                getRegistration().toString(), getBirth().toString(), getPhone(), getEmail(), getAddress() };
    }

    @Override
    public String toString() {
        return StringHelper.liner(super.toString(),
                StringHelper.itemer("Username", getUsername()),
                StringHelper.itemer("Registration date", getRegistration()),
                StringHelper.itemer("Currently borrowing", StringHelper.obj2str(borrowings)));
    }

    private PFArray<Document> borrowings = new PFArray<>();
}
