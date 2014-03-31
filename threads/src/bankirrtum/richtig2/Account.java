package bankirrtum.richtig2;

import java.util.concurrent.TimeUnit;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public final class Account {
	@GuardedBy("this")
	private double amount;

	public Account() {
		this(0);
	}

	public Account(double initialAmount) {
		amount = initialAmount;
	}

	public void withdraw(double d) {
		// System.out.println("(Thread: " + Thread.currentThread().getName() +
		// ")" + " Trying to withdraw " + d + "." +
		// " Account's current amount: " + amount);

		if (d <= 0)
			throw new IllegalArgumentException("Can not withdraw amounts <= 0!");

		synchronized (this) {
			if (amount < d)
				throw new IllegalStateException("Account limit exceeded!");

			waitFor(100, TimeUnit.MILLISECONDS);

			amount -= d;
		}

		System.out.println("(Thread: " + Thread.currentThread().getName() + ")" //
				+ " Withdrawal successful! Amount withdrawn: " + d + "."//
				+ " Account's new amount: " + amount);
	}

	public void deposit(double d) {
		if (d <= 0)
			throw new IllegalArgumentException("Can not deposite amounts <= 0!");

		waitFor(100, TimeUnit.MILLISECONDS);

		synchronized (this) {
			amount += d;
		}

		System.out.println("(Thread: " + Thread.currentThread().getName() + ")" //
				+ " Deposit successful! Amount deposited: " + d + "."//
				+ " Account's new amount: " + amount);
	}

	private static void waitFor(long timeSpan, TimeUnit timeUnit) {
		try {
			long millisecs = timeUnit.convert(timeSpan, TimeUnit.MILLISECONDS);
			Thread.sleep(millisecs);
		} catch (InterruptedException e) {
			/* exception ignored */
		}
	}
}
