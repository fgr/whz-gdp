package bankirrtum.falsch;

public class Bankirrtum {
	public static void main(String[] args) {
		final Account a = new Account(29);

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				a.withdraw(10);
				a.withdraw(10);
			}
		});
		t1.setName("A");

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				a.withdraw(10);
			}
		});
		t2.setName("B");

		t1.start();
		t2.start();
	};
}
