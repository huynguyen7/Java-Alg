public class Data {
	private final String orderName;

	private Data() {
		orderName = null;
	}
	public Data(String orderName) {
		this.orderName = orderName;
	}

	@Override
	public String toString() {
		return String.format("[%s]", orderName);
	}
}
