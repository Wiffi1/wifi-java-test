package otherBoxes;

public class StringBox {
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String v) {
		value = v;
	}

	@Override
	public String toString() {

		return String.format("Box mit Wert %s", this.value);
	}
}
