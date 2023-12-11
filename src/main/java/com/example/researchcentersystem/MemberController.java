package com.example.researchcentersystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MemberController implements Initializable {
    private MemorySession database=new MemorySession();

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
    private AnchorPane viewOneTeam_form;





    public void switchForm(ActionEvent event) {

        if (event.getSource() == home) {
            home_form.setVisible(true);
            viewteams_form.setVisible(false);


            home.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c)");
            viewTeams.setStyle("-fx-background-color:transparent");

        }
        else if(event.getSource() == viewTeams){
            home_form.setVisible(false);
            viewteams_form.setVisible(true);


            home.setStyle("-fx-background-color:transparent");
            viewTeams.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c)");

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
            teamButton.setOnAction(event -> handleTeamButtonClick(team)); // Add event handler
            myteams.getChildren().add(teamButton);
        }
    }

    private void handleTeamButtonClick(Team team) {
        selectedTeam = team;
        moveForwardToTeam();
    }

    public void moveBackToTeams(ActionEvent event) {
        if (event.getSource() == closViewTeam_btn) {
            viewteams_form.setVisible(true);
            viewOneTeam_form.setVisible(false);
            home_form.setVisible(false);


        }
    }

    public void moveForwardToTeam() {
        viewteams_form.setVisible(false);
        viewOneTeam_form.setVisible(true);
        home_form.setVisible(false);
    }
}
