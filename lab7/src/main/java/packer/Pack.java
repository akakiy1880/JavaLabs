package packer;

import dragon.Dragon;

import java.io.Serializable;

public class Pack implements Serializable {
    String commandName;
    String[] arg;
    Dragon dragon;
    String user;
    String password;
    boolean reg;

    public Pack() {
        reg = false;
    }

    public void pack(String commandName) {
        this.commandName = commandName;
    }

    public void pack(String commandName, String[] arg) {
        this.commandName = commandName;
        this.arg = arg;
    }
    public void packUser(String user, String password, boolean reg) {
        this.user = user;
        this.password = password;
        this.reg = reg;
    }

    public void pack(String commandName, Dragon dragon) {
        this.commandName = commandName;
        this.dragon = dragon;
    }

    public String getCommandName() {
        return commandName;
    }

    public String[] getArg() {
        return arg;
    }

    public Dragon getDragon() {
        return dragon;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public void setReg(boolean reg) {
        this.reg = reg;
    }

    public boolean getReg() {
        return reg;
    }
}
