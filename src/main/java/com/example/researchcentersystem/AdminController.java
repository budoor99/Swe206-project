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
    private TableView<Machine> addMachine_table;

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
    private TableColumn<Project,String> addProject_col_projectName;
    @FXML
    private TableColumn<Project,String> addProject_col_team;

    @FXML
    private Button addProject_deleteBtn;

    @FXML
    private TextField addProject_projectName;

    @FXML
    private TableView<Project> addProject_table;

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

    @FXML
    private Label home_numOfProject;

    @FXML
    private ComboBox<Team> addProject_selectTeam;

    @FXML
    private TextField addProject_Team;

    @FXML
    private Button addProject_AssignBtn;

    @FXML
    private ListView<String> addMachine_listView;





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
            //homeTheMostActiveMember();


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
            addProjectShowListData();
            addTeams();


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

            addMachineShowListData();

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

    //*************************ALL THESE FUNCTIONS TO ADD NEW MEMBER FORM*************************//
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
    public void addMemberClear() {
        addMember_memberID.setText(""); // Assuming memberID is the actual member ID
        addMember_memberName.setText("");
        addMember_memberEmail.setText("");
        addMember_researchInterest.getSelectionModel().clearSelection();
    }


    //*************************ALL THESE FUNCTIONS TO ADD NEW Project FORM*************************//

    ObservableList<Project> addProjectsList;
    public void addProjectShowListData(){ //big problem!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        addProjectsList= convertProjectsListToObservable(database.getAllProjects());
//        addMember_col_memberName.setCellValueFactory(new PropertyValueFactory<>("userName"));
//        addMember_col_memberEmail.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
//        addMember_col_researchInterest.setCellValueFactory(new PropertyValueFactory<>("researchInterest"));
        addProject_col_projectName.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        addProject_col_team.setCellValueFactory(new PropertyValueFactory<>("team"));
        addProject_table.setItems(addProjectsList);
        System.out.println("here");


    }
    private ObservableList<Project> convertProjectsListToObservable(ArrayList<Project> projectList) {
        ObservableList<Project> projects = FXCollections.observableArrayList();
        for (Project p : projectList) {
            projects.add(p);
        }

        return projects;
    }

    private Project selectedProject;

    public void addProjectSelect(){
        selectedProject=addProject_table.getSelectionModel().getSelectedItem();
        int num=addProject_table.getSelectionModel().getSelectedIndex();

        if((num-1)<-1){
            return;
        }

        addProject_projectName.setText(String.valueOf(selectedProject.getProjectName())); // Assuming memberID is the actual member ID
        Team projectTeam = selectedProject.getTeam();
        addProject_Team.setText(String.valueOf((projectTeam != null) ? projectTeam.getTeamName() :""));
    }


    public void addTeams() {
        ObservableList<Team> RI=FXCollections.observableArrayList(database.getAllTeam());
        addProject_selectTeam.setItems(RI);
    }

    public void  assignBtn(){
        if(addProject_projectName.getText().isEmpty() || addProject_selectTeam.getSelectionModel().getSelectedItem() == null){
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("please fill all the fields");
            alert.showAndWait();

        }else {
            String team = addProject_Team.getText();
            String project = addProject_projectName.getText();
            Team assignedTeam = addProject_selectTeam.getSelectionModel().getSelectedItem();
            if(addProject_Team.getText().isEmpty()){
                selectedProject.assignTeamProject(assignedTeam);

                addProjectShowListData();
                Alert alert;
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText(project+ " has been successfully assigned to "+ assignedTeam.toString());
                alert.showAndWait();
                addProjectClear();

            }
            else {
                Alert alert;
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("the project is already assigned to "+team);
                alert.showAndWait();

            }

        }
    }
    public void addProjectClear() {
        addProject_projectName.setText(""); // Assuming memberID is the actual member ID
        addProject_Team.setText("");
        addProject_selectTeam.getSelectionModel().clearSelection();
        selectedProject=null;
    }
    public void addProjectDelete(){
        Alert alert;
        if(addProject_projectName.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("please select a project to be deleted");
            alert.showAndWait();
        }
        else {
            String name=addProject_projectName.getText();
            String team=addProject_Team.getText();
            Project toBeRemoved=database.searchProject(name);
            if(toBeRemoved!=null){

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Cofirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE  "+name);
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    database.removeProject(name);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully deleted!");
                    alert.showAndWait();
                    addProjectShowListData();
                    addProjectClear();
                }
            }
            else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("The project is not exist!");
                alert.showAndWait();

            }
        }
    }

    //*************************ALL THESE FUNCTIONS TO ADD NEW MACHINE FORM*************************//
    ObservableList<Machine> addMachineList;

    @FXML
    private TableColumn<Machine,String> addMachine_col_researchInterests;
    public void addMachineShowListData(){
        addMachineList= convertMachinesListToObservable(database.getMachines());
        addMachine_col_machineID.setCellValueFactory(new PropertyValueFactory<>("machineID"));
        addMachine_col_MachineName.setCellValueFactory(new PropertyValueFactory<>("machineName"));
        addMachine_col_researchInterests.setCellValueFactory(new PropertyValueFactory<>("researchInterests"));
        addMachine_table.setItems(addMachineList);
        AddMachineFillResearchInterests();

    }
    public void addMachineSelect(){
        Machine machine=addMachine_table.getSelectionModel().getSelectedItem();
        int num=addMachine_table.getSelectionModel().getSelectedIndex();

        if((num-1)<-1){
            return;
        }

        addMachine_machineID.setText(String.valueOf(machine.getMachineID())); // Assuming memberID is the actual member ID
        addMachine_machineName.setText(String.valueOf(machine.getMachineName()));


    }

    private ObservableList<Machine> convertMachinesListToObservable(ArrayList<Machine> machines) {
        ObservableList<Machine> m = FXCollections.observableArrayList();
        for (Machine machine : machines) {
            m.add(machine);
        }

        return m;
    }

    public void addMachineAdd(){
        ObservableList<String> selectedItems = addMachine_listView.getSelectionModel().getSelectedItems();
        Alert alert;
        if(addMachine_machineID.getText().isEmpty() ||
                addMachine_machineName.getText().isEmpty() ||
                selectedItems.isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("please fill all the fields");
            alert.showAndWait();
        }
        else {
            String name=addMachine_machineName.getText(); String ID=addMachine_machineID.getText();
            Machine machine=database.searchMachine(name);
            if(machine==null){
                database.addMachine(name,ID,new ArrayList<>(selectedItems));

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                alert.showAndWait();
                addMachineShowListData();
                addMachineClear();

            }
            else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("the machine is already exist");
                alert.showAndWait();

            }
        }
    }

    public void AddMachineFillResearchInterests(){


        ObservableList<String> interests = FXCollections.observableArrayList(database.getResearchInterest());;

        addMachine_listView.getItems().addAll(interests);
        addMachine_listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }


    public void addMachineDelete(){
        Alert alert;
        if(addMachine_machineID.getText().isEmpty() ||
                addMachine_machineName.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("please fill all the machine id and name");
            alert.showAndWait();
        }
        else {
            String name=addMachine_machineName.getText(); String ID=addMachine_machineID.getText();
            Machine machine=database.searchMachine(name);
            if(machine!=null){

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Cofirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE "+name+" with ID: " + ID + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    database.removeMachine(name);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully deleted!");
                    alert.showAndWait();
                    addMachineShowListData();
                    addMachineClear();
                }
            }
            else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("The machine is not exist!");
                alert.showAndWait();

            }
        }
    }
    public void addMachineClear() {
        addMachine_machineID.setText(""); // Assuming memberID is the actual member ID
        addMachine_machineName.setText("");
        addMachine_listView.getSelectionModel().clearSelection();
    }





// this can NOT be used until we assign team to project.
//    public void homeTheMostActiveMember(){
//        Member m=database.viewMostActiveMember();
//        home_theMostActiveMember.setText(String.valueOf(m.getUserName()));
//        home_numOfProject.setText(String.valueOf(m.viewAssignedProject().size()));
//    }









    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        addMemberShowListData();
        addMemberResearchInterests();
        //homeTheMostActiveMember();

        String adminName = MemorySession.currentUser.getUserName();
        username.setText(adminName);

        addMemberShowListData();
        addMemberResearchInterests();

        AddMachineFillResearchInterests();



    }
}
