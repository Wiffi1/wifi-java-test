package mitarbeiterverwaltungSerialisierung.model;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Repository<T> implements  RepositoryInterface<T> {

    // Version von unserem Dokument-Format
    private static final long serialVersionUID = 2L;
    private List<T> list = new ArrayList<T>();
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
                for (T listItem : list) {
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
            List<T> temp = (List<T>) ois.readObject();
            list = temp;
            Mitarbeiter.initNextId(ois.readInt());

            if (debugLevel == 1) {
                System.out.printf("%d Items vom File geladen\n",  list.size() );
                System.out.printf("\n %d Items vom File geladen\n",  temp.size() );
                for (T listItem : list) {
                    System.out.printf("\t%s\n", listItem);
                }
            }
        }
    }

    public List<T> getAll() throws IOException, ClassNotFoundException {
        loadData();
        return list;
    }

    public T getById(int id) {
        int index = getIndexFromId(id);
        if (index == -1) {
            throw new IndexOutOfBoundsException();
        }
        T ma = list.get(index);
        return ma;
    }

    public T updateById(int id, T ma) throws IOException {
        T tmpMa = getById(id);
        // todo so lassen oder verbessern?
        tmpMa = ma;
        saveData();
        return ma;
    }

    public void add(T ma) throws IOException {
        list.add(ma);
        saveData();
    }

    public T removeById(int id) throws IOException {
        T ma = getById(id);
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
        for (int i = 0; i < list.size(); i++) {

            // todo kann man das hier generisch machen?
//            Mitarbeiter mTemp = (Mitarbeiter) list.get(i);
            T mTemp1 = (T) list.get(i);


//            https://stackoverflow.com/questions/8918550/cast-via-reflection-and-use-of-class-cast
//            mTemp1.getClass().getDeclaredFields().
            Field[] fields = mTemp1.getClass().getDeclaredFields();
            for (int j = 0; j < fields.length; j++) {
                System.out.println("fields##: " + fields[j].getName());
            }

//            System.out.printf("");
//            klass.getMethod(methodName, null).invoke();

            Mitarbeiter mTemp = (Mitarbeiter) list.get(i);
            // wenn die Id gleich der gesuchten Id ist
            if(mTemp.getId() == mitarbeiterID){
                // den Index zurückliefern
                return i;
            }
        }

        // wenn wir die Nummer nicht gefunden haben,
        // einen ungültigen Index zurückliefern
        return -1;
    }


/*    public <T> T getObject(Class<T> type){
        Object o = getSomeObject();
        if (type.isInstance(o)){
            return type.cast(o);
        } else {
            return null;
        }
    }*/
}
