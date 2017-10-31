package cn.cvte.dao.mapper;

import cn.cvte.entity.TaskModel;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TaskModelDao {

    @Select("select * from task;")
    public List<TaskModel> getAll();

    @Select("select * from task where tid=#{tid};")
    public TaskModel getByTid(int tid);

}
