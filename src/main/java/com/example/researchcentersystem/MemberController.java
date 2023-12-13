package com.example.researchcentersystem;

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

    @FXML
    private Label date_label;


    @FXML
    private Label machines_label;

    @FXML
    private Label time_label;


    @FXML
    private VBox vVbox;

    @FXML
    private Label assignedProjext_label;


    @FXML
    private TableColumn<Map.Entry<String, List<String>>, String> machines_col;

    @FXML
    private TableColumn<Map.Entry<String, List<String>>, String> reservations_col;


    @FXML
    private TableView<Map.Entry<String, List<String>>> reserved_table;


    @FXML
    private DatePicker date_picker;


    @FXML
    private TableColumn<Reservation, String> machine_col;

    @FXML
    private TableColumn<Reservation, String> teams_col;


    @FXML
    private TableColumn<Reservation, String> time_col;


    @FXML
    private TableColumn<Reservation, String> date_col;

    @FXML
    private ComboBox<String> time_combo;

    @FXML
    private ComboBox<String> teams_combo;

    @FXML
    private ComboBox<String> machine_combo;

    @FXML
    private TableView<Reservation> reserve_table;

    @FXML
    private Button reserve_nbtn;










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


        machineComboList();
        machine_combo.setOnAction(event -> handleMachineSelection());

        teamsComboList();


        timeComboList();



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
            teamButton.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(teamButton, Priority.ALWAYS);
            teamButton.setOnAction(event -> handleTeamButtonClick(team));
            myteams.getChildren().add(teamButton);
        }
    }

    private void handleTeamButtonClick(Team team) {
        selectedTeam = team;

        MemberShowListData(selectedTeam);



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


    private ObservableList<Member> convertMembersListToObservable(ArrayList<Member> memberList) {
        ObservableList<Member> members = FXCollections.observableArrayList();
        for (Member member : memberList) {
            members.add(member);
        }

        return members;
    }

    public void MemberShowListData(Team team) {

        ObservableList<Member> memberList = convertMembersListToObservable(team.getMembers());

        col_memberName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        col_memberEmail.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
        Member_table.setItems(memberList);


    }


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




    public void showMachinesAndInfo(Team selectedTeam) {
        HashMap<String, ArrayList<String>> machinesInfo = selectedTeam.getTeamReservations();

        // Debug
        System.out.println(machinesInfo);

        if (selectedTeam != null && machinesInfo != null) {

            vVbox.getChildren().clear();

            for (Map.Entry<String, ArrayList<String>> entry : machinesInfo.entrySet()) {
                String machine = entry.getKey();
                ArrayList<String> reservations = entry.getValue();

                Label machineLabel = new Label("Machine: " + machine);
                vVbox.getChildren().add(machineLabel);

                for (String reservation : reservations) {
                    String[] reservationParts = reservation.split(",");
                    String date = reservationParts[0].trim();
                    String timeRange = reservationParts[1].trim();

                    Label dateLabel = new Label("Date: " + date);
                    Label timeRangeLabel = new Label("Time Range: " + timeRange);

                    vVbox.getChildren().addAll(dateLabel, timeRangeLabel);
                }


                vVbox.getChildren().add(new Separator());
            }
        }
    }

    //*******************************All about reserving machines*************************************************


    public void machineComboList() {
        ArrayList<Machine> ML= database.getMachines();
        ArrayList<String> names= new ArrayList<>();

        for(int i = 0; i<ML.size();i++){
            names.add(ML.get(i).getMachineName());
        }

        ObservableList<String> machines = FXCollections.observableArrayList(names);
        machine_combo.setItems(machines);
    }


    public void teamsComboList(){
        ObservableList<String> teamNames = FXCollections.observableArrayList();

        for (Team team : teams){
                teamNames.add(team.getTeamName());
        }
        teams_combo.setItems(teamNames);

    }

    public void timeComboList() {

        ArrayList<String> timeSlots = database.TIME_SLOTS;

        ObservableList<String> timeSlotList = FXCollections.observableArrayList(timeSlots);

        time_combo.setItems(timeSlotList);
    }


    public void handleMachineSelection() {
        String selectedMachine = machine_combo.getValue();

        if (selectedMachine != null) {
            ArrayList<Reservation> reservations = database.getReservations(selectedMachine);
            System.out.println(selectedMachine+"here");


            ObservableList<Reservation> reservationList = FXCollections.observableArrayList(reservations);


            machine_col.setCellValueFactory(new PropertyValueFactory<>("machineName"));
            teams_col.setCellValueFactory(new PropertyValueFactory<>("teamName"));
            time_col.setCellValueFactory(new PropertyValueFactory<>("time"));
            date_col.setCellValueFactory(new PropertyValueFactory<>("date"));


            reserve_table.setItems(reservationList);
        }
    }


    public void addMachineAdd() {
        Alert alert;


        if (machine_combo.getValue().isEmpty() ||
                teams_combo.getValue().isEmpty() ||
                date_picker.getEditor().getText().isEmpty() ||
                time_combo.getValue().isEmpty()) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        } else {

            String machineName = machine_combo.getValue();
            String teamName = teams_combo.getValue();
            String date = date_picker.getEditor().getText(); // directly get as a string
            String time = time_combo.getValue();

            Machine findMachineObject =database.searchMachine(machineName);
            boolean isTaken = findMachineObject.createReservation(date, time);
            if (isTaken) {

                Reservation newReservation = new Reservation(machineName, teamName, time,date);
                Team teamS = database.searchTeam(teamName);
                teamS.addReservation(machineName, date,time);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                alert.showAndWait();


                updateReservationsTableForMachine(machineName);
                reserveMachineClear();

            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("The machine already exists");
                alert.showAndWait();
            }
        }
    }

    public void reserveMachineClear() {
        machine_combo.getSelectionModel().clearSelection();
        teams_combo.getSelectionModel().clearSelection();
        date_picker.setValue(null);
        time_combo.getSelectionModel().clearSelection();
    }

    @FXML
    public void handleReserveButton(ActionEvent event) {
        addMachineAdd();
    }


    public void updateReservationsTableForMachine(String machineName) {

        ArrayList<Reservation> reservations = database.getReservations(machineName);

        ObservableList<Reservation> reservationList = FXCollections.observableArrayList(reservations);

        machine_col.setCellValueFactory(new PropertyValueFactory<>("machineName"));
        teams_col.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        time_col.setCellValueFactory(new PropertyValueFactory<>("time"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("date"));

        reserve_table.setItems(reservationList);
    }





}
