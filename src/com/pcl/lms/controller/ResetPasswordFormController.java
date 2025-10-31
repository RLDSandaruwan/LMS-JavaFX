package com.pcl.lms.controller;

import com.pcl.lms.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ResetPasswordFormController {
    public AnchorPane context;
    private String email;

    public void setUserData(String email){
        this.email=email;
    }
    public User user;

    public void navigateOtpVerificationForm(ActionEvent actionEvent) throws IOException {
        setUi("VerifyOTPForm");
    }

    public void resetPasswordOnAction(ActionEvent actionEvent) throws IOException {
        setUi("LoginForm");
    }


    private void setUi(String location) throws IOException {
        Stage stage =(Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/com/pcl/lms/view/"+location+".fxml"))));
    }
}