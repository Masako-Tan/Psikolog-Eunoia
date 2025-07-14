package edu.uph.m23si1.aplikasi_psikolog.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Reminder extends RealmObject {
    @PrimaryKey
    private String id;
    private String date; // Format: dd-MM-yyyy
    private String task;
    private boolean isDone;

    public Reminder() {}

    public Reminder(String id, String date, String task, boolean isDone) {
        this.id = id;
        this.date = date;
        this.task = task;
        this.isDone = isDone;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTask() { return task; }
    public void setTask(String task) { this.task = task; }

    public boolean isDone() { return isDone; }
    public void setDone(boolean done) { isDone = done; }
}
