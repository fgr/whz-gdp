package bankirrtum;

import java.util.concurrent.TimeUnit;

public final class Account {
	private double amount;

	public Account() {
		this(0);
	}

	public Account(double initialAmount) {
		amount = initialAmount;
	}

	public void withdraw(double d) {
		// System.out.println("(Thread: " + Thread.currentThread().getName() +
		// ")" //
		// + " Trying to withdraw " + d + "."//
		//		+ " Account's current amount: " + amount);
		
		if (amount < d)
			throw new IllegalStateException("Account limit exceeded!");
		waitFor(100, TimeUnit.MILLISECONDS);
		
		amount -= d;
		
		System.out.println("(Thread: " + Thread.currentThread().getName() + ")" //
				+ " Withdrawal successful! Amount withdrawn: " + d + "."//
				+ " Account's new amount: " + amount);
	}

	private static void waitFor(long timeSpan, TimeUnit timeUnit) {
		try {
			long millisecs = timeUnit.convert(timeSpan, TimeUnit.MILLISECONDS);
			Thread.sleep(millisecs);
		} catch (InterruptedException e) { /* exception ignored */
		}
	}
}
