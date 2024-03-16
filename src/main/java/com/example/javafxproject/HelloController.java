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

    private Stage stage;
    private Scene scene;



    @FXML
    public void onLogin(ActionEvent event) throws IOException {
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
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
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