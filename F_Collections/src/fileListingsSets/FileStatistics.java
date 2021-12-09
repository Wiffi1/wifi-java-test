package fileListingsSets;


import java.io.File;
import java.io.IOException;
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
    private List<FileData> files = new ArrayList<>();

    /**
     * das Objekt mit dem angegebenen Verzeichnis initialisieren. Alle Files des Verzeichnisses werden
     * rekursiv nach Extension gruppiert
     *
     * @param dirName
     */
    public FileStatistics(String dirName) throws IOException {
        // TODO das Verzeichnis verarbeiten

    }

    // TODO: ein Verzeichnis verarbeiten

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
     * ältestes und neuestes File anzeigen
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
