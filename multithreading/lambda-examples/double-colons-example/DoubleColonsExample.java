import java.util.*;

public class DoubleColonsExample {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");

		list.forEach((String name) -> System.out.println(name));
		System.out.println();
		list.forEach(System.out::println); // This does not need any argument
	}
}
