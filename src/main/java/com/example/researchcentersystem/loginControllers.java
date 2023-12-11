package com.example.researchcentersystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class loginControllers implements Initializable {
    @FXML
    private Button closeBtn;

    @FXML
    private Button loginBtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField password;

    @FXML
    private TextField username;
    public void close(){
        System.exit(0);
    }

    Alert alert;
    public void login(){
        MemorySession database = new MemorySession();
        String name=username.getText();
        String pass=password.getText();
        boolean found=false;

        System.out.println(name+" "+pass);
        if(name.isEmpty()|| pass.isEmpty()){
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("please fill the all blank fields");
        }
        else {
            try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/com/example/researchcentersystem/loginInfo.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");

                    if (parts.length == 4) {
                        String storedUsername = parts[0];
                        String storedPassword = parts[1];
                        String status=parts[3];
                        if (name.equals(storedUsername) && pass.equals(storedPassword)) {

                            //for members
                            BufferedReader readerMember = new BufferedReader(new FileReader("src/main/java/com/example/researchcentersystem/member.txt"));
                            String memberInfo;
                            while ((memberInfo = readerMember.readLine()) != null){
                                String [] info = memberInfo.split(",");
                                database.addMember(info[0], info[1], info[3], info[2]);
                            }

                            //for teams
                            BufferedReader readerTeam = new BufferedReader(new FileReader("src/main/java/com/example/researchcentersystem/team.txt"));
                            String teamInfo;
                            while ((teamInfo = readerTeam.readLine())!=null){
                                String [] tInfo = teamInfo.split(",");
                                System.out.println(Arrays.toString(tInfo));
                                Team t1 = new Team (tInfo[0], tInfo[1]);
                                t1.setLeader(tInfo[2]);
                                for (int i = 2; i<tInfo.length;i++){
                                    t1.addTeamMember(database.searchMember(tInfo[i]));
                                    database.searchMember(tInfo[i]).addToMyTeams(t1);
                                }

                                database.addTeamToList(t1);
                            }

                            //for projects
                            BufferedReader readerProjects = new BufferedReader(new FileReader("src/main/java/com/example/researchcentersystem/projects.txt"));
                            String projectInfo;
                            while ((projectInfo = readerProjects.readLine())!=null){
                                String [] pInfo = projectInfo.split(",");
                                System.out.println(Arrays.toString(pInfo));
                                Team t=database.searchTeam(pInfo[1]);
                                if(t!=null){
                                    database.addProject(pInfo[0],t);
                                }
                                else {
                                    database.addProject(pInfo[0],null);

                                }
                            }


                            //if all went well
                            found=true;
                            loginBtn.getScene().getWindow().hide();
                            if(status.equals("admin")) {
                                Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                                Stage stage = new Stage();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                            }
                            else {
                                Parent root = FXMLLoader.load(getClass().getResource("memberPage.fxml"));
                                Stage stage = new Stage();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                            }

                            alert=new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information");
                            alert.setHeaderText(null);
                            alert.setContentText("successfully login");
                            alert.show();
                        }
                    }
                }
                if(!found) {

                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error message");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect username or password");
                    alert.show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
