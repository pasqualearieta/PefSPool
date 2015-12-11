package igpe.billiard.core;

public class Chronometer {

	private long elapsedTime = 0;
	private long startTime;
	private boolean isRunning;
	private long endTime;

	public Chronometer() {
		reset();
	}

	public void start() {
		if (!isRunning) {
			isRunning = true;
			startTime = System.currentTimeMillis();
		}
	}

	public void stop() {
		if (isRunning) {
			isRunning = false;
			endTime = System.currentTimeMillis();
			elapsedTime = elapsedTime + endTime - startTime;
		}
	}

	public long getElapsedTime() {
		if (isRunning) {
			endTime = System.currentTimeMillis();
			elapsedTime = elapsedTime + endTime - startTime;
			startTime = endTime;
		}
		return elapsedTime / 1000;
	}

	public void reset() {
		elapsedTime = 0;
		isRunning = false;
	}

	public void resume(long b) {
		b = b * 1000;
		elapsedTime = 0;
		startTime = System.currentTimeMillis() - b;

	}

	public long getMinute() {
		return getElapsedTime() / 60;
	}

}
