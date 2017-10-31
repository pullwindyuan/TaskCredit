package cn.cvte.dao.mapper;

import cn.cvte.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserDao {

    @Insert("insert ")
    public int inert(User user);

    @Select("select ")
    public User getUserByUid(String uid);

    @Update("update ")
    public int update(User user);

}
