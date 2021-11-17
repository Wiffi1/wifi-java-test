module swdev2021.samples.I.JavaFx {
    // die erforderlichen JavaFX-Module importieren
    // das geht nu, wenn die JavaFX-Library in dem IntelliJ-Module eingebunden ist.

    requires javafx.controls;
    requires javafx.fxml;

    // erlauben, dass Java FX ein Objekt unserer Main-Klasse erzeugen darf,
    opens introFx;
    opens introFxml;
}