import java.util.function.*;

public class SupplierExample {
	public static void main(String[] args) {
		Supplier<Double> getRandomDouble = Math::random;
		System.out.println(getRandomDouble.get());
	}
}
