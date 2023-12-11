package com.example.researchcentersystem;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    private MemorySession database=new MemorySession();

    @FXML
    private Button addMachine_DeleteBtn;

    @FXML
    private Button addMachine_UpdateBtn;

    @FXML
    private Button addMachine_addBtn;

    @FXML
    private Button addMachine_btn;

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
    private Button addMember_btn;

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
    private Button addProject_btn;

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
    private Button close;

    @FXML
    private Button home_btn;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Button logout;


    @FXML
    private AnchorPane machine_form;

    @FXML
    private AnchorPane member_form;

    @FXML
    private Button minimize;

    @FXML
    private AnchorPane project_form;

    @FXML
    private Label username;

    @FXML
    private Button viewMachines_btn;

    @FXML
    private Button viewTeams_btn;

    private double x=0;
    private double y=0;

    public void logout(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional< ButtonType> option = alert.showAndWait();

        try {
            if (option.get().equals(ButtonType.OK))
            {

                logout.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) ->{
                    x=event.getSceneX();
                    y=event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) ->{
                    stage.setX(event.getScreenX()-x);
                    stage.setY(event.getScreenX()-y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event)->{
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);


                stage.setScene(scene);
                stage.show();
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void close(){
        System.exit(0);
    }

    public void minimize(){
        Stage stage = (Stage)home_form.getScene().getWindow();
        stage.setIconified(true);
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        

    }
}
