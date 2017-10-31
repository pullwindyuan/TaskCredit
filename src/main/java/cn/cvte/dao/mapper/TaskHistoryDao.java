package cn.cvte.dao.mapper;

import cn.cvte.entity.TaskHistory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TaskHistoryDao {

    @Select("select * from task_history where uid=#{uid}")
    public List<TaskHistory> getHistoryByUid(String uid);

    @Insert("insert into task_history(uid,tid,step,score,createTime,description)" +
            " value(#{uid},#{tid},#{step},#{score},#{createTime},#{desc})")
    @Options(useGeneratedKeys = true)
    public int insert(TaskHistory taskHistory);

}
