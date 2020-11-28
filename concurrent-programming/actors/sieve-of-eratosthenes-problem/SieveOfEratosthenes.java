import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class SieveOfEratosthenes {
	public static void main(String[] args) {
		System.out.println("\t----SIEVE-OF-ERATOSTHENES----");
		System.out.print("Enter input n: ");
		Scanner sc = new Scanner(System.in);

		int n  = sc.nextInt();
		if(n < 2) {
			System.out.println("INVALID INPUT.");
			return;
		}

		System.out.print("Enter number of max threads: ");
		int maxNumThreads = sc.nextInt();
		if(maxNumThreads < 0) {
			System.out.println("INVALID INPUT.");
			return;
		}


		SieveOfEratosthenes prog = new SieveOfEratosthenes(n, maxNumThreads);
		prog.start();
	}

	private final int n;
	private final int maxNumThreads;

	// Constructor
	public SieveOfEratosthenes(int n, int maxNumThreads) {
		this.n = n;
		this.maxNumThreads = maxNumThreads;
	}

	private void start() {
		List<Integer> rsInSequential = printTimeInSequential();
		System.out.printf("\nTime taken in PARALLEL:\n");
		for(int numThreads = 1; numThreads <= maxNumThreads; ++numThreads) {
			List<Integer> rsInParallel = printTimeInParallel(numThreads);
			assertion(rsInSequential, rsInParallel);
		}
	}
	
	private List<Integer> printTimeInSequential() {
		double startTime = System.nanoTime();
		
		boolean[] isPrime = new boolean[n + 1];
		Arrays.fill(isPrime, true);

		for(int num = 3; num <= Math.sqrt(n); num += 2) {
			if(isPrime[num]) {
				for(int i = num * num; i <= n; i += num)
					isPrime[i] = false;
			}
		}

		List<Integer> rs = new ArrayList<>();
		rs.add(2);
		for(int i = 3; i < isPrime.length; i += 2) {
			if(isPrime[i])
				rs.add(i);
		}

		double endTime = System.nanoTime();
		double timeTaken = (endTime - startTime) / 1e6; // milliseconds. 

		System.out.printf("\nTime taken in SEQUENTIAL: %10.3f ms.\n", timeTaken);
		//System.out.println(rs.toString());

		return rs;
	}

	private List<Integer> printTimeInParallel(final int numThreads) {
		SystemProperty.numWorkers.setProperty(String.valueOf(numThreads)); // Set num threads.

		final SieveActor actor = new SieveActor(); 
		actor.add(2); // just to add 2 as the base case.

		double startTime = System.nanoTime();

		PCDP.finish(() -> { // wait for all tasks in async to finish.
			for(int num = 3; num <= n; num += 2)
				actor.send(num);
			
			actor.send(0); // terminate actors.
		});

		double endTime = System.nanoTime();
		double timeTaken = (endTime - startTime) / 1e6; // milliseconds.
		System.out.printf("\tNUM_THREADS = %d\tTIME_TAKEN = %10.3f ms.\n", numThreads, timeTaken);
		
		List<Integer> rs = actor.getRs();
		SieveActor tmpActor = actor.getNextActor();
		while(tmpActor != null) {
			rs.addAll(actor.getRs());
			tmpActor = tmpActor.getNextActor();
		}
		//System.out.println(rs.toString());
		
		return rs;
	}

	// Use 'java -ea' SieveOfEratosthenes for assertions.
	private void assertion(List<Integer> list1, List<Integer> list2) {
		assert(list1.size() == list2.size()): String.format("INVALID LIST SIZE %d != %d.", list1.size(), list2.size());
		for(int i = 0; i < list1.size(); ++i)
			assert(list1.get(i).compareTo(list2.get(i)) == 0): String.format("INVALID VALUE %d != %d.", list1.get(i), list2.get(i));
	}
}
