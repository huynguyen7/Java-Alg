import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.*;

/*
	This implementation uses global isolations.
**/

public class BankTransactionsUsingGlobalIsolation {
	private static Random random = new Random();
	private static final int MAX_RANDOM_BALANCE = 1000;

	public static void main(final String[] args) {
		System.out.println("\t*BANK-TRANSACTIONS-GLOBAL*");

		// Get input.
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of bank accounts: ");
		final int numAccounts = sc.nextInt();
		System.out.print("Enter number of transactions: ");
		final int numTransactions = sc.nextInt();
		System.out.print("Enter number of threads: ");
		final int maxNumThreads = sc.nextInt();

		final Account[] accounts = new Account[numAccounts];

		// Init bank accounts.
		for(int i = 0; i < numAccounts; ++i)
			accounts[i] = new Account(i, random.nextInt(MAX_RANDOM_BALANCE) + 1);

		BankTransactionsUsingGlobalIsolation app = new BankTransactionsUsingGlobalIsolation(numAccounts, numTransactions, maxNumThreads, accounts);
	}
	
	public BankTransactionsUsingGlobalIsolation(int numAccounts, int numTransactions, int maxNumThreads, final Account[] accounts) {
		System.out.printf("Time taken in GLOBAL ISOLATIONS:\n");
		// execute the tasks.
		for(int numThreads = 1; numThreads <= maxNumThreads; ++numThreads)
			this.benchmark(numAccounts, numTransactions, numThreads, accounts);
		System.out.println();
	}

	private void benchmark(int numAccounts, int numTransactions, int numThreads, final Account[] accounts) {
		if(numAccounts <= 0 || numTransactions <= 0) {
			System.out.println("INVALID INPUT.");
			return;
		}

		SystemProperty.numWorkers.setProperty(String.valueOf(numThreads)); // Set num threads.
		
		final double startTime = System.nanoTime();

		PCDP.finish(() -> { // wait for all tasks in async to finish..
			for(int i = 0; i < numTransactions - 1; ++i) {
				final int transactionId = i;
				PCDP.async(() -> {
					process(transactionId, numAccounts, accounts);
				}); // execute the transaction task in async.
			}

			// async peeling
			process(numTransactions - 1, numAccounts, accounts);
		});

		final double endTime = System.nanoTime();
		final double timeTaken = (endTime - startTime) / 1e6; // milliseconds.
		System.out.printf("\tNUM_THREADS = %d\tTIME_TAKEN = %10.3f ms.\n", numThreads, timeTaken);
	}

	private void process(int transactionId, int numAccounts, Account[] accounts) {
		final int srcAccountId = random.nextInt(numAccounts);
		final int destAccountId = random.nextInt(numAccounts);
		Account srcAccount = accounts[srcAccountId];
		Account destAccount = accounts[destAccountId];

		PCDP.isolated(() -> { // Isolate globally, lock this piece of code -> Only one thread can access.
			final int transferAmount = random.nextInt(srcAccount.balance() + 1);
			final boolean isSucceeded = srcAccount.withdraw(transferAmount);

			busyWork(srcAccountId, destAccountId);
			if(isSucceeded) destAccount.deposit(transferAmount);
		});
	}

	// simulation of doing task.
	private void busyWork(final int srcAccountId, int destAccountId) {
		try {
			Thread.sleep(1); // 1 ms. CHANGE THIS VALUE.
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
