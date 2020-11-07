import java.util.function.*;
import java.util.*;

public class ConsumerBiConsumerExample {
	public static void main(String[] args) {
		Consumer<String> c1 = (String a) -> System.out.println(a);
		Consumer<String> c2 = (a) -> System.out.println(a);
		Consumer<String> c3 = System.out::println;

		c1.accept("HELLO FROM CONSUMER 1");
		c2.accept("HELLO FROM CONSUMER 2");
		c3.accept("HELLO FROM CONSUMER 3");

		System.out.println();
		BiConsumer<String, String> bc1 = (String a, String b) -> System.out.println(a + ", " + b);
		BiConsumer<String, String> bc2 = (a, b) -> System.out.println(a + ", " + b);
		BiConsumer<String, String> bc3 = ConsumerBiConsumerExample::printTwoArguments;
		
		bc1.accept("HELLO FROM BICONSUMER 1", "1");
		bc2.accept("HELLO FROM BICONSUMER 2", "2");
		bc3.accept("HELLO FROM BICONSUMER 3", "3");
	}

	private static void printTwoArguments(String a, String b) {
		System.out.println(a + ", " + b);
	}
}
