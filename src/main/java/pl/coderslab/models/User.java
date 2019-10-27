package pl.coderslab.models;

import pl.coderslab.utils.PasswordUtil;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private int group_id;
    private String group_name;

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    //Konstruktor dla tworzenia nowego Usera, ktory zostanie wprowadzony do bazy lub dokona w niej modyfikacji
    // tj. dla metod create, update. Konstruktor nie przyjmuje id - baza danych sama nadaje id(auto_increment).
    public User(String username, String email, String password, int group_id) {
        this.username = username;
        this.email = email;
        this.password = PasswordUtil.createHash(password);
        this.group_id = group_id;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = PasswordUtil.createHash(password);
    }
    //Konstruktor do pobierania rekordu z bazy danych.
    public User() {
    }

    @Override
    public String toString() {
        return "Id: " + id + " Username: " + username + ", E-mail: " + email + ", Id grupy: " + group_id;
    }
}
