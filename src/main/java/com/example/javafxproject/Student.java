package com.example.javafxproject;
import java.util.*;

public class Student {
    int studentID;
    int age;
    String name;
    String sex;

    public Student(int studentID, String name, int age, String sex){
        this.studentID = studentID;
        this.age = age;
        this.name = name;
        this.sex = sex;
    }

    public void getID(){
        System.out.println(this.studentID);
    }

    public void setID(int id){
        this.studentID = id;
    }
}
