package es.ulpgc.alexmoreno.usercv.data;

import io.realm.RealmObject;

public class Curriculum extends RealmObject {
    private int id;
    private String title;
    private String cvDescription;

    public Curriculum(){

    }

    public Curriculum(int id, String title, String cvDescription) {
        this.id = id;
        this.title = title;
        this.cvDescription = cvDescription;
    }

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

    public String getCvDescription() {
        return cvDescription;
    }

    public void setCvDescription(String cvDescription) {
        this.cvDescription = cvDescription;
    }
}
