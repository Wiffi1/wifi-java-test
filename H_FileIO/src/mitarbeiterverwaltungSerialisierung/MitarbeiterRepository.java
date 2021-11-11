package mitarbeiterverwaltungSerialisierung;

import serialization.model.Fahrzeug;

import java.util.ArrayList;
import java.util.List;

public class MitarbeiterRepository {

    // Version von unserem Dokument-Format
    private static final long serialVersionUID = 1L;
    private List<Fahrzeug> fahrzeuge = new ArrayList<>();

    private String fileName;


}
