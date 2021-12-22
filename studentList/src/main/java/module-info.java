module swdev2021.samples.studentList {
    requires javafx.controls;
    requires javafx.fxml;

    // für JDBC
    requires java.sql;

    opens students.program;
    opens students.model;



}