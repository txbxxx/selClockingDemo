package fun.tanc.selfclocking.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import fun.tanc.selfclocking.dao.UserDao;
import fun.tanc.selfclocking.dao.UserTaskDao;
import fun.tanc.selfclocking.model.Schedule;
import fun.tanc.selfclocking.model.UserModel;
import fun.tanc.selfclocking.model.UserTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserTaskServiceImpl {
    @Autowired
    UserServiceImpl usImpl;
    @Autowired
    UserTaskDao userTaskDao;

    //添加任务字段
    @Transactional(rollbackFor = Exception.class)
    public Boolean addUserTask(String userName,String taskName,int taskLevel,String taskStr){
        //查找用户是否存在
        UserModel userModel = usImpl.findUser(userName);
        if (userModel == null) {
            System.out.println("用户不存在");
            return false;
        }
        //获取用户id
        long userID = userModel.getId();

        //查找此任务是否已经存在(依照任务名)
        if (userTaskDao.selectOne(new QueryWrapper<UserTask>().eq("user_id",userID ).eq("task_name", taskName)) != null) {
            System.out.println("任务已存在");
            return false;
        }

        //更新用户task字段
        int insert = userTaskDao.insert(new UserTask(userID, taskName,taskLevel, taskStr,0));
        if (insert < 0) {
            System.out.println("添加失败");
            return false;
        }

        return true;
    }

    //删除任务字段
    public Boolean deleteUserTask(String userName,String taskName){
        UserModel userModel = usImpl.findUser(userName);
        int delete = userTaskDao.delete(new QueryWrapper<UserTask>().eq("user_id", userModel.getId()).eq("task_name", taskName));
        if (delete <= 0) {
            System.out.println("删除失败");
            return false;
        }
        return true;
    }

    //列出所有任务字段
    public List<UserTask> finderUserTask(String username){
        UserModel userModel = usImpl.findUser(username);
        List<UserTask> userTaskList = userTaskDao.selectList(new QueryWrapper<UserTask>().eq("user_id", userModel.getId()));
        if(userTaskList.isEmpty()){
            return null;
        }
        return userTaskList;
    }

    //查询任务字段(模糊)
    public List<UserTask> findUserTask(String userName,String taskFiled){
        UserModel userModel = usImpl.findUser(userName);
        return userTaskDao.selectList(new QueryWrapper<UserTask>().eq("user_id", userModel.getId()).like("task_name", taskFiled).like("task_field", taskFiled));
    }

    //修改任务字段
    public Boolean updateUserTask(String userName,String taskName,int taskLevel,String taskStr,String oldTaskName){
        System.out.println("老任务名: "+oldTaskName);
        UserModel userModel = usImpl.findUser(userName);
        UserTask userTask = userTaskDao.selectOne(new QueryWrapper<UserTask>().eq("user_id", userModel.getId()).eq("task_name", oldTaskName));
        if  (userTask == null) {
            System.out.println("任务不存在");
            return false;
        }
        int update = userTaskDao.update(new UserTask(userModel.getId(), taskName, taskLevel,taskStr,0), new UpdateWrapper<UserTask>().eq("user_id", userModel.getId()).eq("task_id", userTask.getTaskId()));
        if (update < 0) {
            System.out.println("修改失败");
            return false;
        }
        return true;
    }

    //更新任务完成没完成
    public Boolean updateUserTaskOver(String userName,String taskName,int isOver){
        UserModel userModel = usImpl.findUser(userName);
        UserTask userTask = userTaskDao.selectOne(new QueryWrapper<UserTask>().eq("user_id", userModel.getId()).eq("task_name", taskName));
        if  (userTask == null) {
            System.out.println("任务不存在");
            return null;
        }
        userTask.setIsOver(isOver);
        int update = userTaskDao.updateById(userTask);
        if (update < 0) {
            System.out.println("修改失败");
            return false;
        }
        return true;
    }
}
