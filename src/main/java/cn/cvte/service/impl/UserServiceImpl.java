package cn.cvte.service.impl;

import cn.cvte.dao.mapper.UserScoreDao;
import cn.cvte.dto.ResponseDto;
import cn.cvte.entity.UserScore;
import cn.cvte.service.UserService;
import cn.cvte.util.VarifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserScoreDao userScoreDao;


    public String login(String phone) {
        String uid = VarifyUtil.getMD5(phone);
        UserScore userScore = userScoreDao.getUserByUid(uid);
        if (userScore == null) {
            int insertCount = userScoreDao.insert(new UserScore(uid, phone, 0));
        }
        return uid;
    }


    /*
        查看积分
     */
    public ResponseDto getScore(String uid) {
        UserScore userScore = userScoreDao.getUserByUid(uid);
        if (userScore != null) {
            return ResponseDto.success(userScore.getScore());
        }
        return ResponseDto.fail();
    }

    public String setToken(String uid) {
        return VarifyUtil.getMD5(uid);
    }
}
