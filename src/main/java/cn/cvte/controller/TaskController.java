package cn.cvte.controller;

import cn.cvte.dto.ResponseDto;
import cn.cvte.enums.ResultCode;
import cn.cvte.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/receive",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseDto receiveTask(String uid, int tid) {
        return taskService.receiveTask(uid, tid);
    }


    @RequestMapping(value = "/{tid}/finish/",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseDto doTask(String uid, int tid) {
        return taskService.doTask(uid, tid);
    }

    @RequestMapping(value = "/{tid}/detail",
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseDto getTaskDetail(String uid, int tid) {
        return taskService.getTaskDetail(uid, tid);
    }

    @RequestMapping(value = "/list",
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseDto getTaskList(String uid, int tid) {
        return taskService.getTaskDetail(uid, tid);
    }
}
