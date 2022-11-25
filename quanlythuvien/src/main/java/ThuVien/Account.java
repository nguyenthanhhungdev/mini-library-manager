package ThuVien;

import Polyfill.StringHelper;
import Polyfill.ThoiGian;

public class Account extends People {
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
        if (!checkPassword(oldPassword.trim())) {
            return false;
        }
        password = newPassword.trim();
        return true;
    }

    public ThoiGian getRegistration() {
        return registration;
    }

    @Override
    public String toString() {
        return StringHelper.liner(super.toString(),
                StringHelper.itemer("Username", username),
                StringHelper.itemer("Password", StringHelper.spacer("[" + password.length(), "character]")),
                StringHelper.itemer("Registration date", registration));
    }

    private final String username;
    private String password;
    private final ThoiGian registration;
}
