package fun.tanc.selfclocking.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import fun.tanc.selfclocking.dao.RelationshipDao;
import fun.tanc.selfclocking.model.Relationship;
import fun.tanc.selfclocking.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class RelationServiceImpl {
    @Autowired
    private RelationshipDao relationshipDao;

    @Autowired
    private UserServiceImpl userService;




    //添加关系
    public Boolean addRelation(String userName,String friendName,String relationship){
        //检查用户是否存在
        UserModel user1 = userService.findUser(userName);
        UserModel user2 = userService.findUser(friendName);
        if(user1 ==null|| user2 ==null){
            System.out.println("用户不存在");
            return false;
        }
        // 获取当前时间的毫秒数
        long currentTimeMillis = System.currentTimeMillis();

        // 将毫秒数转换为日期对象
        Date date = new Date(currentTimeMillis);

        // 定义日期格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // 格式化日期,获取当前时间
        String StartDay = dateFormat.format(date);


        //添加关系

        //判断关系是否存在
        Relationship re = relationshipDao.selectOne(new QueryWrapper<Relationship>().eq("user_one", user1.getName()).eq("user_two", user2.getName()));
        if(re != null){
            System.out.println("关系已存在");
            return false;
        }

        //添加关系
        int insert = relationshipDao.insert(new Relationship(user1.getId(), user2.getId(), relationship, 1,StartDay));
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

    //列出和用户绑定的用户
    public UserModel listRelation(String userName){
        UserModel user1 = userService.findUser(userName);
        if (user1 == null) {
            return null;
        }
        Relationship relationship = relationshipDao.selectOne(new QueryWrapper<Relationship>().eq("user_one", user1.getId()).or().eq("user_two", user1.getId()));
        if (user1.getId() == relationship.getUserOneId()) {
           return userService.findIdUser(String.valueOf(relationship.getUserTwoId()));
        } else if (user1.getId() == relationship.getUserTwoId()) {
          return   userService.findIdUser(String.valueOf(relationship.getUserOneId()));
        }
        return null;
    }


    //查找用户存在的关系
    public Relationship findRelation(String userName){
        UserModel user1 = userService.findUser(userName);
        if (user1 == null) {
            return null;
        }
        System.out.println(user1.getId());
        Relationship userOne = relationshipDao.selectOne(new QueryWrapper<Relationship>().eq("user_one", user1.getId()).or().eq("user_two", user1.getId()));
        if (userOne == null){
            return null;
        }else {
            System.out.println(userOne);
            return userOne;
        }
    }


    //更新绑定天数
    public Boolean updateRelationDay(String userName) throws ParseException {
        //先找到关系
        Relationship relation = findRelation(userName);
        //获取今天的时间
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        //将日期开始日期解析为date
        String Start = relation.getStartDay();
        Date StartDay = dateFormat.parse(Start);

        // 计算日期差
        long diffInMillies = Math.abs(date.getTime() - StartDay.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        relation.setDay((int)diff);

        //更新
        int relationship = relationshipDao.update(relation, new QueryWrapper<Relationship>().eq("user_one", relation.getUserOneId()).eq("user_two", relation.getUserTwoId()));
        if(relationship > 0){
            return  true;
        }
        return  false;
    }
}
