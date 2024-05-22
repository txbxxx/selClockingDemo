package fun.tanc.selfclocking.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import fun.tanc.selfclocking.dao.RelationshipDao;
import fun.tanc.selfclocking.dao.UserDao;
import fun.tanc.selfclocking.model.Relationship;
import fun.tanc.selfclocking.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDateTime;

@Service
public class RelationServiceImpl {
    @Autowired
    private RelationshipDao relationshipDao;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserDao userDao;


    //添加关系
    public Boolean addRelation(String userName,String friendName,String relationship){
        //检查用户是否存在
        UserModel user1 = userService.findUser(userName);
        UserModel user2 = userService.findUser(friendName);
        if(user1 ==null|| user2 ==null){
            System.out.println("用户不存在");
            return false;
        }

        //添加关系

        //判断关系是否存在
        Relationship re = relationshipDao.selectOne(new QueryWrapper<Relationship>().eq("user_one", user1.getId()).eq("user_two", user2.getId()));
        if(re != null){
            System.out.println("关系已存在");
            return false;
        }

        //添加关系
        int insert = relationshipDao.insert(new Relationship(user1.getId(), user2.getId(), relationship, LocalDateTime.now()));
        if(insert <= 0){
            System.out.println("添加失败");
            return false;
        }

        return true;
    }

    //解除关系
    public Boolean deleteRelation(String userName,String friendName){
        UserModel user1 = userService.findUser(userName);
        UserModel user2 = userService.findUser(friendName);
        Relationship relationship = relationshipDao.selectOne(new QueryWrapper<Relationship>().eq("user_one", user1.getId()).eq("user_two", user2.getId()));
        //删除关系
        relationshipDao.delete(new QueryWrapper<Relationship>().eq("r_id",relationship.getId()));

        return true;
    }


    //查找用户存在的关系
    public Relationship findRelation(String userName){
        UserModel user1 = userService.findUser(userName);
        return relationshipDao.selectOne(new QueryWrapper<Relationship>().eq("user_one", user1.getId()));
    }

}
