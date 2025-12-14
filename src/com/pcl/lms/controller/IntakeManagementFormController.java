package com.pcl.lms.controller;

import com.pcl.lms.DB.Database;
import com.pcl.lms.model.Intake;
import com.pcl.lms.model.Program;
import com.pcl.lms.view.tm.IntakeTm;
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
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

public class IntakeManagementFormController {
    public AnchorPane context;
    public TextField txtid;
    public Button btnSave;
    public DatePicker dteStart;
    public TextField txtName;
    public ComboBox<String> cmbProgram;
    public TextField txtSearch;
    public TableView<IntakeTm> tblIntake;
    public TableColumn<IntakeTm,String> colid;
    public TableColumn<IntakeTm,String>  colName;
    public TableColumn<IntakeTm,Date>  colDate;
    public TableColumn<IntakeTm,String>  colProgram;
    public TableColumn<IntakeTm,Button>  colOption;
    private String SearchText="";

    public void initialize(){
        setIntakeid();
        setProgramsData();
        loadTableData(SearchText);

        colid.setCellValueFactory(new PropertyValueFactory<>("intakeid"));
        colName.setCellValueFactory(new PropertyValueFactory<>("intakeName"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            this.SearchText = newValue;
            loadTableData(SearchText);
        });

        tblIntake.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                setDataForm((IntakeTm)newValue);
            }
        });
    }

    private void setDataForm(IntakeTm tm) {
        txtid.setText(tm.getIntakeid());
        txtName.setText(tm.getIntakeName());
        dteStart.setValue(tm.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        cmbProgram.setValue(tm.getProgramName());
        btnSave.setText("Update");
    }

    private void loadTableData(String searchText) {
        ObservableList<IntakeTm> intakeObList = FXCollections.observableArrayList();
        intakeObList.clear();

        for(Intake intake:Database.intakeTable){
            if (intake.getName().toLowerCase().contains(searchText.toLowerCase())){
                Button btn = new Button("delete");
                intakeObList.add(new IntakeTm(
                      intake.getId(),
                      intake.getName(),
                      intake.getDate(),
                      intake.getProgramName(),
                      btn
                ));
                btn.setOnAction((event) -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        Database.intakeTable.remove(intake);
                        loadTableData(SearchText);
                        setIntakeid();
                    }
                });
            }
        }
        tblIntake.setItems(intakeObList);
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
        if(btnSave.getText().equals("Save")){
            Database.intakeTable.add(new Intake(
                    txtid.getText(),
                    txtName.getText(),
                    Date.from(dteStart.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    cmbProgram.getValue()
            ));
            new Alert(Alert.AlertType.INFORMATION,"New Intake Saved").show();
            setIntakeid();
            setProgramsData();
            clearFileds();
            loadTableData(SearchText);
        }
        else{
            Optional<Intake> selectedIntake = Database.intakeTable.stream().filter(e -> e.getId().equals(txtid.getText())).findFirst();
            if (selectedIntake.isPresent()){
                selectedIntake.get().setName(txtName.getText());
                selectedIntake.get().setDate(Date.from(dteStart.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                selectedIntake.get().setProgramName(cmbProgram.getValue());

                new Alert(Alert.AlertType.INFORMATION,selectedIntake.get().getId() + " Intake Updated").show();
                clearFileds();
                btnSave.setText("Save");
                loadTableData(SearchText);
                setIntakeid();
            }
        }
    }

    private void clearFileds() {
        txtName.clear();
        dteStart.setValue(null);
    }

    public void newIntakeOnAction(ActionEvent actionEvent) {
        clearFileds();
        setIntakeid();
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
