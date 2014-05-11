package whiteboard;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import javafx.application.Platform;

final class JavafxPlatformUtils {
	public static void runAndWait(Runnable runnable)
			throws InterruptedException, ExecutionException {
		FutureTask<Void> future = new FutureTask<>(runnable, null);
		Platform.runLater(future);
		future.get();
	}

	public static <T> T runAndWait(Callable<? extends T> callable)
			throws InterruptedException, ExecutionException {
		FutureTask<? extends T> future = new FutureTask<>(callable);
		Platform.runLater(future);
		return future.get();
	}
}
