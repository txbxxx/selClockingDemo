package fun.tanc.selfclocking.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import fun.tanc.selfclocking.dao.UserDao;
import fun.tanc.selfclocking.dao.UserTaskDao;
import fun.tanc.selfclocking.model.UserModel;
import fun.tanc.selfclocking.model.UserTask;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class) //回滚
public class UserServiceImpl {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserTaskDao userTaskDao;

    //创建用户
    @Transactional(rollbackFor = Exception.class)
    public Boolean addUser(String name,String password)
    {
        //检查用户是否存在
        UserModel user = findUser(name);
        if (user != null){
            System.out.println("当前用户已经存在！！");
            return false;
        }

        //生成UUID
        long id = 0;
        //如果生成的id为0或者小于0就在生成一次
        while (id <= 0) {
            id = UUID.randomUUID().hashCode();
        }

        //插入用户
        int insert = userDao.insert(new UserModel(id, name, password));
        if (insert < 0){
            return false;
        }

        return true;
    }


    //使用用户名查找用户(精确)
    public UserModel findUser(String name)
    {
        //查询用户
        UserModel n = userDao.selectOne(new QueryWrapper<UserModel>().eq("name", name));
//        System.out.println(n);
        return n;
    }

    //使用用户名查找用户(模糊)
    public List<UserModel> finderUserName(String name)
    {
        //查询用户
        List<UserModel> n = userDao.selectList(new QueryWrapper<UserModel>().like("name", name));
        n.forEach(System.out::println);
        return n;
    }

    //使用用户名查找用户(精确)
    public UserModel findIdUser(String id)
    {
        //查询用户
        UserModel n = userDao.selectOne(new QueryWrapper<UserModel>().eq("id", id));
        return n;
    }


    //删除用户
    public boolean deleteUser(String name){
        //查询用户
        UserModel user = findUser(name);
        //如果用户不存在则返回false
        if(user == null) return false;

        //获取用户id，根据id删除用户
        Long id = user.getId();
        System.out.println(id);
        userDao.deleteById(id);

        return true;
    }
}
