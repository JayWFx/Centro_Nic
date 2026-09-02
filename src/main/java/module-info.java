module ni.uam.edu.centronic {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;

    opens ni.uam.edu.centronic.controllers to javafx.fxml;
    opens ni.uam.edu.centronic.modelos to javafx.base;
    exports ni.uam.edu.centronic;
    exports ni.uam.edu.centronic.controllers;
    exports ni.uam.edu.centronic.modelos;
}