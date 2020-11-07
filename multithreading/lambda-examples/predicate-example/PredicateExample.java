import java.util.function.*;
import java.util.*;

public class PredicateExample {
	public static void main(String[] args) {
		List<String> names = new ArrayList<>();
		Predicate<Integer> isLessThan100 = i -> i < 100;
		
		System.out.println(isLessThan100.test(88));
		System.out.println(isLessThan100.test(102));
	}
}
