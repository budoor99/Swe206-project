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
            try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Dell\\Documents\\SWE206\\researchCenterSystem\\src\\main\\java\\com\\example\\researchcentersystem\\loginInfo"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");

                    if (parts.length == 4) {
                        String storedUsername = parts[0];
                        String storedPassword = parts[1];
                        String status=parts[3];
                        if (name.equals(storedUsername) && pass.equals(storedPassword)) {
                            //if all went well
                            found=true;
                            loginBtn.getScene().getWindow().hide();
                            if(status=="admin") {
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
