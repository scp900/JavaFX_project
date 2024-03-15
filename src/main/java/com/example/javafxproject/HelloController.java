package com.example.javafxproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.*;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField Username;
    @FXML
    private TextField Password;

    public HashMap<String, String> logins = new HashMap<String, String>();

    @FXML
    protected void onLogin() {
        String enteredUsername = Username.getText();
        String enteredPassword = Password.getText();

        logins.put("Username","Password");
        logins.put("Username2","Password");

        if (logins.containsKey(enteredUsername)){
            System.out.println("Username Found");
            if (Objects.equals(logins.get(enteredUsername), enteredPassword)){
                System.out.println("Password is correct");
            }else{
                System.out.println("Password is incorrect");
            }
        }else{
            System.out.println("Username Not Found");
            welcomeText.setText("Username Not Found");
        }
    }

    @FXML
    protected void onExit() {
        System.exit(0);
    }


}