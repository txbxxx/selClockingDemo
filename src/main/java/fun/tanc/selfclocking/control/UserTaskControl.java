package fun.tanc.selfclocking.control;

import fun.tanc.selfclocking.model.UserTask;
import fun.tanc.selfclocking.service.UserTaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserTaskControl {
    @Autowired
    UserTaskServiceImpl userTaskService;


    //添加任务
    @RequestMapping (value = "/addUserTask", method = RequestMethod.POST)
    public Boolean addUserTask(@RequestParam("username") String userName,
                               @RequestParam("taskname") String taskName,
                               @RequestParam("taskstr") String taskStr){
        return userTaskService.addUserTask(userName,taskName,taskStr);
    }


    //删除任务
    @RequestMapping(value = "/deleteUserTask",method = RequestMethod.DELETE)
    public void deleteUserTask(@RequestParam("username") String userName,
                               @RequestParam("taskname") String taskName) {
        userTaskService.deleteUserTask(userName,taskName);
    }


    //查询任务
    @RequestMapping(value = "/findUserTask",method = RequestMethod.GET)
    public List<UserTask> findUserTask(@RequestParam("username") String userName) {
        List<UserTask> userTask = userTaskService.findUserTask(userName);
        userTask.forEach(x -> System.out.println(x.getTaskField()));
        return userTask;
    }

    //列出所有任务
    @RequestMapping(value = "/finderUserTask",method = RequestMethod.GET)
    public List<UserTask> finderUserTask(@RequestParam("username") String userName,
                                         @RequestParam("taskname") String taskName) {
        List<UserTask> userTask = userTaskService.finderUserTask(userName,taskName);
        userTask.forEach(x -> System.out.println(x.getTaskField()));
        return userTask;
    }
}
