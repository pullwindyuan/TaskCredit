package cn.cvte.service.impl;

import cn.cvte.dao.mapper.UserScoreDao;
import cn.cvte.dto.ResponseDto;
import cn.cvte.entity.UserScore;
import cn.cvte.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserScoreDao userScoreDao;

    private static final String KEY = "qwer#$%wqet@52345";

    public String login(String phone) {
        String uid = getMD5(phone);
        UserScore userScore = userScoreDao.getUserByUid(uid);
        if (userScore == null) {
            int insertCount = userScoreDao.insert(new UserScore(uid, phone, 0));
        }
        return uid;
    }

    private String getMD5(String phone){
        String base = phone + KEY;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
