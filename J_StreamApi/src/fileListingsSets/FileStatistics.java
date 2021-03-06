package fileListingsSets;


import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * die Klasse gruppiert die Files eines Verzeichnisses (samt Unterverzeichnissen) nach der
 * File-Extension und unterstützt statistische Auswertungen nach Extension.
 *
 * @author Michaela
 */
public class FileStatistics {
    // passende Zuordnung für File-Extension zu Liste von Filenamen
    // statt einzelner Listen
    // private List<FileData> textFiles , javaFiles, classFiles;
    // TODO Set statt liste
    //
    private Set<FileData> files = new TreeSet<>();

    /**
     * das Objekt mit dem angegebenen Verzeichnis initialisieren. Alle Files des Verzeichnisses werden
     * rekursiv nach Extension gruppiert
     *
     * @param dirName
     */
    public FileStatistics(String dirName) throws IOException {
        // das Verzeichnis verarbeiten
        System.out.printf("dirName: " + dirName);
       readDirectory(new File(dirName));

        // in einer Liste könne gleiche Objekte
       // in einem Set kommt jedes Objekt nur einmal vor -> es gibt keine Verdoppelung durch 2-fache Verarbeitung
       readDirectory(new File(dirName));
    }

    // ein Verzeichnis verarbeiten
    private void readDirectory(File dir) {
        // alle Files und Verzeichnisst in diesem
        File[] files = dir.listFiles();
        System.out.printf("Verzeichnus %s: %d Einträge\n", dir.getAbsolutePath(), files.length);
        // alle Einträge verarbeiten

        // 2 Stream-Api
        // 1. verabetiet unr Files und erzeugt asu jedem File ein FileDataObjekt, das im foreach in die Liste hingzugefügt wird
        // 2. verarbeitet nur Directories und führt für jedes Verzeichnis die readDirectory-Methode aus


        // 2 Stream-API-Aufrufe:
// der 1. verarbeitet nur Files und erzeugt aus jedem File ein FileData-Objekt, das im foreach in die Liste hinzugefügt wird
        Stream.of(files)
            .filter(File::isFile)
// oder
//.filter(f -> f.isFile())
//            .map(file -> new FileData(file.getAbsolutePath(), file.length(), Instant.ofEpochMilli(file.lastModified())))
            // mit neuem Konstruktor
//            .map(file -> new FileData(file))
            // oder mit Kontruktor-Referenz
            .map(FileData::new)
// statt method reference geht auch
//.forEach(fd -> this.files.add(fd));
            .forEach(this.files::add);

/*        Arrays.stream(files)
            .filter(f -> f.isFile())
            .map(file -> new FileData(file.getAbsolutePath(), file.length(), Instant.ofEpochMilli(file.lastModified())))
            .forEach(fd -> this.files.add(fd));

        Arrays.stream(files)
            .filter(f -> f.isDirectory())
            .forEach(file -> {
                System.out.printf("\tUnterverzeichnis %s...", file.getAbsolutePath());
                // auch das Unterverzeichnis verarbeiten.
                readDirectory(file);
            });

        Stream.of(files)
            .filter(f -> f.isDirectory())
            .forEach(file -> {
                System.out.printf("\tUnterverzeichnis %s...", file.getAbsolutePath());
                // auch das Unterverzeichnis verarbeiten.
                readDirectory(file);
            });*/


// der 2. verarbeitet nur Directories und führt für jedes Verzeichnis die readDirectory-Methode aus
        Stream.of(files)
            .filter(File::isDirectory)
// Alternative ohne method reference:
//.forEach(file -> readDirectory(file));
            .forEach(this::readDirectory);


//        for (File file: files) {
//            // für Files: In der Liste hinzufügen
//            if (file.isFile()) {
////               String path = file.getAbsolutePath();
////               long size = file.length();
////               // Änderungsdatum: milliseconds seit 1.1.1970
////               long lastModifiedMillis = file.lastModified();
////               // Einen Instand daraus erzeugen
////                Instant lastModified = Instant.ofEpochMilli(lastModifiedMillis);
//                // daraus ein FileData-Objekt erzeugen
////                FileData fd = new FileData(path, size, lastModified);
//                FileData fd = new FileData(file.getAbsolutePath(), file.length(), Instant.ofEpochMilli(file.lastModified()));
//                this.files.add(fd);
//            } else  if(file.isDirectory()) {
//                System.out.printf("\tUnterverzeichnis %s...", file.getAbsolutePath());
//                // auch das Unterverzeichnis verarbeiten.
//                readDirectory(file);
//            }
//        }

    }

    /**
     * Alle Files von allen Extensions anzeigen
     */
    public void showAll() {
        System.out.println("Alle Files:");
        // alle anzeige
        //
        files.stream().forEach(a -> System.out.printf("\t%s\n", a));
//        for (FileData fd : files) {
//            // alle Files anzeigen
//            System.out.printf("\t%s\n", fd);
//        }
        System.out.println();
    }

    /**
     * alle Files der angegebenen Extension anzeigen
     *
     * @param ext die Extension
     */
    public void showFiles(String ext) {
        System.out.println("Files with extension " + ext);
/*
        for (FileData fd : files) {
            // wenn die Extension passt:
            if(fd.getExtension().equalsIgnoreCase(ext))
                // das File anzeigen
                System.out.printf("\t%s\n", fd);
        }
*/

        files.stream()
            .filter(file -> file.getExtension().equalsIgnoreCase(ext))
            .forEach(a -> System.out.printf("\t%s\n", a));

//        Set<FileData>
/*
        Stream<FileData> myStream =  files.stream()
            .filter(file -> file.getExtension().equalsIgnoreCase(ext));
        myStream.forEach(a -> System.out.printf("\t%s\n", a));
*/

        System.out.println();
    }



    /**
     * kleinstes und neuestes File anzeigen
     */
    public void showOldestNewest() {
        System.out.println("Ältestes/neuestes File ");
        FileData minFile = null, maxFile = null;

        // FileData mit min und max Datum suchen
        for (FileData fileData : files) {
            if (minFile == null || fileData.getLastModified().isBefore(minFile.getLastModified())) {
                minFile = fileData;
            }
            if (maxFile == null || fileData.getLastModified().isAfter(maxFile.getLastModified())) {
                maxFile = fileData;
            }
        }

        // Ergebnis anzeigen
        System.out.printf("\tÄltestes File: %s\n", minFile);
        System.out.printf("\tJüngstes File: %s\n", maxFile);
        System.out.println();
    }

    /**
     * ältestes und neuestes File anzeigen
     */
    public void showBiggestSmallest() {
        System.out.println("Ältestes/neuestes File ");

        FileData minFile = files.stream().min(Comparator.comparing(f -> f.getSize())).orElse(null);
        FileData maxFile = files.stream().min(Comparator.comparing(FileData::getSize)).orElse(null);

/*
        FileData minFile = null, maxFile = null;
        // FileData mit min und max Größe suchen
        for (FileData fileData : files) {
            if (minFile == null || fileData.getSize() < minFile.getSize()) {
                minFile = fileData;
            }
            if (maxFile == null || fileData.getSize() > maxFile.getSize()) {
                maxFile = fileData;
            }
        }
*/

        // Ergebnis anzeigen
        System.out.printf("\tKleinstes File: %s\n", minFile);
        System.out.printf("\tGrößtes   File: %s\n", maxFile);
        System.out.println();
    }

    public void showBiggestSmallestNew() {
        System.out.println("Ältestes/neuestes File ");
//        FileData minFile = null, maxFile = null;
        // FileData mit min und max Größe suchen
/*        for (FileData fileData : files) {
            if (minFile == null || fileData.getSize() < minFile.getSize()) {
                minFile = fileData;
            }
            if (maxFile == null || fileData.getSize() > maxFile.getSize()) {
                maxFile = fileData;
            }
        }*/

//        Optional<FileData> min = Arrays.stream(files)
//            .filter(Predicate.not(Animal::isHerbivore))

        Optional<FileData> minFile = files.stream()
            .min(Comparator.comparing(f -> f.getSize()));

        Optional<FileData> maxFile = files.stream()
            .min(Comparator.comparing(f -> f.getSize()));

        // Ergebnis anzeigen
        System.out.printf("\tKleinstes File: %s\n", minFile);
        System.out.printf("\tGrößtes   File: %s\n", maxFile);
        System.out.println();
    }

}



/*            .forEach(s -> System.out.printf("\t%s\n", s));

                // den Fleischfresser mit dem kleinsten Gewicht
                Optional<Animal> min = Arrays.stream(allAnimals)
    .filter(Predicate.not(Animal::isHerbivore))
    .min(Comparator.comparing(Animal::getWeight));

    min.ifPresent(a -> System.out.println("Das Tier mit dem kleinsten Gewich ist " + a.getName()));

    int weight = 10;
    Animal max = Arrays.stream(allAnimals)
    .filter(a -> a.getWeight() < weight)
    .max(Comparator.comparing(Animal::getWeight))
    // wenn kein Objekt im ERgebnis ist, soll null geliefert werden
    .orElse(null);
    if (max != null) {
    System.out.println("Schwerste Tier unter " + weight + " x kg ist " + max);
    } else {
    System.out.println("keine Tiere im Stream");
    }
    }*/

