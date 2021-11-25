package mitarbeiterverwaltungSerialisierung.model;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Repository<T> implements  RepositoryInterface<T> {

    // Version von unserem Dokument-Format
    private static final long serialVersionUID = 2L;
    private List<T> list = new ArrayList<>();
    private String fileName;
    private boolean debugLoadedData, debugSavedData;
//    private boolean useReflection4MitarbeiterClass = true;
    private boolean useReflection4MitarbeiterClass;

    public Repository(String fileName) {
        this.fileName = fileName;
        System.out.println(this.fileName);
        try {
            File maFile = new File(fileName);
            // Falls file nicht vorhanden ist, werden Defaultwerte verwendet
            if (maFile.exists()) {
                loadData();
            }
        }
        catch (ClassNotFoundException | IOException e) {
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
            if (debugSavedData) {
                System.out.printf("%d In File gespeichert\n",  list.size() );
                for (T listItem : list) {
                    System.out.printf("\t%s\n", listItem);
                }
            }

            oos.writeObject(list);

            // den Zähler für den index auch speichern
            int id;
            if (useReflection4MitarbeiterClass) {
                id = getCurrentIdByReflection();
            } else {
                id = Mitarbeiter.getCurrentId();
            }
            oos.writeInt(id);
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

            int id = ois.readInt();
            if (useReflection4MitarbeiterClass) {
                initNextIdByReflection(id);
            } else {
                Mitarbeiter.initNextId(id);
            }

            if (debugLoadedData) {
                System.out.printf("%d Items vom File geladen\n",  list.size() );
                System.out.printf("\n %d Items vom File geladen\n",  temp.size() );
                for (T listItem : list) {
                    System.out.printf("\t%s\n", listItem);
                }
            }
        }
    }

    private void initNextIdByReflection(int id) {
        T mTemp0 = (T) list.get(0);
        Method initNextIdMethod = null;

        try {
//                https://www.tutorialspoint.com/java/lang/class_getmethod.htm
            Class[] cArg = new Class[id];
            initNextIdMethod = mTemp0.getClass().getMethod("initNextId", cArg);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        System.out.println("initNextIdMethod " + initNextIdMethod);
        if (initNextIdMethod != null) {
            try {
                initNextIdMethod.invoke(id);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public List<T> getAll(boolean doNotLoadList) throws IOException, ClassNotFoundException {
        File maFile = new File(fileName);
        if (maFile.exists()) {
            if (!doNotLoadList) {
                loadData();
            }
            return list;
        } else {
            return null;
        }
    }

    public T getById(int id) {
        int index = getIndexFromId(id);
        if (index == -1) {
            throw new IndexOutOfBoundsException();
        }
        return list.get(index);
    }

    public T updateById(int id, T ma) throws IOException {
        saveData();
        return ma;
    }

    public void add(T ma) throws IOException {
        list.add(ma);
        saveData();
    }

    public T removeById(int id) throws IOException {
        System.out.println("removeById id %d" + id);
        System.out.println("removeById getCurrentId %d" + Mitarbeiter.getCurrentId());

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
            if (!useReflection4MitarbeiterClass) {
                Mitarbeiter mTemp = (Mitarbeiter) list.get(i);
                // wenn die Id gleich der gesuchten Id ist
                if(mTemp.getId() == mitarbeiterID){
                    // den Index zurückliefern
                    return i;
                }
            } else {
                int id = -1;
                T mTemp = (T) list.get(i);
                Method getIdMethod = getItemMethod("getId", mTemp);

                if (getIdMethod != null) {
                    try {
                        id = (int) getIdMethod.invoke(mTemp);
                        if(id == mitarbeiterID){
                            // den Index zurückliefern
                            System.out.printf("id: %d", id);
                            return i;
                        }

                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // wenn wir die Nummer nicht gefunden haben,
        // einen ungültigen Index zurückliefern
        return -1;
    }

    public Method getItemMethod(String methodName, T mTemp) {
        Method method = null;
        try {
//            System.out.println(mTemp.getClass().getMethods().toString());
            method = mTemp.getClass().getMethod(methodName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return method;
    }

    private int getCurrentIdByReflection() {
        int id = -1;
        T mTemp0 = list.get(0);
        Method getCurrentId = getItemMethod("getCurrentId", mTemp0);

        if (getCurrentId != null) {
            try {
                id = (int) getCurrentId.invoke(mTemp0);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return id;
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
