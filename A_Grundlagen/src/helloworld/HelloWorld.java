package helloworld;

import java.util.Scanner;

public class HelloWorld {

	
	public static void main(String[] args) {
		boolean istSchoenesWetter = false;
		char einZeichen = 'W';
		
		short eineZahl = 1277;
		int num = 123456834;
		
		short test = 123;
		
		eineZahl = (short) num; // expliziter Cast 
		
		// impliziter Cast
		num = test;
		
		// Ein Literal f�r float muss ein f haben bei einer Kommazahl
		float temperatur = 13.0F;
		
		double temp1 = 7.0, temp2;
		temp2 = temp1;
		
		// L  f�r long literal
		long grosseZahl = 1234666L;
		
		
		int nr1 = 5;
		int nr2 = 2;
		
		double ergebnis = (double) 5 / (double) 2; 
		
		String einText = """
				Franz
				Irnberger
				""";
		
		var x = nr1; 
		
//		System.out.printf("erg: %.6f\n", ergebnis);		
//		System.out.println("Hello world" + istSchoenesWetter + " zahl: " + eineZahl);		
//		System.out.println(einText);
		
		// var a = typeof(x);
		
		final int ANZAHL = 5;
		
		System.out.println();
		
		
		// Scanner ist nicht in java.lang
		Scanner eingabe = new Scanner(System.in);
		
		// liest ganze Zeile ein
		// nextLine() nicht mit nextInt(), next(), nextDouble() etc. mischen
		// Entweder nur nextLine() 
//		String line = eingabe.nextLine();		
//		int number = eingabe.nextInt();
				
		System.out.println("Bitte eine Zahl eingeben");
		int number = eingabe.nextInt();

		System.out.println("Bitte eine Wort eingeben");
		String einWort = eingabe.next();

		
		System.out.println("Folgendes wurde ausgegeben: " + number + " " + einWort);					
		eingabe.close();  //ACHTUNG, wenn System,in einmal geschlossen ist, kann nicht mehr davon gelesen werden.
		
		
//		Scanner eingabe1 = new Scanner(System.in);
//		int number1 = eingabe.nextInt();
//		System.out.println("Bitte eine Wort eingeben");
//		String einWort1 = eingabe.next();		
		
	}

}
