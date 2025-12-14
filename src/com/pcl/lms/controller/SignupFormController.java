package com.pcl.lms.controller;

import com.pcl.lms.DB.Database;
import com.pcl.lms.env.StaticResource;
import com.pcl.lms.model.User;
import com.pcl.lms.utill.security.PasswordManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignupFormController {
    public PasswordField txtPassword;
    public TextField txtFullName;
    public TextField txtAge;
    public TextField txtEmail;
    public VBox context;
    public Label lblCompany;
    public Label lblVersion;

    public void initialize() {
        setStaticData();
    }

    private void setStaticData() {
        lblCompany.setText(StaticResource.getCOMPANY());
        lblVersion.setText(StaticResource.getVERSION());
    }


    public void alreadyHaveAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUi("LoginForm");
    }

    public void signUpOnAction(ActionEvent actionEvent) throws IOException {
        String email = txtEmail.getText();
        String fullName = txtFullName.getText();
        int age = Integer.parseInt(txtAge.getText());
        String password = new PasswordManager().encode(txtPassword.getText());

        User user = new User(email,fullName,age,password);
        try{
            signup(user);
            System.out.println(user.toString());

            new Alert(Alert.AlertType.INFORMATION,"Account Created").show();
            setUi("LoginForm");
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

    }

    private boolean signup(User user) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextstackmvc","root","Hello@123");
        /*String sql="INSERT INTO user VALUES('"+user.getEmail()+"','"+user.getFullName()+"','"+user.getAge()+"','"+user.getPassword())";*/

        String sql="INSERT INTO user VALUES(?,?,?,?)";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, user.getEmail());
        ps.setString(2,user.getFullName());
        ps.setInt(3,user.getAge());
        ps.setString(4,user.getPassword());
/*
        int rowCount = ps.executeUpdate();
        if (rowCount > 0) {
            return true;
        }return false;*/
        return ps.executeUpdate()>0;
    }

    private void setUi(String location) throws IOException {

        URL resource = getClass().getResource("/com/pcl/lms/view/"+location+".fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage= (Stage) context.getScene().getWindow();
        stage.setScene(scene);

    }
}