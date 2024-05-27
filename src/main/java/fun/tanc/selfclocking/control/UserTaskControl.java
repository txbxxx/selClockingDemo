package fun.tanc.selfclocking.control;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import fun.tanc.selfclocking.model.UserTask;
import fun.tanc.selfclocking.service.UserTaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserTaskControl {
    @Autowired
    UserTaskServiceImpl userTaskService;


    //添加任务
    @SaCheckLogin
    @RequestMapping (value = "/addUserTask", method = RequestMethod.POST)
    @CrossOrigin
    public Boolean addUserTask(@RequestParam("taskname") String taskName,
                               @RequestParam("taskstr") String taskStr){
        String userName = StpUtil.getLoginId().toString();
        return userTaskService.addUserTask(userName,taskName,taskStr);
    }


    //删除任务
    @SaCheckLogin
    @RequestMapping(value = "/deleteUserTask",method = RequestMethod.DELETE)
    @CrossOrigin
    public Boolean deleteUserTask(@RequestParam("taskname") String taskName) {
        String userName = StpUtil.getLoginId().toString();
        return userTaskService.deleteUserTask(userName, taskName);
    }



    //查询任务
    @SaCheckLogin
    @RequestMapping(value = "/findUserTask",method = RequestMethod.GET)
    @CrossOrigin
    public List<UserTask> findUserTask(@RequestParam("taskFiled") String taskFiled) {
        String userName = StpUtil.getLoginId().toString();
        List<UserTask> userTask = userTaskService.findUserTask(userName,taskFiled);
        userTask.forEach(x -> System.out.println(x.getTaskField()));
        return userTask;
    }



    //列出所有任务
    @SaCheckLogin
    @RequestMapping(value = "/finderUserTask",method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:8080")
    public List<UserTask> finderUserTask() {
        String userName = StpUtil.getLoginId().toString();
        List<UserTask> userTask = userTaskService.finderUserTask(userName);
        userTask.forEach(x -> System.out.println(x.getTaskField()));
        return userTask;
    }
}
