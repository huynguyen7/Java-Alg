import java.util.stream.*;
import java.util.stream.Collector.*;
import java.util.stream.Collectors.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

// EXPLANATION:
// https://stackoverflow.com/questions/29122394/word-frequency-count-java-8?noredirect=1&lq=1

// Word-Count
// Applying map-reduce
public class Example {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		String[] words = generateRandom(n);

		printTimeInSerial(words);
		printTimeInParallel(words);
	}

	private static void printTimeInSerial(String[] words) {
		double startTime = System.nanoTime();
		Map<String, Integer> map = new HashMap<>();
		for(String word: words)
			map.put(word, map.getOrDefault(word, 0) + 1);
		double endTime = System.nanoTime();
		double timeTaken = endTime - startTime;
		System.out.println("Time taken in serial: " + (timeTaken / 1e9) + " seconds.");
		System.out.println(map.toString() + "\n");
	}

	private static void printTimeInParallel(String[] words) {
		double startTime = System.nanoTime();
		Map<String, Integer> map = Stream.of(words)
								.parallel()
								.collect(Collectors.toConcurrentMap(
									word -> word, 
									w -> 1, 
									Integer::sum));
		double endTime = System.nanoTime();
		double timeTaken = endTime - startTime;
		System.out.println("Time taken in parallel stream: " + (timeTaken / 1e9) + " seconds.");
		System.out.println(map.toString() + "\n");
	}

	private static final String[] templates = {"HUY", "TOAN", "QUOC"};

	private static String[] generateRandom(int n) {
		Random rand = new Random();
		String[] rs = new String[n];
		for(int i = 0; i < rs.length; ++i)
			rs[i] = new String(templates[rand.nextInt(3)]);
		return rs;
	}
}
