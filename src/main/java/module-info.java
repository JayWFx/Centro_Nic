module org.example.centro_nic {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.centro_nic to javafx.fxml;
    opens org.example.centro_nic.controllers to javafx.fxml;
    opens org.example.centro_nic.models to javafx.base;

    exports org.example.centro_nic;
    exports org.example.centro_nic.controllers;
    exports org.example.centro_nic.models;
}