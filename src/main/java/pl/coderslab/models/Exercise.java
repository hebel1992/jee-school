package pl.coderslab.models;

public class Exercise {
    private int id;
    private String title;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //Konstruktor dla tworzenia nowego Zadania, ktory zostanie wprowadzony do bazy lub dokona w niej modyfikacji
    //tj. dla metod create, update. Konstruktor nie przyjmuje id - baza danych sama nadaje id(auto_increment).
    public Exercise(String title, String description) {
        this.title = title;
        this.description = description;
    }

    //Konstruktor do pobierania rekordu z bazy danych.
    public Exercise() {
    }

    @Override
    public String toString() {
        return "ID zadania: " + id + ", Tytul zadania: " + title + ", Opis: " + description;
    }
}
