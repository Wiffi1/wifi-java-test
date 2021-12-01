module swdev2021.samples.I.SimpleEditor {
    requires javafx.controls;
    requires javafx.fxml;

    // erlauben, dass Java FX ein Objekt unserer Main-Klasse erzeugen darf,
//    opens students;
    opens layout;
}