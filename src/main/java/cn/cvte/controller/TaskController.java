package cn.cvte.controller;

import cn.cvte.dto.ResponseDto;
import cn.cvte.enums.ResultCode;
import cn.cvte.service.TaskService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 内部使用/api/task/
 * 外部使用/application/task
 */
@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/{tid}/receive",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseDto receiveTask(@RequestParam("uid") String uid,
                                   @PathVariable("tid") int tid) {
        return taskService.receiveTask(uid, tid);
    }


    @RequestMapping(value = "/{tid}/finish",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseDto doTask(@RequestParam("uid") String uid,
                              @PathVariable("tid") int tid) {
        return taskService.doTask(uid, tid);
    }

    @RequestMapping(value = "/{tid}/detail",
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseDto getTaskDetail(@CookieValue("uid") String uid,
                                     @PathVariable("tid")int tid) {
        return taskService.getTaskDetail(uid, tid);
    }

    @RequestMapping(value = "/list",
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseDto getTaskList(String uid) {
        return taskService.getTaskList(uid);
    }
}
