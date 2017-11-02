package cn.cvte.controller;

import cn.cvte.dao.mapper.UserScoreDao;
import cn.cvte.dto.ResponseDto;
import cn.cvte.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseDto login(@Param("phone")String phone,
                             HttpServletRequest request, HttpServletResponse response ){

        try {
            Cookie cookie = new Cookie("uid", userService.login(phone));//创建新cookie
            cookie.setMaxAge(5 * 60);// 设置存在时间为5分钟
            cookie.setPath("/");//设置作用域
            response.addCookie(cookie);//将cookie添加到response的cookie数组中返回给客户端
        } catch (Exception e) {
            ResponseDto.fail();
        }

        return ResponseDto.success();
    }

    @RequestMapping(value = "/score",
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseDto getScore(String uid){
        return userService.getScore(uid);
    }


}
