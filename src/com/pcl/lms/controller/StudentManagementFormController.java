package com.pcl.lms.controller;

import com.pcl.lms.DB.Database;
import com.pcl.lms.model.Student;
import com.pcl.lms.view.tm.StudentTm;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

public class StudentManagementFormController {
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
    public AnchorPane context;
    String searchText = "";

    public void initialize(){
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        setStudentID();
        setTableData(searchText);

        //when row selected it makes to fill fields
        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                setData((StudentTm)newValue);
            }
        });

        //search text
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            this.searchText = newValue;
            setTableData(searchText);
        });
    }

    private void setData(StudentTm newValue) {
        txtStudentID.setText(newValue.getId());
        txtStudentName.setText(newValue.getName());
        txtStudentAddress.setText(newValue.getAddress());
        dtDOB.setValue(LocalDate.parse(newValue.getDob()));

        btnSave.setText("update");
    }

    //fill the table
    private void setTableData(String searchText) {
        ObservableList<StudentTm> studentTm = FXCollections.observableArrayList();
        for (Student st:Database.studentTable){
            if (st.getName().toLowerCase().contains(searchText.toLowerCase())){
                Button btn = new Button("Delete");
                st.getDOB();
                StudentTm tm = new StudentTm(
                        st.getId(),
                        st.getName(),
                        st.getStudentAddress(),
                        new SimpleDateFormat("yyyy-MM-dd").format(st.getDOB()),
                        btn
                );

                //delete button
                btn.setOnAction((ActionEvent event) -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION,"Are you sure you want to delete "+st.getId()+" student?",ButtonType.YES,ButtonType.NO);
                    alert.showAndWait();

                    if(alert.getResult()==ButtonType.YES){
                        Database.studentTable.remove(st);
                        new Alert(Alert.AlertType.INFORMATION,st.getId() +" Student deleted Successfully").show();
                        setStudentID();
                        setTableData(searchText);
                    }
                });
                studentTm.add(tm);
            }
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
        if(btnSave.getText().equals("Save")){
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
            setTableData(searchText);
            
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
                setTableData(searchText);
                btnSave.setText("Save");

            }
        }
    }

    public void newStudentOnAction(ActionEvent actionEvent) {
        clearFields();
        setStudentID();
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
