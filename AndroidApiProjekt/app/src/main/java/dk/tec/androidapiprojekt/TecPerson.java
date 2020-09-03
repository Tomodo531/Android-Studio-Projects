package dk.tec.androidapiprojekt;

import java.io.Serializable;

public class TecPerson implements Serializable {

    private int id;
    private String name;
    private String job;
    private int age;
    private double height;
    private boolean adult;
    private int shoes;

    public TecPerson() {
        this.id = -1;
    }

    public TecPerson(int id, String name, String job, int age, double height, boolean adult, int shoes)
    {
        this.id = id;
        this.name = name;
        this.job = job;
        this.age = age;
        this.height = height;
        this.adult = adult;
        this.shoes = shoes;
    }

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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public int getShoes() {
        return shoes;
    }

    public void setShoes(int shoes) {
        this.shoes = shoes;
    }
}
