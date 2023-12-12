package com.example.researchcentersystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;

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
    private TableColumn<Member, String> addMember_col_memberEmail;

    @FXML
    private TableColumn<Member, String> addMember_col_memberID;

    @FXML
    private TableColumn<Member, String> addMember_col_memberName;

    @FXML
    private TableColumn<Member, String> addMember_col_researchInterest;

    @FXML
    private Button addMember_deleteBtn;

    @FXML
    private TextField addMember_memberEmail;

    @FXML
    private TextField addMember_memberID;

    @FXML
    private TextField addMember_memberName;

    @FXML
    private ComboBox<String> addMember_researchInterest;

    @FXML
    private TableView<Member> addMember_table;

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
    @FXML
    private Button assignTeamToProject_btn;
    @FXML
    private AnchorPane teams_form;

    @FXML
    private AnchorPane machines_form;

    @FXML
    private AnchorPane assignTeam_form;

    @FXML
    private Label home_theMostActiveMember;
    private ObservableList<Member> addMemberList;

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
    public void switchForm(ActionEvent event) {

        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            machine_form.setVisible(false);
            member_form.setVisible(false);
            project_form.setVisible(false);
            teams_form.setVisible(false);
            machines_form.setVisible(false);
            assignTeam_form.setVisible(false);


            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            addMachine_btn.setStyle("-fx-background-color:transparent");
            addMember_btn.setStyle("-fx-background-color:transparent");
            addProject_btn.setStyle("-fx-background-color:transparent");
            viewTeams_btn.setStyle("-fx-background-color:transparent");
            viewMachines_btn.setStyle("-fx-background-color:transparent");
            assignTeamToProject_btn.setStyle("-fx-background-color:transparent");

            addMemberShowListData();
            addMemberClear();


        } else if (event.getSource() == addProject_btn) {
            home_form.setVisible(false);
            machine_form.setVisible(false);
            member_form.setVisible(false);
            project_form.setVisible(true);
            teams_form.setVisible(false);
            machines_form.setVisible(false);
            assignTeam_form.setVisible(false);


            addProject_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            addMachine_btn.setStyle("-fx-background-color:transparent");
            addMember_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            viewTeams_btn.setStyle("-fx-background-color:transparent");
            viewMachines_btn.setStyle("-fx-background-color:transparent");
            assignTeamToProject_btn.setStyle("-fx-background-color:transparent");


        } else if (event.getSource() == addMachine_btn) {
            home_form.setVisible(false);
            machine_form.setVisible(true);
            member_form.setVisible(false);
            project_form.setVisible(false);
            teams_form.setVisible(false);
            machines_form.setVisible(false);
            assignTeam_form.setVisible(false);


            addMachine_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            addProject_btn.setStyle("-fx-background-color:transparent");
            addMember_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            viewTeams_btn.setStyle("-fx-background-color:transparent");
            viewMachines_btn.setStyle("-fx-background-color:transparent");
            assignTeamToProject_btn.setStyle("-fx-background-color:transparent");

//            salaryShowListData();

        } else if (event.getSource() ==addMember_btn) {
            home_form.setVisible(false);
            machine_form.setVisible(false);
            member_form.setVisible(true);
            project_form.setVisible(false);
            teams_form.setVisible(false);
            machines_form.setVisible(false);
            assignTeam_form.setVisible(false);


            addMember_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            addProject_btn.setStyle("-fx-background-color:transparent");
            addMachine_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            viewTeams_btn.setStyle("-fx-background-color:transparent");
            viewMachines_btn.setStyle("-fx-background-color:transparent");
            assignTeamToProject_btn.setStyle("-fx-background-color:transparent");
            
        } else if (event.getSource() ==viewTeams_btn) {
            home_form.setVisible(false);
            machine_form.setVisible(false);
            member_form.setVisible(false);
            project_form.setVisible(false);
            teams_form.setVisible(true);
            machines_form.setVisible(false);
            assignTeam_form.setVisible(false);


            viewTeams_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            addProject_btn.setStyle("-fx-background-color:transparent");
            addMember_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            addMachine_btn.setStyle("-fx-background-color:transparent");
            viewMachines_btn.setStyle("-fx-background-color:transparent");
            assignTeamToProject_btn.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() ==viewMachines_btn) {
            home_form.setVisible(false);
            machine_form.setVisible(false);
            member_form.setVisible(false);
            project_form.setVisible(false);
            teams_form.setVisible(false);
            machines_form.setVisible(true);
            assignTeam_form.setVisible(false);


            viewMachines_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            addProject_btn.setStyle("-fx-background-color:transparent");
            addMember_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            viewTeams_btn.setStyle("-fx-background-color:transparent");
            addMachine_btn.setStyle("-fx-background-color:transparent");
            assignTeamToProject_btn.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() ==assignTeamToProject_btn) {
            home_form.setVisible(false);
            machine_form.setVisible(false);
            member_form.setVisible(false);
            project_form.setVisible(false);
            teams_form.setVisible(false);
            machines_form.setVisible(false);
            assignTeam_form.setVisible(true);


            assignTeamToProject_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            addProject_btn.setStyle("-fx-background-color:transparent");
            addMember_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            viewTeams_btn.setStyle("-fx-background-color:transparent");
            viewMachines_btn.setStyle("-fx-background-color:transparent");
            addMachine_btn.setStyle("-fx-background-color:transparent");

        }

    }
    public void addMemberShowListData(){
        addMemberList= convertMembersListToObservable(database.getAllMembers());
//        addMember_col_memberID.setCellValueFactory(new PropertyValueFactory<>("UserID"));
        addMember_col_memberID.setCellValueFactory(new PropertyValueFactory<>("UserID"));
        addMember_col_memberName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        addMember_col_memberEmail.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
        addMember_col_researchInterest.setCellValueFactory(new PropertyValueFactory<>("researchInterest"));
        addMember_table.setItems(addMemberList);

    }
    public void addMemberSelect(){
        Member member=addMember_table.getSelectionModel().getSelectedItem();
        int num=addMember_table.getSelectionModel().getSelectedIndex();

        if((num-1)<-1){
            return;
        }

        addMember_memberID.setText(String.valueOf(member.getUserID())); // Assuming memberID is the actual member ID
        addMember_memberName.setText(String.valueOf(member.getUserName()));
        addMember_memberEmail.setText(String.valueOf(member.getUserEmail()));

    }

    public void addMemberResearchInterests() {
        ArrayList<String> RI=database.getResearchInterest();

//        for (String R: RI) {
//            researchInterests.add(R);
//        }

        ObservableList<String> researchInterests = FXCollections.observableArrayList(RI);
        addMember_researchInterest.setItems(researchInterests);
    }
    private ObservableList<Member> convertMembersListToObservable(ArrayList<Member> memberList) {
        ObservableList<Member> members = FXCollections.observableArrayList();
        for (Member member : memberList) {
            members.add(member);
        }

        return members;
    }

    public void addMemberAdd(){
        Alert alert;
        if(addMember_memberID.getText().isEmpty() ||
        addMember_memberName.getText().isEmpty() ||
        addMember_memberEmail.getText().isEmpty() ||
        addMember_researchInterest.getSelectionModel().getSelectedItem() == null){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("please fill all the fields");
            alert.showAndWait();
        }
        else {
            String name=addMember_memberName.getText(); String ID=addMember_memberID.getText();
            String email=addMember_memberEmail.getText(); String interest=(String) addMember_researchInterest.getSelectionModel().getSelectedItem();
            Member member=database.searchMember(name);
            if(member==null){
                database.addMember(name,email,interest,ID);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                alert.showAndWait();
                addMemberShowListData();
                addMemberClear();

            }
            else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("the member is already exist");
                alert.showAndWait();

            }
        }
    }

    public void addMemberDelete(){
        Alert alert;
        if(addMember_memberID.getText().isEmpty() ||
                addMember_memberName.getText().isEmpty() ||
                addMember_memberEmail.getText().isEmpty() ||
                addMember_researchInterest.getSelectionModel().getSelectedItem() == null){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("please fill all the fields");
            alert.showAndWait();
        }
        else {
            String name=addMember_memberName.getText(); String ID=addMember_memberID.getText();
            String email=addMember_memberEmail.getText(); String interest=(String) addMember_researchInterest.getSelectionModel().getSelectedItem();
            Member member=database.searchMember(name);
            if(member!=null){

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Cofirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Member "+name+" with ID: " + ID + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    database.removeMember(name);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully deleted!");
                    alert.showAndWait();
                    addMemberShowListData();
                    addMemberClear();
                }
            }
            else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("The member is not exist!");
                alert.showAndWait();

            }
        }
    }

    public void homeTheMostActiveMember(){
//        Member m=database.viewMostActiveMember();
//        home_theMostActiveMember.setText(String.valueOf(countData));
    }


    public void addMemberClear() {
        addMember_memberID.setText(""); // Assuming memberID is the actual member ID
        addMember_memberName.setText("");
        addMember_memberEmail.setText("");
        addMember_researchInterest.getSelectionModel().clearSelection();
    }






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addMemberShowListData();
        addMemberResearchInterests();


        String adminName = MemorySession.currentUser.getUserName();
        username.setText(adminName);
        


    }
}
