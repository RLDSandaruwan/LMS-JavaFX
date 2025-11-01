package com.pcl.lms.controller;

import com.pcl.lms.DB.Database;
import com.pcl.lms.model.Student;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.time.ZoneId;
import java.util.Date;

public class StudentManagementForm {
    public TextField txtStudentID;
    public TextField txtStudentName;
    public TextField txtStudentAddress;
    public DatePicker dtDOB;
    public TextField txtSearch;
    public Button btnSave;

    public void initialize(){
        setStudentID();
    }

    private void setStudentID() {
        if(Database.studentTable.isEmpty()){
            txtStudentID.setText("S-1");
        }
        else{
            //id generate
            Student lastStudent = Database.studentTable.get(Database.studentTable.size()-1);
            String lastStudentID = lastStudent.getId();
            String[] splitData = lastStudentID.split("-");
            String lastCharacter = splitData[1];
            int lastDigit = Integer.parseInt(lastCharacter);
            lastDigit++;
            String generatedID = "S-"+lastDigit;
            txtStudentID.setText(generatedID);
        }
    }

    private void clearFields() {
        txtStudentAddress.clear();
        txtStudentName.clear();
        dtDOB.setValue(null);
    }

    public void saveOnAction(ActionEvent actionEvent) {
        Student student = new Student(
                txtStudentID.getText(),
                txtStudentName.getText(),
                txtStudentAddress.getText(),
                Date.from(dtDOB.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())
        );
        Database.studentTable.add(student);
        System.out.println(student.toString());
        new Alert(Alert.AlertType.INFORMATION, "Student Saved", ButtonType.OK).show();
        setStudentID(); // for next student
        clearFields();
    }

}
