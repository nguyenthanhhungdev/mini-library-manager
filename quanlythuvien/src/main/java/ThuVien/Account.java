package ThuVien;

import Polyfill.KhoangThoiGian;
import Polyfill.StringHelper;
import Polyfill.ThoiGian;

public abstract class Account extends People {
    public Account(int id, String username) {
        this(id, username, ThoiGian.now());
    }

    protected Account(int id, String username, ThoiGian registration) {
        super(id);
        this.username = username;
        this.registration = registration;
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return StringHelper.isNullOrBlank(this.password) || this.password.equals(password);
    }

    public boolean changePassword(String oldPassword, String newPassword) {
        if (oldPassword == null) {
            oldPassword = "";
        }
        if (!checkPassword(oldPassword.trim())) {
            return false;
        }
        password = newPassword.trim();
        return true;
    }

    protected String getPassword() {
        return password;
    }

    public ThoiGian getRegistration() {
        return registration;
    }

    @Override
    public String toString() {
        return StringHelper.liner(super.toString(),
                StringHelper.itemer("Username", username),
                StringHelper.itemer("Password", StringHelper.spacer("[" + password.length(), "character]")),
                StringHelper.itemer("Registration date", registration),
                StringHelper.itemer("Registered", KhoangThoiGian.between(registration, ThoiGian.now())));
    }

    private final String username;
    private String password;
    private final ThoiGian registration;
}
