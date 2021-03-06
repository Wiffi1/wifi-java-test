package fileListingsSets;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Instant;
import java.util.*;

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
        for (File file: files) {
            // für Files: In der Liste hinzufügen
            if (file.isFile()) {
               String path = file.getAbsolutePath();
               long size = file.length();
               // Änderungsdatum: milliseconds seit 1.1.1970
               long lastModifiedMillis = file.lastModified();
               // Einen Instand daraus erzeugen
                Instant lastModified = Instant.ofEpochMilli(lastModifiedMillis);
                // daraus ein FileData-Objekt erzeugen
                FileData fd = new FileData(path, size, lastModified);
                this.files.add(fd);
            } else  if(file.isDirectory()) {
                System.out.printf("\tUnterverzeichnis %s...", file.getAbsolutePath());
                // auch das Unterverzeichnis verarbeiten.
                readDirectory(file);
            }
        }
    }

    /**
     * Alle Files von allen Extensions anzeigen
     */
    public void showAll() {
        System.out.println("Alle Files:");
        // alle anzeige
        for (FileData fd : files) {
            // alle Files anzeigen
            System.out.printf("\t%s\n", fd);
        }
        System.out.println();
    }

    /**
     * alle Files der angegebenen Extension anzeigen
     *
     * @param ext die Extension
     */
    public void showFiles(String ext) {
        System.out.println("Files with extension " + ext);
        for (FileData fd : files) {
            // wenn die Extension passt:
            if(fd.getExtension().equalsIgnoreCase(ext))
                // das File anzeigen
                System.out.printf("\t%s\n", fd);
        }
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

        // Ergebnis anzeigen
        System.out.printf("\tKleinstes File: %s\n", minFile);
        System.out.printf("\tGrößtes   File: %s\n", maxFile);
        System.out.println();
    }
}
