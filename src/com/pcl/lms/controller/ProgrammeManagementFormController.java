package com.pcl.lms.controller;

import com.pcl.lms.DB.Database;
import com.pcl.lms.model.Program;
import com.pcl.lms.model.Teacher;
import com.pcl.lms.model.Module;
import com.pcl.lms.view.tm.ModulesTm;
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
    public TableView tblProgram;
    public TableColumn colProgramid;
    public TableColumn colProgramName;
    public TableColumn colTeacher;
    public TableColumn colModuleList;
    public TableColumn colCost;
    public TableColumn colOption;
    public AnchorPane context;
    static ArrayList<Module> modList = new ArrayList<>();

    public void initialize() {
        colModuleid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colModuleName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colModuleRemove.setCellValueFactory(new PropertyValueFactory<>("btn"));
        setModuleTableData();
        setProgramid();
        setTeacher();
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
            new Alert(Alert.AlertType.INFORMATION, "Program Saved", ButtonType.OK).show();
        }
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
        ObservableList<ModulesTm> list = FXCollections.observableArrayList();
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
