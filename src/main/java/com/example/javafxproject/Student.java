package com.example.javafxproject;

public class Student {
    private final String name;
    private final String id;
    private final String age;
    private final String gender;


    public Student(String name, String id, String age, String gender) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.gender = gender;

    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getId() {
        return id;
    }
}
