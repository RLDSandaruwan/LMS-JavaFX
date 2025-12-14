package com.pcl.lms.controller;

import com.pcl.lms.DB.Database;
import com.pcl.lms.model.Enroll;
import com.pcl.lms.model.Program;
import com.pcl.lms.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationFormController {
    public AnchorPane context;
    public TextField txtId;
    public Button btnSave;
    public ComboBox<String> cmbProgram;
    public ComboBox<String> cmbStudent;
    public TextField txtSearch;
    public RadioButton rbtnPaid;
    public RadioButton rbtnUnpaid;
    public ToggleGroup rbtnPayement;
    String searchText = "";

    public void initialize() {
        setProgramData();
        setStudentData(searchText);
        setStudentID();

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
           if(newValue != null) {
               searchText = newValue;
               setStudentData(searchText);
               cmbStudent.show();
           }
        });

        cmbStudent.valueProperty().addListener((observable, oldValue, newValue) -> {
           setStudentID();
        });
    }

    private void setStudentID() {
        if(cmbStudent.getValue() ==null){
            txtId.setPromptText("Select a student");

        }else{
            String studentComboValue = cmbStudent.getValue();
            String[] splittedComboValue = studentComboValue.split("-");
            txtId.setText(splittedComboValue[0] + "-" + splittedComboValue[1]);
        }
    }

    private void setStudentData(String searchText) {
        ObservableList<String> studentObList = FXCollections.observableArrayList();
        studentObList.clear();
        if(!Database.studentTable.isEmpty()){
            for (Student st:Database.studentTable){
                if (st.getName().toLowerCase().contains(searchText.toLowerCase())){
                    studentObList.add(st.getId()+"-"+st.getName());
                }

            }
            cmbStudent.setItems(studentObList);
        }

    }

    private void setProgramData() {
        ObservableList<String> programOblist = FXCollections.observableArrayList();

        if(Database.programTable.isEmpty()) {
            cmbProgram.setValue("Program not found");
        }else{
            for(Program program: Database.programTable) {
                programOblist.add(program.getProgramid()+"-"+program.getProgramname());
            }
            cmbProgram.setItems(programOblist);
        }
    }

    public void newRegistrationOnAction(ActionEvent actionEvent) {
        clearFileds();
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashBoardForm");
    }

    public void saveOnAction(ActionEvent actionEvent) {
        Database.enrollTable.add(new Enroll(
                cmbStudent.getValue(),
                cmbProgram.getValue(),
                rbtnPaid.isSelected()
        ));
        new Alert(Alert.AlertType.INFORMATION, "New Registration Successful").show();
        System.out.println(Database.enrollTable);
        clearFileds();
    }

    private void clearFileds() {
        txtId.clear();
        rbtnPaid.setSelected(false);
        rbtnUnpaid.setSelected(false);
    }


    private void setUi(String location) throws IOException {
        Stage stage =(Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/com/pcl/lms/view/"+location+".fxml"))));
    }
}
