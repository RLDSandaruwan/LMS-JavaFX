package com.pcl.lms.controller;

import com.pcl.lms.model.Module;
import com.pcl.lms.view.tm.ModulesTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class ModulePopupController {
    public AnchorPane context;
    public ListView<String> lxtModule;
    ObservableList<String> moduleObList = FXCollections.observableArrayList();

    public void initialize(){
        setModuleList();
    }

    private void setModuleList() {
        for(ModulesTm tempMod : ProgrammeManagementFormController.list){
            moduleObList.add(tempMod.getId()+ " : " + tempMod.getName());
        }
        lxtModule.setItems(moduleObList);
    }
}
