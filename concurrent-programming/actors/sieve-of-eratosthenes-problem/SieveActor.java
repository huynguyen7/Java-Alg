import java.util.List;
import java.util.ArrayList;

public class SieveActor extends Actor {
	private final int MAX_SIZE_FILTER = 10_000; // CHANGE THIS VALUE FOR EXPERIMENTS.
	private List<Integer> localPrimes;
	private SieveActor nextActor;

	public SieveActor() {
		localPrimes = new ArrayList<>();
		nextActor = null;
	}

	@Override
	public void process(final Object msg) {
		final int num = (Integer) msg;
		if(num <= 0) {
			if(nextActor != null)
				nextActor.send(msg); // terminate the next actor.
			return; // terminate the current actor.
		}

		final boolean isLocallyPrime = isLocallyPrime(num);
		if(isLocallyPrime) {
			if(localPrimes.size() < MAX_SIZE_FILTER) {
				localPrimes.add(num);
			} else if(nextActor != null) {
				nextActor.send(num);
			} else {
				nextActor = new SieveActor();
				nextActor.send(num);
			}
		}
	}

	private boolean isLocallyPrime(final int num) {
		for(int i = 0; i < localPrimes.size(); ++i) {
			if(num % localPrimes.get(i) == 0)
				return false;
		}

		return true;
	}

	public void add(int num) {
		localPrimes.add(2);
	}

	public SieveActor getNextActor() {
		return nextActor;
	}

	public List<Integer> getRs() {
		return localPrimes;
	}

	public int numLocalPrimes() {
		return localPrimes.size();
	}
}
