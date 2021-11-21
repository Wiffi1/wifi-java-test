package mitarbeiterverwaltungSerialisierung.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repository<T> implements  RepositoryInterface {

    // Version von unserem Dokument-Format
    private static final long serialVersionUID = 2L;
    private List<Mitarbeiter> list = new ArrayList<>();
    private String fileName;
    private int debugLevel = 0;

    public Repository(String fileName) {
        this.fileName = fileName;
        System.out.println(this.fileName);
        try {
            File maFile = new File(fileName);
            // File nicht vorhanden?
            if (!maFile.exists()) {
                // Soll ich da was was tun?
            } else {
                // Sonst: Laden, anzeigen, usw...
                loadData();
            }
        } catch (ClassNotFoundException | IndexOutOfBoundsException | IOException e) {
            System.out.println("Es ist ein Fehler aufgetreten:" + e);
            e.printStackTrace();
        }
    }

    public void saveData() throws IOException {
        // Speichern
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){
            // Die Liste "serialisieren", d.h. alle Objekte mit ihren Attributen
            // in den Stream speichern (Das Ergebnis ist ein Binär-File)

//            https://www.tutorialspoint.com/can-we-serialize-static-variables-in-java
            System.out.println("save'Data");

            if (debugLevel == 2) {
                System.out.printf("uuuuuu%d In File gespeichert\n",  list.size() );
                for (Mitarbeiter listItem : list) {
                    System.out.printf("\t%s\n", listItem);
                }
            }

            oos.writeObject(list);
            // den Zähler für den index auch speichern
            oos.writeInt(Mitarbeiter.getCurrentId());
        }
    }

    public void loadData() throws ClassNotFoundException, IOException {
        // laden
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))){
            // die Liste "deserialisieren", dh aus dem Stream die Array-List und alle
            // Mitarbeiter wiederherstellen
            // Ergebnis ist vom Typ Object -> casten auf den erwarteten Typ
            @SuppressWarnings("unchecked")
            List<Mitarbeiter> temp = (List<Mitarbeiter>) ois.readObject();
            list = temp;
            Mitarbeiter.initNextId(ois.readInt());

            if (debugLevel == 1) {
                System.out.printf("%d Items vom File geladen\n",  list.size() );
                System.out.printf("\n %d Items vom File geladen\n",  temp.size() );
                for (Mitarbeiter listItem : list) {
                    System.out.printf("\t%s\n", listItem);
                }
            }
        }
    }

    public List<Mitarbeiter> getAll() throws IOException, ClassNotFoundException {
        loadData();
        return list;
    }

    public Mitarbeiter getById(int id) {
        int index = getIndexFromId(id);
        if (index == -1) {
            throw new IndexOutOfBoundsException();
        }
        Mitarbeiter ma = list.get(index);
        return ma;
    }

    public Mitarbeiter updateById(int id, Mitarbeiter ma) throws IOException {
        Mitarbeiter tmpMa = getById(id);
        // ich glaube, das funzt so noch nicht.
        tmpMa = ma;
        saveData();
        return ma;
    }

    public void add(Mitarbeiter ma) throws IOException {
        list.add(ma);
        saveData();
    }

    public Mitarbeiter removeById(int id) throws IOException {
        Mitarbeiter ma = getById(id);
        int index = getIndexFromId(id);
        list.remove(index);
        saveData();
        return ma;
    }

    // den Index des Mitarbeiters im Array suchen
    private int getIndexFromId(int mitarbeiterID)  {
        // Mitarbeiter suchen
        // indexOf-Methode der Liste ist nicht zielführend, weil wir nicht
        // eine Zahl mit einem Mitarbeiter-Objekt vergleichen können
        //int x = liste.indexOf(nr)
        // daher selber das passende Objekt suchen
//        List<Mitarbeiter> mitarbeiterListe = repository.getAll();
        for (int i = 0; i < list.size(); i++) {
            Mitarbeiter mTemp = list.get(i);
            // wenn die Nummer gleich der gesuchten Nummer ist
            if(mTemp.getId() == mitarbeiterID){
                // den Index zurückliefern
                return i;
            }
        }

        // wenn wir die Nummer nicht gefunden haben,
        // einen ungültigen Index zurückliefern
        return -1;
    }
}
