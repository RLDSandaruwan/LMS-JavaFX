module lms {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires jbcrypt;
    requires jakarta.mail;
    requires java.sql;

    opens com.pcl.lms to javafx.controls, javafx.fxml,javafx.graphics;
    opens com.pcl.lms.view.tm to javafx.base;

    //optional

    exports com.pcl.lms.controller;
}