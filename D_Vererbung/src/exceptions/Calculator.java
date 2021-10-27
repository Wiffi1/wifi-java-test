package exceptions;

public class Calculator {
	public int calculate(char op, int number1, int number2) {
		// Berechnung je nach Operator
		switch (op) {
		case '+':
			return number1 + number2;
		case '-':
			return number1 - number2;
		case '/':
			// TODO: handle division by zery
			try	{
				return number1 / number2;
			} catch(ArithmeticException e) {
				// ArithmeticException in eine CalculationException einpacken und werfen.
				throw new CalculationException("Fehler in der Division.", e);
			}
		case '*':
			return number1 * number2;
		default:
			throw new CalculationException("ungültiger Operator " + op);
//			return 0;
		}

	}
}
