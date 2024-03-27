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
        Connection connection = DriverManager.getConnection(dbUrl);
        return connection;
    }

    @FXML
    public void onLogin(ActionEvent event) throws IOException, SQLException {

        Connection studentInfo;

        try {
            studentInfo = getConnection();

            Statement statement = studentInfo.createStatement();
            String query1 = "SELECT StudentID FROM studentInfo";
            ResultSet studentResult = statement.executeQuery(query1);

           while(studentResult.next()){
               System.out.println(studentResult);
            }

        }catch(SQLException err){
            System.out.println("Database cannot connect.");
        }

        String enteredUsername = Username.getText();
        String enteredPassword = Password.getText();

        logins.put("Username","Password");
        logins.put("Username2","Password");





        if (logins.containsKey(enteredUsername)){
            if (Objects.equals(logins.get(enteredUsername), enteredPassword)){
                welcomeText.setText("Login Correct. Redirecting");
                Username.setEditable(false);
                Password.setEditable(false);

                Parent root = FXMLLoader.load(Objects.requireNonNull(HelloController.class.getResource("testScene.fxml")));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }else{
                welcomeText.setText("Password is incorrect");
            }
        }else{
            welcomeText.setText("Username Not Found");
        }
    }

    @FXML
    protected void onExit() {
        System.exit(0);
    }


}