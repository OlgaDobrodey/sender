package org.dobrodey.sender.listener;

import org.dobrodey.sender.service.ReportCreatorService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@WebListener
public class SenderJobManager implements ServletContextListener {

    private ScheduledExecutorService scheduler;
    private final int TARGET_HOUR = 22;
    private final int TARGET_MIN = 0;
    private final int TARGET_SECOND = 0;
    private final long PERIOD = 24*3600; // the period between successive executions - 1 day in seconds
    private final long PERIOD_TEST = 20; // the period between successive executions - 1 day in seconds

//    @Override
//    public void contextInitialized(ServletContextEvent event) {
//        long initialDelay = getSecond(TARGET_HOUR, TARGET_MIN, TARGET_SECOND);
//        scheduler = Executors.newSingleThreadScheduledExecutor();
//        scheduler.scheduleAtFixedRate(new ReportCreatorService(), initialDelay, PERIOD, TimeUnit.SECONDS);
//    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new ReportCreatorService(), 0, PERIOD_TEST, TimeUnit.SECONDS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        scheduler.shutdownNow();
    }

    private static long  getSecond(int targetHour, int targetMin, int targetSec){
        LocalDateTime localNow = LocalDateTime.now();
        ZoneId currentZone = ZoneId.systemDefault();
        ZonedDateTime zonedNow = ZonedDateTime.of(localNow, currentZone);
        ZonedDateTime zonedNextTarget = zonedNow.withHour(targetHour).withMinute(targetMin).withSecond(targetSec);
        if(zonedNow.compareTo(zonedNextTarget) > 0)
            zonedNextTarget = zonedNextTarget.plusDays(1);

        Duration duration = Duration.between(zonedNow, zonedNextTarget);
        return duration.getSeconds();
    }

}
