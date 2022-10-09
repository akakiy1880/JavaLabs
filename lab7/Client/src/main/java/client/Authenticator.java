package client;

import utils.IOUtils;

public class Authenticator {
    private String username;
    private String password;

    public String username() {
        while (true) {
            IOUtils.print("Введите имя пользователя: ");
            username = IOUtils.input();
            if (username.length() < 3 || username.length() > 16)
                IOUtils.printErr("Длина имени должна быть от 3 до 16 символов!");
            else
                return username.trim();
        }
    }

    public String password() {
        while (true) {
            if (System.console() == null) {
                IOUtils.print("Введите пароль: ");
                password = IOUtils.input();
                if (password.length() < 3 || password.length() > 16)
                    IOUtils.printErr("Длина пароля должна быть от 6 до 32 символов!");
                else
                    return password.trim();
            } else {
                char[] passwordChars = System.console().readPassword("Введите пароль: ");
                password = new String(passwordChars).trim();
                if (password.length() < 3 || password.length() > 16)
                    IOUtils.printErr("Длина пароля должна быть от 6 до 32 символов!");
                else
                    return password.trim();
            }
        }
    }
}
