package pl.coderslab.models;

public class Group {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Konstruktor dla tworzenia nowej Grupy, ktora zostanie wprowadzona do bazy lub dokona w niej modyfikacji
    // tj. dla metod create, update. Konstruktor nie przyjmuje id - baza danych sama nadaje id(auto_increment).
    public Group(String name) {
        this.name = name;
    }

    //Konstruktor do pobierania rekordu z bazy danych.
    public Group() {

    }

    @Override
    public String toString() {
        return "ID grupy: " + id + ", Nazwa grupy: " + name;
    }
}
