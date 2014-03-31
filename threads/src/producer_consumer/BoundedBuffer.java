package producer_consumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public final class BoundedBuffer {
	private final Lock lock = new ReentrantLock();

	/**
	 * Condition indicating that additional entries can be added to the
	 * {@link #buffer} as it has not yet reached its maximum {@link #capacity}.
	 */
	private final Condition notFull = lock.newCondition();

	/**
	 * Condition indicating that no more entries may be added to the
	 * {@link #buffer} as it has reached its maximum {@link #capacity}.
	 */
	private final Condition notEmpty = lock.newCondition();

	/** The bounded buffer. */
	@GuardedBy("lock")
	private final String[] buffer;
	/** Maximum number of {@link #buffer} entries. */
	private final int capacity;
	/** Actual number of {@link #buffer} entries. */
	private int count;

	private int front;
	private int rear;

	public BoundedBuffer(int capacity) {
		super();

		this.capacity = capacity;

		buffer = new String[capacity];
	}

	public void push(String data) throws InterruptedException {
		lock.lock();

		try {
			while (count == capacity)
				notFull.await();

			buffer[rear] = data;
			rear = (rear + 1) % capacity;
			count++;

			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	public String pop() throws InterruptedException {
		lock.lock();

		try {
			while (count == 0)
				notEmpty.await();

			String result = buffer[front];
			front = (front + 1) % capacity;
			count--;

			notFull.signal();

			return result;
		} finally {
			lock.unlock();
		}
	}
}