package cn.cvte.timeTask;

import cn.cvte.dao.mapper.TaskRecordDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimeTask {

    private static Logger logger = LoggerFactory.getLogger(TimeTask.class);

    @Autowired
    private TaskRecordDao taskRecordDao;

    @Scheduled(cron = "0 0 * * *")
    public void resetDailyTask () {
        logger.info("每天重置数据");
    }

    @Scheduled(cron = "0 0 * * 0")
    public void resetWeekTask () {
        logger.info("每周重置数据");
    }

    @Scheduled(cron = "0 0 1 * *")
    public void resetMonthTask () {
        logger.info("每月重置数据");
    }
}
