package cn.cvte.dao.mapper;

import cn.cvte.entity.UserScore;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserScoreDao {

    @Insert("insert into user_score(uid, phone, score,createTime,updateTime) values(#{uid},#{phone},#{score},#{createTime},#{updateTime})")
    public int insert(UserScore userScore);

    @Select("select * from user_score where uid=#{uid}")
    public UserScore getUserByUid(String uid);

    @Update("update user_score set score=#{score},updateTime=#{updateTime} where uid=#{uid} for update")
    public int updateByUid(UserScore userScore);

}
