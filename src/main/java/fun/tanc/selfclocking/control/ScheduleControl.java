package fun.tanc.selfclocking.control;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import fun.tanc.selfclocking.model.Schedule;
import fun.tanc.selfclocking.service.ScheduleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin
public class ScheduleControl {
    @Autowired
    ScheduleServiceImpl scheduleService;


    //添加日程
    @PostMapping(value = "/addSchedule")
    public SaResult addSchedule(@RequestBody Map<String,String> map)
    {
        String userName = StpUtil.getLoginId().toString();
        String scheduleFiled = map.get("scheduleFiled");
        String starTime = map.get("starTime");
        String endTime = map.get("endTime");
        String endDate = map.get("endDate");



        if (scheduleFiled == null){
            return SaResult.error("日程不能为空");
        }
        String date = map.get("date");
        Boolean b = scheduleService.addSchedule(userName, scheduleFiled,endDate, date, starTime, endTime);
        if (b){
            return SaResult.ok("添加成功").setData(true);
        }else {
            return SaResult.error("添加失败");
        }
    }

    //列出所有日程
    @GetMapping(value = "/findAllSchedule")
    public SaResult findAllSchedule()
    {
        String userName = StpUtil.getLoginId().toString();
        List<Schedule> allSchedule = scheduleService.findAllSchedule(userName);
        if (allSchedule != null) {
            return SaResult.data(allSchedule);
        }
        return SaResult.ok("此用户没有日程");
    }


    //查询日程
    @PostMapping(value = "/findSchedule")
    public SaResult findSchedule(@RequestBody Map<String,String> map)
    {
        String scheduleFiled = map.get("scheduleFiled");
        String userName = map.get("userName");
        Schedule schedule = scheduleService.findSchedule(scheduleFiled, userName);
        if (schedule != null) {
            return SaResult.data(schedule);
        }
        return SaResult.ok("此用户没有此日程");
    }

    //查询日程（模糊）
    @PostMapping(value = "/fuzzySchedule")
    public SaResult fuzzySchedule(@RequestBody Map<String,String> map)
    {
        String scheduleFiled = map.get("scheduleFiled");
        String userName = StpUtil.getLoginId().toString();
        List<Schedule> schedule = scheduleService.finderSchedule(scheduleFiled, userName);
        if (schedule != null) {
            return SaResult.data(schedule);
        }
        return SaResult.ok("此用户没有此日程");
    }

    //删除日程
    @DeleteMapping(value = "/deleteSchedule")
    public SaResult deleteSchedule(@RequestBody Map<String,String> map)
    {
        String scheduleFiled = map.get("scheduleFiled");
        String username = StpUtil.getLoginId().toString();
        String date = map.get("date");
        Boolean b = scheduleService.deleteSchedule(scheduleFiled, username,date);
        if (b) {
            return SaResult.ok("删除成功");
        }
        return SaResult.error("删除失败");
    }
}
