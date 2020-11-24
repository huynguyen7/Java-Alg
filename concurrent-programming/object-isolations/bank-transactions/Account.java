public class Account {

	private int id;
	private int balance;

	public Account(int id, int balance) {
		this.id = id;
		this.balance = balance;
	}

	// Return the account's balance.
	public int balance() {
		return balance;
	}

	// Remove the specified amount from the current balance.
	public boolean withdraw(final int amount) {
		if(amount > 0 && amount < balance) {
			balance -= amount;
			return true;
		}

		return false;
	}

	// Add the specified amount from the current balance.
	public boolean deposit(final int amount) {
		if(amount > 0) {
			balance += amount;
			return true;
		}

		return false;
	}
}
