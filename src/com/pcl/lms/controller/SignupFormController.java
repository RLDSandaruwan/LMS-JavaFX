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

        boolean emailExists = Database.userTable.stream().anyMatch(u -> u.getEmail().equals(email));
        if(emailExists){
            new Alert(Alert.AlertType.ERROR, "Email already exists").show();
            return;
        }
        User user = new User(fullName, email, age, password);
        Database.userTable.add(user);
        System.out.println(user.toString());
        new Alert(Alert.AlertType.INFORMATION, "Account created successfully").show();

        setUi("LoginForm");
    }

    private void setUi(String location) throws IOException {

        URL resource = getClass().getResource("/com/pcl/lms/view/"+location+".fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage= (Stage) context.getScene().getWindow();
        stage.setScene(scene);

    }
}