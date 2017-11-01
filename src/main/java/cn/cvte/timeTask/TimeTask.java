package cn.cvte.timeTask;

import cn.cvte.dao.mapper.TaskRecordDao;
import cn.cvte.entity.TaskModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TimeTask {

    private static Logger logger = LoggerFactory.getLogger(TimeTask.class);

    @Autowired
    private TaskRecordDao taskRecordDao;

    @Scheduled(cron = "0 0 * * *")
    public void resetDailyTask () {
        logger.info("每天重置数据");
        taskRecordDao.resetRecord(TaskModel.TYPE_DAY, new Date());
    }

    @Scheduled(cron = "0 0 * * 0")
    public void resetWeekTask () {
        logger.info("每周重置数据");
        taskRecordDao.resetRecord(TaskModel.TYPE_WEEK, new Date());
    }

    @Scheduled(cron = "0 0 1 * *")
    public void resetMonthTask () {
        logger.info("每月重置数据");
        taskRecordDao.resetRecord(TaskModel.TYPE_MONTH, new Date());
    }
}
