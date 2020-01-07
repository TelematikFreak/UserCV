package es.ulpgc.alexmoreno.usercv.data;

import io.realm.RealmObject;

public class User extends RealmObject {
    private int id;
    private String name;
    private String surname;
    private int age;
    private String job;
    private String idNumber;
    private int cv;

    // examen
    private int rate;

    public User(){

    }

    public User(int id, String name, String surname, int age, String job, String idNumber, int cv, int rate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.job = job;
        this.idNumber = idNumber;
        this.cv = cv;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public int getCv() {
        return cv;
    }

    public void setCv(int cv) {
        this.cv = cv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
