package personen;

public class PersonenMain {

	// Die Personengruppe ist static, dh. es gibt nur eine einzige Gruppe im gesamten Programm.
	private static PersonenGruppe meineGruppe = new PersonenGruppe(5);

	public static void main(String[] args) {
		
		// anmelden
		testeAnmeldung("Max", 10);
		testeAnmeldung("Moritz", 8);
		testeAnmeldung("Susi", 9);

//		System.out.printf("");
		System.out.println("Nach drei Anmeldungen : ");
		testeAnmeldung("Karli", 7);
		testeAnmeldung("Karo", 9);
		System.out.println("Nach fünf Anmeldungen : ");
		meineGruppe.alleAnzeigen();
		testeAnmeldung("Kurti", 10);
		System.out.println("Nach sechs Anmeldungen : ");
		meineGruppe.alleAnzeigen();

		System.out.println("Eine Person anzeigen");
		meineGruppe.anzeigen(3);
		meineGruppe.anzeigen(99);

		// abmelden
		testeAbmeldung(3);
		System.out.println("Gruppe nach Abmelden:");
		meineGruppe.alleAnzeigen();

		System.out.println("*********** Abmeldung *******************");
		// Person löschen die nicht mehr vorhanden ist
		testeAbmeldung(3);
		System.out.println("Gruppe nach erfolglosem Abmelden:");

		testeAbmeldung(2);
		System.out.println("Gruppe nach erfolglosem Abmelden:");

		System.out.println("*********** Anmedlung *******************");
		System.out.println("Weiter Anmeldung");
		testeAnmeldung("Kurti", 10);
		meineGruppe.alleAnzeigen();
	}

	static void testeAnmeldung(String name, int alter) {
		try {
			// die Methode ausführen, in der ein Fehler auftreten kann
			Person p = new Person(name, alter);
			meineGruppe.anmelden(p);
			System.out.printf("Person angemeldet: %s\n", p/*.getName()*/);
		} catch (Exception e) {
			System.out.println("FEHLER bei der Anmeldung:" + e/*.toString()*/);
		}
	}
	
	static void testeAbmeldung(int nr) {
		try {
			Person tmpPerson = meineGruppe.abmelden(nr);
			// Wenn das Abmelden erfolgreich war, => anzeigen
			System.out.println("Person abgmeldet: " + tmpPerson);
		} catch (Exception e) {
			System.out.println("FEHLER beim Abmelden:" + e/*.toString()*/);
		}
	}



}
