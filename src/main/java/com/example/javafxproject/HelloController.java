package com.example.javafxproject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.sql.*;
import java.util.*;
import java.io.IOException;
import java.util.*;

import static java.lang.Integer.parseInt;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField Username;
    @FXML
    private TextField Password;
    public HashMap<String, String> logins = new HashMap<String, String>();

    public ArrayList<Student> Student = new ArrayList<>();



    private static Connection getConnection() throws SQLException {
        String dbUrl = "jdbc:sqlite:data.sqlite";
        return DriverManager.getConnection(dbUrl);
    }

    @FXML
    public void onLogin(ActionEvent event) throws IOException, SQLException {
        String query1 = "SELECT StudentID FROM studentInfo";
        String query2 = "SELECT Name FROM studentInfo";
        String query3 = "SELECT Gender FROM studentInfo";
        String query4 = "SELECT Age FROM studentInfo";
        Connection studentInfo;


        String requestID = "";
        try {
            studentInfo = getConnection();

            Statement statement = studentInfo.createStatement();
            Statement statement2 = studentInfo.createStatement();
            Statement statement3 = studentInfo.createStatement();
            Statement statement4 = studentInfo.createStatement();


            ResultSet stID = statement.executeQuery(query1);
            ResultSet stName = statement2.executeQuery(query2);
            ResultSet stSex = statement3.executeQuery(query3);
            ResultSet stAge = statement4.executeQuery(query4);


            while (stID.next()) {
//                System.out.println(stID.getInt("StudentID") + "\t");
                requestID = (stID.getString("StudentID"));


            }
            while (stName.next()) {
//                System.out.println(stName.getString("Name") + "\t");
            }
            while (stSex.next()) {
//                System.out.println(stSex.getString("Gender") + "\t");
            }
            while (stAge.next()) {
//                System.out.println(stAge.getInt("Age") + "\t");
            }

        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }

        String enteredUsername = Username.getText();


        

        System.out.println((enteredUsername));
        System.out.println(requestID);

        if (enteredUsername.equals(requestID)) {
            System.out.println("IDs match");

        }else{
            System.out.println("IDs do not match");
        }
    }

    @FXML


    public void onNewStudent(ActionEvent actionEvent) {
    }

    public void onStudentLookup(ActionEvent actionEvent) {
    }

    public void onAllStudents(ActionEvent actionEvent) {
    }

    public void onToHome(ActionEvent actionEvent) {
    }

    public void onSearch(ActionEvent actionEvent) {
    }
}