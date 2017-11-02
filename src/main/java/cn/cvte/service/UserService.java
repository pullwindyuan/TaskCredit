package cn.cvte.service;

import cn.cvte.dto.ResponseDto;

public interface UserService {
    String login(String phone);
    ResponseDto getScore(String uid);
}
