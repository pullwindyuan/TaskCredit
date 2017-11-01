package cn.cvte.dao.mapper;

import cn.cvte.entity.TaskRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface TaskRecordDao {

    @Insert("insert into task_record(uid,tid,history,step,score,state,createTime,updateTime)" +
            " values(#{uid},#{tid},#{history},#{step},#{score},#{state},#{createTime},#{updateTime})")
    public int insert(TaskRecord taskRecord);

    @Select("select * from task_record where uid=#{uid}")
    public List<TaskRecord> getRecordByUid(String uid);

    @Select("select * from task_record where uid=#{uid} and tid=#{tid}")
    public TaskRecord getRecordByUidAndTid(@Param("uid") String uid, @Param("tid") int tid);

    @Select("select count(*) from task_record where tid=#{tid} and state>-1")
    public int getNumByTid(@Param("tid") int tid);

    @Update("update task_record set history=#{history},step=#{step},score=#{score},state=#{state},updateTime=#{updateTime}" +
            " where uid=#{uid} and tid=#{tid}")
    public int updateRecord(TaskRecord taskRecord);

    @Update("update task_record set step=0, score=0, state=-1, updateTime=#{updateTime}" +
            " where tid in (select tid from task where type=#{type})")
    public int resetRecord(@Param("type")int type, @Param("updateTime")Date updateTime);

}
