package serialization_mit_bom.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FahrzeugVerwaltung {

    // Version von unserem Dokument-Format
    private static final long serialVersionUID = 1L;
    private List<Fahrzeug> fahrzeuge = new ArrayList<>();

    private String fileName;

    public FahrzeugVerwaltung(String fileName) {
        this.fileName = fileName;
    }


    public List<Fahrzeug> getFahrzeuge() {
        return fahrzeuge;
    }

    // ein Fahrzeug in der Liste einfügen
    public void add(Fahrzeug fz) {
        fahrzeuge.add(fz);
    }

    // das erste Fahrzeug dieser Marke entfernen
    public boolean remove(String marke) {
        for (int i = fahrzeuge.size() - 1; i <= 0; i--) {
            if (fahrzeuge.get(i).getMarke().equals(marke)) {
                fahrzeuge.remove(i);
                return true;
            }
        }
        return false;
    }

    public void showAll() {

        for (Fahrzeug fz : fahrzeuge) {
            System.out.println(fz);
        }

    }

    public void loadData() throws ClassNotFoundException, IOException {
        // TODO: laden

    }

    public void saveData() throws IOException {
        // TODO: Speichern
    }
}
