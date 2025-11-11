package com.pcl.lms.controller;

import com.pcl.lms.DB.Database;
import com.pcl.lms.model.Teacher;
import com.pcl.lms.view.tm.TeacherTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class TeacherManagementFormController {
    public AnchorPane context;
    public TextField txtTeacherID;
    public TextField txtTeacherName;
    public TextField txtContactNumber;
    public Button btnSave;
    public TextField txtTeacherAddress;
    public TextField txtSearch;
    public TableColumn<TeacherTm,String> colId;
    public TableColumn<TeacherTm,String> colName;
    public TableColumn<TeacherTm,String> colContact;
    public TableColumn<TeacherTm,String> colAddress;
    public TableColumn<TeacherTm,Button> colOption;
    public TableView<TeacherTm> tblTeacher;
    String searchText = "";

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        setTeacherId();
        setTableData(searchText);

        // //when row selected it makes to fill fields
        tblTeacher.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                setData((TeacherTm)newValue);
            }
        });

        //search text
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            this.searchText = newValue;
            setTableData(searchText);
        });

    }

    private void setData(TeacherTm newValue) {
        txtTeacherID.setText(newValue.getId());
        txtTeacherName.setText(newValue.getName());
        txtContactNumber.setText(newValue.getContact());
        txtTeacherAddress.setText(newValue.getAddress());

        btnSave.setText("Update");
    }

    //fill the table
    private void setTableData(String searchText){
        ObservableList<TeacherTm> teacherTm = FXCollections.observableArrayList();
        for(Teacher th:Database.teacherTable){
            if(th.getName().toLowerCase().contains(searchText.toLowerCase())){
                Button btn = new Button("Delete");
                TeacherTm tm = new TeacherTm(
                        th.getId(),
                        th.getName(),
                        th.getContact(),
                        th.getAddress(),
                        btn
                );
                //delete button
                btn.setOnAction((ActionEvent event) -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION,"Are you sure you want to delete "+th.getId()+" taecher?",ButtonType.YES,ButtonType.NO);
                    alert.showAndWait();

                    if(alert.getResult()==ButtonType.YES){
                        Database.teacherTable.remove(th);
                        new Alert(Alert.AlertType.INFORMATION,th.getId() +" Student deleted Successfully").show();
                        setTeacherId();
                        setTableData(searchText);
                    }
                });
                teacherTm.add(tm);
            }
        }
        tblTeacher.setItems(teacherTm);
    }

    private void setTeacherId() {
        if (Database.teacherTable.isEmpty()) {
            txtTeacherID.setText("T-1");
        }else{
            //id generate
            Teacher lastTeacher = Database.teacherTable.get(Database.teacherTable.size()-1);
            String lastTeacherID = lastTeacher.getId();
            String[] splitData = lastTeacherID.split("-");
            String lastCharacter = splitData[1];
            int lastDigit = Integer.parseInt(lastCharacter);
            lastDigit++;
            String generatedId = "T-" + lastDigit;
            txtTeacherID.setText(generatedId);
        }
    }

    public void saveOnAction(ActionEvent actionEvent) {
        if(btnSave.getText().equals("Save")){
            Teacher teacher = new Teacher(
                txtTeacherID.getText(),
                txtTeacherName.getText(),
                txtTeacherAddress.getText(),
                txtContactNumber.getText()
        );
            Database.teacherTable.add(teacher);
            System.out.println(teacher.toString());
            new Alert(Alert.AlertType.INFORMATION,"Teacher Saved", ButtonType.OK).show();
            setTeacherId(); //next teacher
            clearFields();
            setTableData(searchText);

        }else{
           Optional<Teacher> slectedTeacher = Database.teacherTable.stream().filter(
                   teacher ->teacher.getId().equals(txtTeacherID.getText())).findFirst();
           if(slectedTeacher.isPresent()){
               slectedTeacher.get().setName(txtTeacherName.getText());
               slectedTeacher.get().setContact(txtContactNumber.getText());
               slectedTeacher.get().setAddress(txtTeacherAddress.getText());
               new Alert(
                       Alert.AlertType.INFORMATION, "Teacher "+txtTeacherID.getText()+" Updated", ButtonType.OK).show();
               setTeacherId();
               clearFields();
               setTableData(searchText);
               System.out.println("update successfully");
               btnSave.setText("Save");
           }
        }
    }

    private void clearFields() {
        txtTeacherName.clear();
        txtTeacherAddress.clear();
        txtContactNumber.clear();
    }

    public void newTeacherOnAction(ActionEvent actionEvent) {
        clearFields();
        setTeacherId();
        btnSave.setText("Save");
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashBoardForm");
    }

    private void setUi(String location) throws IOException {
        URL resource = getClass().getResource("/com/pcl/lms/view/"+location+".fxml");

        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage= (Stage) context.getScene().getWindow();
        stage.setScene(scene);
    }
}
