package otherBoxes;

import java.time.LocalDate;

public class LocalDateBox {
	private LocalDate value;

	public LocalDate getValue() {
		return value;
	}

	public void setValue(LocalDate v) {
		value = v;
	}

	@Override
	public String toString() {

		return String.format("Box mit Wert %s", this.value);
	}
}
