package fileListingsSets;


import java.io.IOException;

public class FileStatisticsProgram {

    public static void main(String[] args) {
        try {
            String dirName = args.length == 0 ? "F_Collections" : args[0];

            FileStatistics stats = new FileStatistics(dirName);
            stats.showAll();

            stats.showFiles(".java");
            stats.showFiles(".txt");

            stats.showOldestNewest();
            stats.showBiggestSmallest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
