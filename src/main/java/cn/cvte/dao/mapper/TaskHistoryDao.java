package cn.cvte.dao.mapper;

import cn.cvte.entity.TaskHistory;

import java.util.List;

public interface TaskHistoryDao {

    public List<TaskHistory> getHistoryByUid(String uid);

    public int insert();

}
