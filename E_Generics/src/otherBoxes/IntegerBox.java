package otherBoxes;

public class IntegerBox {
	private Integer value;

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer v) {
		value = v;
	}

	@Override
	public String toString() {

		return String.format("Box mit Wert %s", this.value);
	}
}
