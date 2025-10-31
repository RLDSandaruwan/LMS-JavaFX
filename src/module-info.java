module lms {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires jbcrypt;
    requires jakarta.mail;

    opens com.pcl.lms to javafx.controls, javafx.fxml,javafx.graphics;

    //optional

    exports com.pcl.lms.controller;
}