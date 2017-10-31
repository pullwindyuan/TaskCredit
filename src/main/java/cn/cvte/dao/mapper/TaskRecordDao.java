package cn.cvte.dao.mapper;

import cn.cvte.entity.TaskRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TaskRecordDao {

    @Insert("insert ")
    public int insert();

    @Select("select ")
    public List<TaskRecord> getRecordByUid(String uid);

    @Update("update")
    public int updateRecord();

}
