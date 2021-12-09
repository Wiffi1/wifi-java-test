package fileListingsSets;

import java.io.File;
import java.time.Instant;

/**
 * die Klasse kapselt die erforderlichen Informationen für ein File
 *
 * @author Michaela
 */
public class FileData implements Comparable<FileData> {

    // Attribute für name, path, size und lastModified
    private final String name, path, extension;

    private final long size;

    private final Instant lastModified;

    /**
     * passender Konstruktor mit dem alle Attribute initialisiert werden
     *
     * @param path
     * @param size
     * @param lastModified
     */
    public FileData(String path, long size, Instant lastModified) {
        super();
        this.path = path;
        this.name = new File(path).getName();
        int index = name.lastIndexOf('.');
        this.extension = index >= 0 ? name.substring(index) : "";
        this.size = size;
        this.lastModified = lastModified;
    }

    /**
     * getter für den Namen
     */
    public String getName() {
        return name;
    }

    /**
     * getter für den Pfad
     */
    public String getPath() {
        return path;
    }

    public String getExtension() {
        return extension;
    }

    /**
     * getter für die Größe
     */
    public long getSize() {
        return size;
    }

    /**
     * getter für das Datum der letzten Änderung
     */
    public Instant getLastModified() {
        return lastModified;
    }

    // toString überschreiben
    @Override
    public String toString() {

        return String.format("%-30s - %10d Byte - %s - %s", name, size, lastModified, path);
    }

    // hashCode und equals implementieren
    @Override
    public int hashCode() {
        // einen Streuwert für das Objekt liefern, z.B. für relvante Zeichenfolge des Objekts
        return path.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        // wenn das andere Objekt auch ein FileData-Objekt ist
        if(other instanceof FileData fd) {
            return path.equals(fd.path) && size == fd.size && lastModified.equals(fd.lastModified);
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(FileData o) {
        int ret = path.compareTo(o.path);
        if (ret == 0) {
            ret = lastModified.compareTo(o.lastModified);
            if (ret == 0) {
                ret = Long.compare(size, o.size);
            }
        }
        return ret;
    }
}
