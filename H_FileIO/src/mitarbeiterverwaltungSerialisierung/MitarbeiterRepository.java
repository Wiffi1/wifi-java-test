package mitarbeiterverwaltungSerialisierung;

import mitarbeiterverwaltungSerialisierung.model.Mitarbeiter;

import java.util.ArrayList;
import java.util.List;

public class MitarbeiterRepository {

    // Version von unserem Dokument-Format
    private static final long serialVersionUID = 1L;
    private List<Mitarbeiter> mitarbeiterListe = new ArrayList<>();

    private String fileName;

    public MitarbeiterRepository(String fileName) {
//        this.mitarbeiterListe = mitarbeiterListe;
        this.fileName = fileName;
    }



}
