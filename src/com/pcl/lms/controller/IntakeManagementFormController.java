package com.pcl.lms.controller;

import com.pcl.lms.DB.Database;
import com.pcl.lms.model.Intake;
import com.pcl.lms.model.Program;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class IntakeManagementFormController {
    public AnchorPane context;
    public TextField txtid;
    public Button btnSave;
    public DatePicker dteStart;
    public TextField txtName;
    public ComboBox cmbProgram;
    public TextField txtSearch;
    public TableView tblIntake;
    public TableColumn colid;
    public TableColumn colName;
    public TableColumn colDate;
    public TableColumn colProgram;
    public TableColumn colOption;

    public void initialize(){
        setIntakeid();
        setProgramsData();
    }

    private void setProgramsData() {
        ObservableList<String> programsOblist = FXCollections.observableArrayList();
        for(Program temp:Database.programTable){
            programsOblist.add(
                 temp.getProgramid()+" "+temp.getProgramname()
            );
        }
        cmbProgram.setItems(programsOblist);
    }

    private void setIntakeid() {
        if (Database.intakeTable.isEmpty()){
            txtid.setText("I-1");
        }else{        Intake lastIntake = Database.intakeTable.get(Database.intakeTable.size()-1);
            String lastid = lastIntake.getId();
            String[] splittedid = lastid.split("-");
            int id = Integer.parseInt(splittedid[1]);
            id++;
            txtid.setText("I -"+id);}
    }

    public void saveOnAction(ActionEvent actionEvent) {
    }

    public void newIntakeOnAction(ActionEvent actionEvent) {
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
