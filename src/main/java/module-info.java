module com.example.researchcentersystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.researchcentersystem to javafx.fxml;
    exports com.example.researchcentersystem;
}