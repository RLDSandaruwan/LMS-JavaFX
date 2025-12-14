package com.pcl.lms.controller;

import com.pcl.lms.env.StaticResource;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DashBoardFormController {
    public Label lblDate;
    public Label lblTime;
    public Label lblVersion;
    public AnchorPane context;

    public void initialize(){
        setStaticData();
    }

    private void setStaticData() {
        lblVersion.setText(StaticResource.getVERSION());

        String dateForamt = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        lblDate.setText(dateForamt);

        Timeline clock = new Timeline(new KeyFrame(Duration.seconds(1),e ->{
            String timeForamt = new SimpleDateFormat("HH:mm:ss").format(new Date());
            lblTime.setText(timeForamt);
        }));

        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();
    }

    private void setUi(String location) throws IOException {

        Stage stage =(Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/com/pcl/lms/view/"+location+".fxml"))));
    }

    public void btnNavigatetoLoginFormOnAction(ActionEvent actionEvent) throws IOException {
        setUi("LoginForm");
    }

    public void studentManagementOnAction(ActionEvent actionEvent) throws IOException {
        setUi("StudentManagmentForm");
    }

    public void teacherManagementOnAction(ActionEvent actionEvent) throws IOException {
        setUi("TeacherManagementForm");
    }

    public void programmeManagementOnAction(ActionEvent actionEvent) throws IOException {
        setUi("ProgrammeManagementForm");
    }

    public void intakemeManagementOnAction(ActionEvent actionEvent) throws IOException {
        setUi("IntakeManagementForm");
    }

    public void StudentRegistationOnAction(ActionEvent actionEvent) throws IOException {
        setUi("RegistrationForm");
    }
}
