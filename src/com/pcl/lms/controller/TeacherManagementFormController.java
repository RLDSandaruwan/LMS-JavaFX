package com.pcl.lms.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class TeacherManagementFormController {
    public AnchorPane context;
    public TextField txtTeacherID;
    public TextField txtTeacherName;
    public TextField txtContactNumber;
    public Button btnSave;
    public TextField txtTeacherAddress;
    public TextField txtSearch;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colContact;
    public TableColumn colAddress;
    public TableColumn colOption;

    public void saveOnAction(ActionEvent actionEvent) {
    }

    public void newTeacherOnAction(ActionEvent actionEvent) {
    }

    public void backToHomeOnAction(ActionEvent actionEvent) {
    }
}
