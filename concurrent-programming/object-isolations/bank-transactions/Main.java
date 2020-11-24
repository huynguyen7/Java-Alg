import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.*;

/*
	Compare the difference between the two implementations.
**/

public class Main {
	private static Random random = new Random();
	private static final int MAX_RANDOM_BALANCE = 1000;

	public static void main(final String[] args) {
		// Get input.
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of bank accounts: ");
		final int numAccounts = sc.nextInt(); // suggest 10
		System.out.print("Enter number of transactions: ");
		final int numTransactions = sc.nextInt(); // suggest 200
		System.out.print("Enter number of threads: ");
		final int maxNumThreads = sc.nextInt(); 

		final Account[] accounts = new Account[numAccounts];
		
		// Init bank accounts.
		for(int i = 0; i < numAccounts; ++i)
			accounts[i] = new Account(i, random.nextInt(MAX_RANDOM_BALANCE) + 1);

		System.out.println("\n------OUTPUT------");
		BankTransactionsUsingGlobalIsolation app1 = new BankTransactionsUsingGlobalIsolation(numAccounts, numTransactions, maxNumThreads, accounts);
		BankTransactionsUsingObjectBasedIsolation app2 = new BankTransactionsUsingObjectBasedIsolation(numAccounts, numTransactions, maxNumThreads, accounts);
	}
}
