package personen;

public class PersonenMainSimple {
    public static void main(String[] args) {
        Person [] gruppe = new Person[5];
        // die ersten paar Indices verwenden
        gruppe[0] = new Person("Max", 10);
        gruppe[1] = new Person("Moritz", 8);
        gruppe[3] = new Person("Susi", 12);

        for (int i = 0; i < gruppe.length; i++) {
            Person tempPerson = gruppe[i];

            // Wenn an der Stelle eine Referenz vorhanden sit
            if (tempPerson != null) {
                System.out.printf("Person am Index %d: %s\n", i, tempPerson.getName());
            } else {
                System.out.printf("  --Person am Index nicht vorhanden\n");
            }

        }
    }
}
