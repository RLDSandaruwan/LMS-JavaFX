package com.pcl.lms.controller;

import com.pcl.lms.DB.Database;
import com.pcl.lms.model.Student;
import com.pcl.lms.view.tm.StudentTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

public class StudentManagementForm {
    public TextField txtStudentID;
    public TextField txtStudentName;
    public TextField txtStudentAddress;
    public DatePicker dtDOB;
    public TextField txtSearch;
    public Button btnSave;
    public TableView<StudentTm> tblStudent;
    public TableColumn<StudentTm,String> colID;
    public TableColumn<StudentTm,String> colName;
    public TableColumn<StudentTm,Date> colDOB;
    public TableColumn<StudentTm,Button> colOption;
    public TableColumn<StudentTm,String> colAddress;

    public void initialize(){
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        setStudentID();
        setTableData();

        //when row selected it makes to fill fields
        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                setData((StudentTm)newValue);
            }
        });
    }

    private void setData(StudentTm newValue) {
        txtStudentID.setText(newValue.getId());
        txtStudentName.setText(newValue.getName());
        txtStudentAddress.setText(newValue.getAddress());
        dtDOB.setValue(LocalDate.parse(newValue.getDob()));

        btnSave.setText("update");
    }

    private void setTableData() {
        ObservableList<StudentTm> studentTm = FXCollections.observableArrayList();
        for (Student st:Database.studentTable){
            Button btn = new Button("Delete");
            st.getDOB();
            StudentTm tm = new StudentTm(
                    st.getId(),
                    st.getName(),
                    st.getStudentAddress(),
                    new SimpleDateFormat("yyyy-MM-dd").format(st.getDOB()),
                    btn
            );
            btn.setOnAction((ActionEvent event) -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Are you sure you want to delete "+st.getId()+" student?",ButtonType.YES,ButtonType.NO);
                alert.showAndWait();

                if(alert.getResult()==ButtonType.YES){
                    Database.studentTable.remove(st);
                    new Alert(Alert.AlertType.INFORMATION,st.getId() +" Student deleted Successfully").show();
                    setStudentID();
                    setTableData();
                }
            });
            studentTm.add(tm);
        }
        tblStudent.setItems(studentTm);
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
        if(btnSave.getText().equals("save")){
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
            setTableData();
        }else{
            Optional <Student> selectedStudent = Database.studentTable.stream().filter(
              student -> student.getId().equals(txtStudentID.getText())).findFirst();
            if(selectedStudent.isPresent()){
                selectedStudent.get().setName(txtStudentName.getText());
                selectedStudent.get().setStudentAddress(txtStudentAddress.getText());
                selectedStudent.get().setDOB(Date.from(dtDOB.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                new Alert(Alert.AlertType.INFORMATION, "Student Updated", ButtonType.OK).show();
                setStudentID();
                clearFields();
                setTableData();
                btnSave.setText("save");
            }
        }
    }

}
