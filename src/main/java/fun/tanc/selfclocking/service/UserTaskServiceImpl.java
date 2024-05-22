package fun.tanc.selfclocking.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import fun.tanc.selfclocking.dao.UserDao;
import fun.tanc.selfclocking.dao.UserTaskDao;
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
    public Boolean addUserTask(String userName,String taskName,String taskStr){
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
        int insert = userTaskDao.insert(new UserTask(userID, taskName, taskStr));
        if (insert < 0) {
            System.out.println("添加失败");
            return false;
        }

        return true;
    }

    //删除任务字段
    public void deleteUserTask(String userName,String taskName){
        UserModel userModel = usImpl.findUser(userName);
        QueryWrapper<UserTask> eq = new QueryWrapper<UserTask>().eq("user_id", userModel.getId()).eq("task_name", taskName);
        int delete = userTaskDao.delete(eq);
        System.out.println(delete);
    }

    //列出所有任务字段
    public List<UserTask> findUserTask(String userName){
        UserModel userModel = usImpl.findUser(userName);
        return userTaskDao.selectList(new QueryWrapper<UserTask>().eq("user_id", userModel.getId()));
    }

    //查询任务字段(模糊)
    public List<UserTask> finderUserTask(String userName,String taskName){
        UserModel userModel = usImpl.findUser(userName);
        List<UserTask> userTasks = userTaskDao.selectList(new QueryWrapper<UserTask>().eq("user_id", userModel.getId()).like("task_name", taskName));
        userTasks.forEach(System.out::println);
        return userTasks;
    }
}
