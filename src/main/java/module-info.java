module org.example.centro_nic {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.centro_nic to javafx.fxml;
    exports org.example.centro_nic;
}