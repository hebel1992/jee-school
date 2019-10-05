package pl.coderslab.models;

import java.sql.Timestamp;

public class Solution {
    private int id;
    private Timestamp created;
    private Timestamp updated;
    private String description;
    private int exercise_id;
    private int user_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    //Konstruktor dla tworzenia nowego Rozwiazania, ktore zostanie wprowadzony do bazy lub dokona w niej modyfikacji
    // tj. dla metod create, update. Konstruktor nie przyjmuje id - baza danych sama nadaje id(auto_increment).
    public Solution(Timestamp created, Timestamp updated, String description, int exercise_id, int user_id) {
        this.created = created;
        this.updated = updated;
        this.description = description;
        this.exercise_id = exercise_id;
        this.user_id = user_id;
    }

    //Konstruktor do pobierania rekordu z bazy danych.
    public Solution() {
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", created: " + created +
                ", updated: " + updated +
                ", description: " + description +
                ", exercise_id: " + exercise_id +
                ", user_id: " + user_id;
    }
}
