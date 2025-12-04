package com.pcl.lms.controller;

import com.pcl.lms.DB.Database;
import com.pcl.lms.model.Program;
import com.pcl.lms.model.Teacher;
import com.pcl.lms.model.Module;
import com.pcl.lms.view.tm.ModulesTm;
import com.pcl.lms.view.tm.ProgramTm;
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
import java.util.ArrayList;

public class ProgrammeManagementFormController {
    public TextField txtProgramid;
    public TextField txtProgramName;
    public TextField txtProgramCost;
    public ComboBox<String> cbxTeacher;
    public TextField txtModules;
    public TableView<ModulesTm> tblModule;
    public TableColumn<ModulesTm,Integer> colModuleid;
    public TableColumn<ModulesTm,String> colModuleName;
    public TableColumn<ModulesTm,Button> colModuleRemove;
    public Button btnSave;
    public TableView<ProgramTm> tblProgram;
    public TableColumn<ProgramTm,String> colProgramid;
    public TableColumn<ProgramTm,String> colProgramName;
    public TableColumn<ProgramTm,String> colTeacher;
    public TableColumn<ProgramTm,Button> colModuleList;
    public TableColumn<ProgramTm,Double> colCost;
    public TableColumn<ProgramTm,Button> colOption;
    public AnchorPane context;
    static ArrayList<Module> modList = new ArrayList<>();
    static ObservableList<ModulesTm> list = FXCollections.observableArrayList();

    public void initialize() {
        colModuleid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colModuleName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colModuleRemove.setCellValueFactory(new PropertyValueFactory<>("btn"));

        colProgramid.setCellValueFactory(new PropertyValueFactory<>("programid"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colTeacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        colModuleList.setCellValueFactory(new PropertyValueFactory<>("btnModules"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));

        tblProgram.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setData((ProgramTm)newValue);
            }
        });

        setModuleTableData();
        setProgramid();
        setTeacher();
        loadProgramData();

    }

    private void setData(ProgramTm newValue) {
        btnSave.setText("Update");

    }

    private void loadProgramData() {
        ObservableList<ProgramTm> programOblist = FXCollections.observableArrayList();

        for(Program temp:Database.programTable){
            Button btnModule = new Button("Modules");
            Button btnDelete = new Button("Delete");
            programOblist.add(
                    new ProgramTm(
                            temp.getProgramid(),
                            temp.getProgramname(),
                            temp.getTeacher(),
                            btnModule,
                            temp.getCost(),
                            btnDelete )

            );
            btnDelete.setOnAction(actionEvent -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure",ButtonType.YES,ButtonType.NO);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    Database.programTable.remove(temp);
                    loadProgramData();
                    setProgramid();
                    new Alert(Alert.AlertType.INFORMATION,"Program Deleted",ButtonType.OK).show();
                }
            });

            btnModule.setOnAction(actionEvent -> {
                Stage stage = new Stage();
                try {
                    stage.setScene(new Scene((FXMLLoader.load(getClass().getResource("/com/pcl/lms/view/ModulePopup.fxml")))));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.setTitle("Module List");
                stage.show();
            });
        }
        tblProgram.setItems(programOblist);
    }

    private void setTeacher() {
        ObservableList<String> list = FXCollections.observableArrayList();
        for(Teacher t : Database.teacherTable){
            list.add(t.getId().trim()+"-"+t.getName().trim());
        }
        cbxTeacher.setItems(list);
    }

    private void setProgramid() {
        if(Database.programTable.isEmpty()) {
            txtProgramid.setText("P-1");
        }else{
            Program lastProgram = Database.programTable.get(Database.programTable.size()-1);
            String lastProgramid = lastProgram.getProgramid();
            String[] splittedlastid = lastProgramid.split("-");
            String lastString = splittedlastid[1];
            int lastDigit = Integer.parseInt(lastString);
            lastDigit++;
            String genearatedid = "P-" + lastDigit;
            txtProgramid.setText(genearatedid);
        }
    }

    public void newProgramOnAction(ActionEvent actionEvent) {
    }

    public void saveOnAction(ActionEvent actionEvent) {
        String [] selectedmodules = new String[modList.size()];
        int pointer = 0;
        for(Module mod:modList){
            selectedmodules[pointer] = mod.getName();
            pointer++;
        }

        if(btnSave.getText().equals("Save")){
            Database.programTable.add(new Program(
                txtProgramid.getText(),
                    txtProgramName.getText(),
                    Double.parseDouble(txtProgramCost.getText()),
                    cbxTeacher.getValue(),
                    selectedmodules
            ));
            setProgramid();
            clearFields();
            setModuleTableData();
            loadProgramData();
            new Alert(Alert.AlertType.INFORMATION, "Program Saved", ButtonType.OK).show();
        }
    }

    private void clearFields() {
        txtProgramCost.clear();
        txtProgramName.clear();
        cbxTeacher.setPromptText("Teacher");
        modList.clear();
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

    public void addModulesOnAction(ActionEvent actionEvent) {
        if(txtModules.getText().isEmpty()){
            return;
        }else {
            modList.add(new Module(getModuleid(),txtModules.getText()));

            setModuleTableData();
            txtModules.setText("");
        }
    }

    private void setModuleTableData() {
        for(Module module : modList){
            Button btn = new Button("Delete");
            list.add(new ModulesTm(
                    module.getId(),
                    module.getName(),
                    btn
            ));
            btn.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure?",ButtonType.YES,ButtonType.NO);
                alert.showAndWait();
                if(alert.getResult() == ButtonType.YES){
                    modList.remove(module);
                    setModuleTableData();
                }
            });
        }
        tblModule.setItems(list);
    }

    private int getModuleid() {
        boolean modListEmpty = modList.isEmpty();
        if(modListEmpty){
            return 1;
        }
        Module lastModule = modList.get(modList.size() -1);
        return lastModule.getId() + 1;
    }
}
