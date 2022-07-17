package control.cron;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitializeListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		CronJob cronJob = new CronJob();
		cronJob.startScheduler();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}