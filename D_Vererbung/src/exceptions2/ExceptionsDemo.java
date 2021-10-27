package exceptions2;

import java.util.Scanner;

public class ExceptionsDemo {

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
			System.out.println("1. Berechnung:");
			runCalculation();

	}

	private static void runCalculation() {
		char op;
		int number1, number2, result;
		// Operator und Operanden einlesen
		System.out.print("1. Operand: ");
		number1 = Integer.parseInt(scanner.nextLine());
		System.out.print("Welche Operation (+ - * /)? ");
		op = scanner.nextLine().charAt(0);
		System.out.print("2. Operand: ");
		number2 = Integer.parseInt(scanner.nextLine());
		// Berechnung ausführen
		Calculator calc = new Calculator();
		result = calc.calculate(op, number1, number2);

		// Ergebnis anzeigen
		System.out.printf("%d%c%d=%d %n", number1, op, number2, result);
	}

}
