module swdev2021.samples.studentList {
    requires javafx.controls;
    requires javafx.fxml;
    // erlauben, dass Java FX ein Objekt unserer Main-Klasse erzeugen darf,
    opens students.program;
    opens students.model;
    opens layout;
}