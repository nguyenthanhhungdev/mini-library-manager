package ThuVien;

import Polyfill.ThoiGian;

public class Reader extends People {
    private static final String NAME = Reader.class.getName();
    // Uses builder pattern
    public Reader(String username, String password) {
        this.username = username;
        this.password = password;
        this.registration = ThoiGian.now();
        setId();
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public int getId() {
        return id;
    }

    public ThoiGian getRegistration() {
        return registration;
    }
    private People setId() {
        if(id == 0) {
            id = id_counter++;
            return this;
        } else throw new UnsupportedOperationException("Logic error in " + NAME);
    }

    private String username, password;
    private int id = 0;
    private ThoiGian registration;
    private static int id_counter = 1;
    public static String getName() {
        return NAME;
    }
}
