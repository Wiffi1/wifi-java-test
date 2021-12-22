package competition;

public class CompetitionProgram1 {
    public static void main(String[] args) {
        //  Array mit 3 Runner-Objekten erzeugen
        Runner[] runnerList = {
                new Runner("Tick"), new Runner("Trick"), new Runner("Track")
        };

        // Array mit 3 Thread-Objekten erzeugen
        Thread[] runnerThreads = new Thread[runnerList.length];
        for (int i = 0; i < runnerThreads.length; i++) {
            runnerThreads[i] = new Thread(runnerList[i]);
        }

        // Wettkampf starten
        for (int i = 0; i < runnerThreads.length; i++) {
            runnerThreads[i].start();
        }

        // auf das Ende warten
        for (int i = 0; i < runnerThreads.length; i++) {
            try {
                runnerThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        // die for-schleifen dürfen nicht "wegoptimiert" werden
//        for (int i = 0; i < runnerThreads.length; i++) {
//            runnerThreads[i] = new Thread(runnerList[i]);
//            runnerThreads[i].start();
//            try {
//                runnerThreads[i].join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
