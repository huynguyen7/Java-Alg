import java.util.function.*;

public class FunctionBiFunctionExample {
	public static void main(String[] args) {
		Function<Integer, Double> squareRoot = Math::sqrt;
		BiFunction<Integer, Integer, Double> power = (a, b) -> Math.pow(a, b);
		
		System.out.println(squareRoot.apply(16));
		System.out.println(power.apply(2,4));
	}
}
