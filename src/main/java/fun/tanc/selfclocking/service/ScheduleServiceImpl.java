package fun.tanc.selfclocking.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import fun.tanc.selfclocking.dao.ScheduleDao;
import fun.tanc.selfclocking.model.Schedule;
import fun.tanc.selfclocking.model.UserModel;
import fun.tanc.selfclocking.model.UserTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl {

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    UserServiceImpl usImpl;


    //添加日程
    public Boolean addSchedule(String userName,String scheduleFiled,String endDate,String date,String starTime,String endTime)
    {
        System.out.println(userName);
        System.out.println(scheduleFiled);
        System.out.println(date);
        Long userId = usImpl.findUser(userName).getId();
        int insert = scheduleDao.insert(new Schedule(userId, null, scheduleFiled, endDate,date, starTime, endTime));
        return insert >= 0;
    }
    
    //查询此用户的单个日程(修改可以匹配名字和时间和日期)
    public Schedule findSchedule(String scheduleFiled,String userName){
        UserModel user = usImpl.findUser(userName);
        Schedule s = scheduleDao.selectOne(new QueryWrapper<Schedule>().eq("user_id", user.getId())
                .like("schedule_filed", scheduleFiled)
                .or()
                .like("date",scheduleFiled)
                .or()
                .like("end_date",scheduleFiled)
                .or()
                .like("start_time",scheduleFiled)
                .or()
                .like("end_time",scheduleFiled));
        if (s != null){
            return s;
        }else{
            return null;
        }
        
    }
    
    //查询日程（模糊）
    public List<Schedule> finderSchedule(String scheduleFiled,String userName){
        UserModel user = usImpl.findUser(userName);
        List<Schedule> schedules = scheduleDao.selectList(new QueryWrapper<Schedule>().eq("user_id", user.getId())
                .like("schedule_filed", scheduleFiled)
                .or()
                .like("date",scheduleFiled)
                .or()
                .like("end_date",scheduleFiled)
                .or()
                .like("start_time",scheduleFiled)
                .or()
                .like("end_time",scheduleFiled));

        return schedules;

    }
    
    //查询此用户所有日程
    public List<Schedule> findAllSchedule(String userName){
        UserModel u = usImpl.findUser(userName);
        if (u == null){
            return null;
        }
        List<Schedule> schedule = scheduleDao.selectList(new QueryWrapper<Schedule>().eq("user_id", u.getId()));
        if (!schedule.isEmpty()){
            return schedule;
        }else{
            return null;
        }
    }


    //删除日程
    public Boolean deleteSchedule(String scheduleFiled,String userName,String date){
        UserModel user = usImpl.findUser(userName);
        int delete = scheduleDao.delete(new QueryWrapper<Schedule>().eq("user_id", user.getId()).eq("schedule_filed", scheduleFiled).eq("date", date));
        return delete >= 0;
    }

}


