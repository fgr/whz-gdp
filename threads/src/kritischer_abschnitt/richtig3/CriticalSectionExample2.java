package kritischer_abschnitt.richtig3;

import java.util.concurrent.atomic.AtomicInteger;

import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class CriticalSectionExample2 {
	private final static class Counter {
		private AtomicInteger counter = new AtomicInteger(0);

		public void increment() {
			counter.incrementAndGet();
		}

		public void decrement() {
			counter.decrementAndGet();
		}

		public int value() {
			return counter.get();
		}
	}

	private final static class CounterRunnable implements Runnable {
		private final Counter c;
		private boolean inc;

		public CounterRunnable(Counter c, boolean inc) {
			this.c = c;
			this.inc = inc;
		}

		@Override
		public void run() {
			int i;
			for (i = 0; i < 100_000; i++)
				if (inc)
					c.increment();
				else
					c.decrement();
			System.out.println(Thread.currentThread().getId() + ": " + i);
		}
	};

	public static void main(String[] args) {
		Counter c = new Counter();

		Thread t1 = new Thread(new CounterRunnable(c, true));
		Thread t2 = new Thread(new CounterRunnable(c, false));

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
