package com.example.researchcentersystem.Backend;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class dashboeardAdminController implements Initializable{

    @FXML
    private Button addMachine_DeleteBtn;

    @FXML
    private Button addMachine_UpdateBtn;

    @FXML
    private Button addMachine_addBtn;

    @FXML
    private Button addMachine_clearBtn;

    @FXML
    private TableColumn<?, ?> addMachine_col_MachineName;

    @FXML
    private TableColumn<?, ?> addMachine_col_machineID;

    @FXML
    private TextField addMachine_machineID;

    @FXML
    private TextField addMachine_machineName;

    @FXML
    private TextField addMachine_researchInterest;

    @FXML
    private TableView<?> addMachine_table;

    @FXML
    private Button addMember_addBtn;

    @FXML
    private Button addMember_clearBtn;

    @FXML
    private TableColumn<?, ?> addMember_col_memberEmail;

    @FXML
    private TableColumn<?, ?> addMember_col_memberID;

    @FXML
    private TableColumn<?, ?> addMember_col_memberName;

    @FXML
    private TableColumn<?, ?> addMember_col_researchInterest;

    @FXML
    private Button addMember_deleteBtn;

    @FXML
    private TextField addMember_memberEmail;

    @FXML
    private TextField addMember_memberID;

    @FXML
    private TextField addMember_memberName;

    @FXML
    private TextField addMember_researchInterest;

    @FXML
    private TableView<?> addMember_table;

    @FXML
    private Button addMember_updateBtn;

    @FXML
    private Button addProject_UpdateBtn;

    @FXML
    private Button addProject_addBtn;

    @FXML
    private Button addProject_clearBtn;

    @FXML
    private TableColumn<?, ?> addProject_col_projectName;

    @FXML
    private Button addProject_deleteBtn;

    @FXML
    private TextField addProject_projectName;

    @FXML
    private TableView<?> addProject_table;

    @FXML
    private AnchorPane home_form;

    @FXML
    private AnchorPane machine_form;

    @FXML
    private AnchorPane member_form;

    @FXML
    private AnchorPane project_form;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
