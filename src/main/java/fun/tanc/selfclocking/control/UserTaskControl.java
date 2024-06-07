package fun.tanc.selfclocking.control;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import fun.tanc.selfclocking.model.UserTask;
import fun.tanc.selfclocking.service.UserTaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class UserTaskControl {
    @Autowired
    UserTaskServiceImpl userTaskService;


    //添加任务
    @SaCheckLogin
    @RequestMapping (value = "/addUserTask", method = RequestMethod.POST)
    @CrossOrigin
    public SaResult addUserTask(@RequestBody Map<String,String> map){
        String taskName = map.get("taskname");
        String taskStr = map.get("taskstr");
        int taskLevel = Integer.parseInt(map.get("tasklevel"));
        String userName = StpUtil.getLoginId().toString();
        Boolean b = userTaskService.addUserTask(userName, taskName,taskLevel,taskStr);
        if (b){
            return SaResult.ok("添加成功");
        }else {
            return SaResult.error("添加失败");
        }
    }


    //删除任务
    @SaCheckLogin
    @DeleteMapping(value = "/deleteUserTask")
    @CrossOrigin
    public SaResult deleteUserTask(@RequestBody Map<String,String> map) {
        String taskName = map.get("taskname");
        String userName = StpUtil.getLoginId().toString();
        Boolean b = userTaskService.deleteUserTask(userName, taskName);
        System.out.println(b);
        if (b){
            return SaResult.ok("删除成功");
        }else {
            return SaResult.error("删除失败");
        }
    }



    //查询任务
    @SaCheckLogin
    @RequestMapping(value = "/searchTask",method = RequestMethod.GET)
    @CrossOrigin
    public List<UserTask> findUserTask(@RequestBody Map<String,String> map) {
        String taskName = map.get("taskname");
        String taskFiled = map.get("taskfiled");
        String userName = StpUtil.getLoginId().toString();
        List<UserTask> userTask = userTaskService.findUserTask(userName,taskName,taskFiled);
        return userTask;
    }



    //列出所有任务
    @SaCheckLogin
    @RequestMapping(value = "/listUserTask",method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:8080")
    public SaResult finderUserTask() {
        String userName = StpUtil.getLoginId().toString();
        List<UserTask> userTask = userTaskService.finderUserTask(userName);
        if (userTask == null){
            return  SaResult.ok("没有任务");
        }
        return SaResult.data(userTask);
    }


    //修改任务内容
    @RequestMapping(value = "/updateTask",method = RequestMethod.PUT)
    @CrossOrigin
    public SaResult updateUserTask(@RequestBody Map<String,String> map) {
        String taskName = map.get("taskname");
        String taskStr = map.get("taskstr");
        int taskLevel = Integer.parseInt(map.get("tasklevel"));
        String oldTaskName = map.get("oldtaskname");
        String userName = StpUtil.getLoginId().toString();
        Boolean b = userTaskService.updateUserTask(userName, taskName, taskLevel,taskStr,oldTaskName);
        if (b){
            return SaResult.ok("修改成功");
        }
        return SaResult.error("修改失败没有这个字段或者");
    }

    //更新任务状态
    @RequestMapping(value = "/updateTaskStatus",method = RequestMethod.PUT)
    @CrossOrigin
    public SaResult updateTaskStatus(@RequestBody Map<String,String> map) {
        String taskName = map.get("taskname");
        int isOver = Integer.parseInt(map.get("taskstatus"));
        String userName = StpUtil.getLoginId().toString();
        Boolean b = userTaskService.updateUserTaskOver(userName, taskName, isOver);
        if (b){
            return SaResult.ok("修改成功");
        }
        return SaResult.error("修改失败没有这个字段或者");
    }

}
