package com.example.javafxproject;

//This program was created by Peyton Girardin for CSI 2300

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.*;
import java.util.*;
import java.io.IOException;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

public class HelloController {

    public String SearchedID = "";

    public Label StudentID_text;
    @FXML
    public Label Name_text;
    @FXML
    public Label Sex_text;
    @FXML
    public Label enteredStudentID;
    @FXML
    public TextField studentIDInput;
    @FXML
    public Label Age_Text;
    @FXML
    public Label studentAddHeader;
    @FXML
    public TextField studentAgeInput;
    @FXML
    public TextField studentGenderInput;
    @FXML
    public TextField studentNameInput;
    @FXML
    public TextField AddstudentIDInput;
    public TableView<Student> tableView;

    @FXML
    public TableColumn<Student, String> columnStuID;
    @FXML
    public TableColumn<Student, String> columnStuName;
    @FXML
    public TableColumn<Student, String> columnStuGender;
    @FXML
    public TableColumn<Student, String> columnStuAge;
    public TextField studentIDInputEdit;
    public TextField studentNameInputEdit;
    public TextField studentAgeInputEdit;
    public TextField studentGenderInputEdit;
    @FXML
    private Label welcomeText;
    @FXML
    private TextField Username;
    ObservableList<Student> data;



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


    public void onNewStudent(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloController.class.getResource("studentAdd.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onStudentLookup(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloController.class.getResource("studentLookup.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onAllStudents(ActionEvent actionEvent) throws IOException, InterruptedException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloController.class.getResource("allStudents.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onToHome(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloController.class.getResource("hello-view.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onSearch(ActionEvent actionEvent) {
        String enteredID = studentIDInput.getText();
        String query1 = "SELECT * FROM studentInfo";
        Connection studentInfo;

        try {
            studentInfo = getConnection();
            Statement statement = studentInfo.createStatement();
            ResultSet rs = statement.executeQuery(query1);

            while (rs.next()){
                String convertedResults = rs.getString("StudentID");
                String convertedResults2 = rs.getString("Name");
                String convertedResults3 = rs.getString("Age");
                String convertedResults4 = rs.getString("Gender");
                if (enteredID.equals(convertedResults) ){
                    StudentID_text.setText("Student ID: " + convertedResults);
                    Name_text.setText("Student Name: " + convertedResults2);
                    Age_Text.setText("Student Age: " + convertedResults3);
                    Sex_text.setText("Student Gender: " + convertedResults4);
                    studentInfo.close();
                    break;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
    }

    public void onAdd(ActionEvent actionEvent) throws SQLException {
        String studentIDA = AddstudentIDInput.getText();
        String studentNameA = studentNameInput.getText();
        String studentGenderA = studentGenderInput.getText();
        String studentAgeA = studentAgeInput.getText();

        String query1 = "INSERT INTO studentInfo (StudentID, Name, Gender, Age) VALUES ('"+studentIDA+"', '"+studentNameA+"', '"+studentGenderA+"', '"+studentAgeA+"')";
        Connection studentInfo;

        try{
            studentInfo = getConnection();
            Statement statement = studentInfo.createStatement();
            statement.executeUpdate(query1);
            studentAddHeader.setText("Records have been inserted");
            studentInfo.close();
        } catch (SQLException e){
            System.out.println(e);
        }
    }

    public void onDelete(ActionEvent actionEvent) {
        String enteredID = studentIDInput.getText();
        String query2 = "DELETE FROM studentInfo WHERE studentID = '"+enteredID+"'";
        Connection studentInfo;

        try {
            studentInfo = getConnection();
            Statement statement = studentInfo.createStatement();
            statement.executeUpdate(query2);
            studentInfo.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
        enteredStudentID.setText("Student deleted.");
    }


    private ObservableList<Student> fetchDataFromDatabase() {
        ObservableList<Student> data = FXCollections.observableArrayList();
        Connection studentInfo;
        String stuID;
        String stuName;
        String stuGender;
        String stuAge;

        try {
            studentInfo = getConnection();
            Statement statement = studentInfo.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM studentInfo");

            while (rs.next()) {
                stuID = rs.getString(1);
                stuName = rs.getString(2);
                stuGender = rs.getString(3);
                stuAge = rs.getString(4);

                data.add(new Student(stuName, stuID, stuAge, stuGender));

            }
        } catch (SQLException er) {
            System.out.println(er);
        }

        return data;
    }


    public void refreshPage() {
        tableView.getColumns().clear();
        tableView.getColumns().addAll(columnStuID, columnStuAge, columnStuName, columnStuGender);

        columnStuID.setCellValueFactory(new PropertyValueFactory<Student, String>("id"));
        columnStuName.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        columnStuAge.setCellValueFactory(new PropertyValueFactory<Student, String>("age"));
        columnStuGender.setCellValueFactory(new PropertyValueFactory<Student, String>("gender"));

        ObservableList<Student> data = fetchDataFromDatabase();
        tableView.setItems(data);


    }

    public void refreshAll(ActionEvent actionEvent) {
        refreshPage();
    }

    public void onEditStudent(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloController.class.getResource("studentEdit.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onSave(ActionEvent actionEvent) {
        String enteredID = studentIDInputEdit.getText();
        String enteredName = studentNameInputEdit.getText();
        String enteredAge = studentAgeInputEdit.getText();
        String enteredGender = studentGenderInputEdit.getText();
        String query1 = "UPDATE studentInfo SET studentId = '"+enteredID+"', Name = '"+enteredName+"', Age = '"+enteredAge+"', Gender = '"+enteredGender+"' WHERE studentId = '"+SearchedID+"' ";
        Connection studentInfo;

        try {
            studentInfo = getConnection();
            Statement statement = studentInfo.createStatement();
            statement.executeUpdate(query1);

        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
    }

    public void onSearchEdit(ActionEvent actionEvent) {
        String enteredID = studentIDInputEdit.getText();
        SearchedID = studentIDInputEdit.getText();
        String query1 = "SELECT * FROM studentInfo";
        Connection studentInfo;

        try {
            studentInfo = getConnection();
            Statement statement = studentInfo.createStatement();
            ResultSet rs = statement.executeQuery(query1);

            while (rs.next()){
                String convertedResults = rs.getString("StudentID");
                String convertedResults2 = rs.getString("Name");
                String convertedResults3 = rs.getString("Age");
                String convertedResults4 = rs.getString("Gender");
                if (enteredID.equals(convertedResults) ){
                    studentIDInputEdit.setText(convertedResults);
                    studentNameInputEdit.setText(convertedResults2);
                    studentAgeInputEdit.setText(convertedResults3);
                    studentGenderInputEdit.setText(convertedResults4);
                    studentInfo.close();
                    break;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
    }
}

