package com.example.researchcentersystem;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.*;

public class MemberController implements Initializable {
    private MemorySession database=new MemorySession();

    @FXML
    private TableColumn<Member, String> col_memberEmail;

    @FXML
    private TableColumn<Member, String> col_memberID;

    @FXML
    private TableColumn<Member, String> col_memberName;

    @FXML
    private TableColumn<Member, String> col_researchInterest;

    @FXML
    private TableView<Member> Member_table;

    @FXML
    private Button close;

    @FXML
    private Button home;

    @FXML
    private Button logout;

    @FXML
    private Label memberName;

    @FXML
    private AnchorPane home_form;


    @FXML
    private Button minimize;

    @FXML
    private Button viewTeams;

    @FXML
    private AnchorPane main_form;

    @FXML
    private VBox myteams;


    @FXML
    private AnchorPane viewteams_form;

    @FXML
    private Label totalTeams;

    @FXML
    private Button closViewTeam_btn;


    @FXML
    private Button reserveMachine_btn;

    @FXML
    private Button reserve_btn; //this one

    @FXML
    private AnchorPane reserve_form;


    @FXML
    private AnchorPane viewOneTeam_form;

    //private ObservableList<Member> MemberList;

    @FXML
    private Label assignedProjext_label;


    @FXML
    private TableColumn<Map.Entry<String, List<String>>, String> machines_col;

    @FXML
    private TableColumn<Map.Entry<String, List<String>>, String> reservations_col;


    @FXML
    private TableView<Map.Entry<String, List<String>>> reserved_table;








    public void switchForm(ActionEvent event) {

        if (event.getSource() == home) {
            home_form.setVisible(true);
            viewteams_form.setVisible(false);
            reserve_form.setVisible(false);


            home.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c)");
            viewTeams.setStyle("-fx-background-color:transparent");
            reserve_btn.setStyle("-fx-background-color:transparent");


        }
        else if(event.getSource() == viewTeams){
            home_form.setVisible(false);
            viewteams_form.setVisible(true);
            reserve_form.setVisible(false);


            home.setStyle("-fx-background-color:transparent");
            viewTeams.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c)");
            reserve_btn.setStyle("-fx-background-color:transparent");

        }

        else if(event.getSource() == reserve_btn){
            home_form.setVisible(false);
            viewteams_form.setVisible(false);
            reserve_form.setVisible(true);


            home.setStyle("-fx-background-color:transparent");
            viewTeams.setStyle("-fx-background-color:transparent");
            reserve_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c)");

        }
    }






    private double x=0;
    private double y=0;

    public void logout(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional< ButtonType> option = alert.showAndWait();
        database.writeToFiles();
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
        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String username = MemorySession.currentUser.getUserName();
        memberName.setText(username);
        addMyTeams();
        totalTeams();



    }

    public void totalTeams(){

        totalTeams.setText(String.valueOf(teams.size()));

    }



    ArrayList<Team> teams;
    private Team selectedTeam;  //store the selected team

    public void addMyTeams() {
        Member member = (Member) MemorySession.currentUser;
        teams = member.viewTeams();

        myteams.getChildren().clear();

        for (Team team : teams) {
            Button teamButton = new Button(team.getTeamName());
            teamButton.setMaxWidth(Double.MAX_VALUE); // Allow the button to grow horizontally
            HBox.setHgrow(teamButton, Priority.ALWAYS);
            teamButton.setOnAction(event -> handleTeamButtonClick(team)); // Add event handler
            myteams.getChildren().add(teamButton);
        }
    }

    private void handleTeamButtonClick(Team team) {
        selectedTeam = team;
        // debug
        System.out.println("Selected Team: " + selectedTeam.getTeamName());
        MemberShowListData(selectedTeam);
        //showTeamReservations(selectedTeam);

        //moveForwardToTeam();


        String assignedProjectName = getAssignedProject(selectedTeam);
        assignedProjext_label.setText(assignedProjectName);

        showMachinesAndInfo(selectedTeam);


    }

    public void moveBackToTeams(ActionEvent event) {
        if (event.getSource() == closViewTeam_btn) {
            viewteams_form.setVisible(true);
            viewOneTeam_form.setVisible(false);
            home_form.setVisible(false);


        }
    }

   /* public void moveForwardToTeam() {
        viewteams_form.setVisible(false);
        viewOneTeam_form.setVisible(true);
        home_form.setVisible(false);

        MemberShowListData(selectedTeam);
    }
*/

    private ObservableList<Member> convertMembersListToObservable(ArrayList<Member> memberList) {
        ObservableList<Member> members = FXCollections.observableArrayList();
        for (Member member : memberList) {
            members.add(member);
        }

        return members;
    }

    public void MemberShowListData(Team team) {
        /*MemberList= convertMembersListToObservable(selectedTeam.getMembers());
        col_memberID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        col_memberName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        col_memberEmail.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
        col_researchInterest.setCellValueFactory(new PropertyValueFactory<>("researchInterest"));
        Member_table.setItems(MemberList);*/


            ObservableList<Member> memberList = convertMembersListToObservable(team.getMembers());

            //debug
            System.out.println("Number of members: " + memberList.size());


            col_memberName.setCellValueFactory(new PropertyValueFactory<>("userName"));
            col_memberEmail.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
            Member_table.setItems(memberList);


    }

    ///////
    public String getAssignedProject(Team selectedTeam){
        if (selectedTeam != null) {
            for (Project project : database.getTakenProjects()) {
                if (project.getTeam() != null && project.getTeam().equals(selectedTeam)) {
                    return project.getProjectName();
                }
            }
        }
        return "No assigned project";
    }



    @FXML
    private Label date_label;


    @FXML
    private Label machines_label;

    @FXML
    private Label time_label;

    //////
    //private HashMap<String, ArrayList <String>> mahinesInfo;

    public void showMachinesAndInfo(Team selectedTeam) {
        HashMap<String, ArrayList<String>> machinesInfo = selectedTeam.getTeamReservations();

        // debug
        System.out.println(machinesInfo);
        if (selectedTeam != null && machinesInfo != null) {
            StringBuilder machinesStringBuilder = new StringBuilder();
            StringBuilder datesStringBuilder = new StringBuilder();
            StringBuilder timeRangesStringBuilder = new StringBuilder();

            for (Map.Entry<String, ArrayList<String>> entry : machinesInfo.entrySet()) {
                String machine = entry.getKey();
                ArrayList<String> reservations = entry.getValue();

                machinesStringBuilder.append(machine).append("\n");

                for (String reservation : reservations) {
                    String[] reservationParts = reservation.split(",");
                    String date = reservationParts[0].trim();
                    String timeRange = reservationParts[1].trim();

                    datesStringBuilder.append(date).append("\n");
                    timeRangesStringBuilder.append(timeRange).append("\n");
                }
            }

            // Set the text for labels
            machines_label.setText(machinesStringBuilder.toString());
            date_label.setText(datesStringBuilder.toString());
            time_label.setText(timeRangesStringBuilder.toString());
        }
    }


}
