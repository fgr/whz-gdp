package kritischer_abschnitt.richtig1;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class CriticalSectionExample {
	private final static class Counter {
		@GuardedBy("this")
		private int counter = 0;

		public synchronized void increment() {
			counter++;
		}

		public int value() {
			return counter;
		}
	}

	private final static class CounterRunnable implements Runnable {
		private final Counter c;

		public CounterRunnable(Counter c) {
			this.c = c;
		}

		@Override
		public void run() {
			int i;
			for (i = 0; i < 100_000; i++)
				c.increment();
			System.out.println(Thread.currentThread().getId() + ": " + i);
		}
	};

	public static void main(String[] args) {
		Counter c = new Counter();

		Thread t1 = new Thread(new CounterRunnable(c));
		Thread t2 = new Thread(new CounterRunnable(c));

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(c.value());
	}

}
