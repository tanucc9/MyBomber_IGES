package control.cron;

import java.util.Timer;
import java.util.TimerTask;

public class CronJob {
	private static final int PERIOD_MINUTES_DURATION = 10;
	private final Timer timer = new Timer();
	private EventStateUpdater eventStateUpdater;

	public void startScheduler() {
		eventStateUpdater = new EventStateUpdater();
		timer.schedule(new TimerTask() {
			public void run() {
				long nowMillis = System.currentTimeMillis();
				eventStateUpdater.updateEventStateByDateTime(nowMillis);
				// timer.cancel();
			}
		}, 0, PERIOD_MINUTES_DURATION * 60 * 1000);
	}
}
