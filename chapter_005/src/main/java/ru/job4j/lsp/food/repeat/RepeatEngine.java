package ru.job4j.lsp.food.repeat;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;

/**
 * @author Geraskin Egor
 * @version 1.0
 * @since 21.12.2020
 */
public final class RepeatEngine implements Repeatable {
    private final SimpleRepeatable toRepeat;
    private Scheduler scheduler;
    private final JobDataMap dataMap = new JobDataMap();
    private final JobDetail job;
    private boolean nowRepeating = false;

    public RepeatEngine(SimpleRepeatable toRepeat) {
        this.toRepeat = toRepeat;
        dataMap.put("repeat", this);
        job = JobBuilder.newJob(RepeatJob.class).setJobData(dataMap).build();
    }

    @Override
    public final void repeatForever(int intervalInSeconds) {
        if (nowRepeating) {
            throw new IllegalCallerException("Repeated now. Can't start new repeat thread.");
        }
        nowRepeating = true;
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            SimpleScheduleBuilder times = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(intervalInSeconds)
                .repeatForever();
            Trigger trigger = TriggerBuilder.newTrigger().startNow()
                .withSchedule(times).build();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void repeat() {
        toRepeat.repeat();
    }

    @Override
    public final void close() {
        try {
            scheduler.shutdown();
            nowRepeating = false;
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public static class RepeatJob implements Job {
        @Override
        public void execute(JobExecutionContext jobExecutionContext) {
            SimpleRepeatable repeatable = (SimpleRepeatable) jobExecutionContext.getJobDetail()
                    .getJobDataMap().get("repeat");
            repeatable.repeat();
        }
    }
}
