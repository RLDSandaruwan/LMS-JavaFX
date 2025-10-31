package com.pcl.lms.controller;

import com.pcl.lms.env.StaticResource;
import com.pcl.lms.utill.security.PasswordManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class VerifyOTPFormController {
    public AnchorPane context;
    public Label lblCompany;
    public Label lblVersion;
    public Label lblUserEmail;
    public TextField txtOtp;
    private int OTP;
    private String email;

    public void initialize() {
        setStaticData();
    }

    private void setStaticData() {
        lblCompany.setText(StaticResource.getCOMPANY());
        lblVersion.setText(StaticResource.getVERSION());

    }

    public void navigateEmailVerificationFormOnAction(ActionEvent actionEvent) throws IOException {
        setUi("EmailVerificationForm");
    }

    public void seteUserData(int verificationCode,String email){
        this.email=email;
        this.OTP=verificationCode;
        lblUserEmail.setText(email);
    }

    public void navigatePasswordResetFormOnAction(ActionEvent actionEvent) throws IOException {
        if(OTP==Integer.parseInt(txtOtp.getText())){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/pcl/lms/view/ResetPasswordForm.fxml"));
            Parent parent=loader.load();
            ResetPasswordFormController controller = loader.getController();
            controller.setUserData(email);

            Stage stage= (Stage) context.getScene().getWindow();
            stage.setScene(new Scene(parent));
        }else {
            new Alert(Alert.AlertType.ERROR,"OTP is wrong").show();
        }
    }
    private void setUi(String location) throws IOException {
        URL resource = getClass().getResource("/com/pcl/lms/view/"+location+".fxml");

        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage= (Stage) context.getScene().getWindow();
        stage.setScene(scene);
    }


}