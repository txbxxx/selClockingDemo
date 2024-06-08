package fun.tanc.selfclocking.control;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import fun.tanc.selfclocking.model.CountDown;
import fun.tanc.selfclocking.service.CountDownServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class CountDownControl {
    @Autowired
    CountDownServiceImpl countDownService;

    //添加倒计时
    @PostMapping(value = "/addCountDown")
    public SaResult addCountDown(@RequestBody Map<String, String> map) {
        String userName = StpUtil.getLoginId().toString();
        String countdownName = map.get("countdownName");
        Long countdownDay = Long.parseLong(map.get("countdownDay"));
        Boolean b = countDownService.addCountDown(userName, countdownName, countdownDay);
        if (b){
            return SaResult.ok("添加成功");
        }else {
            return SaResult.error("添加失败");
        }
    }


    //删除倒计时
    @DeleteMapping(value = "/deleteCountDown")
    public SaResult deleteCountDown(@RequestBody Map<String, String> map) {
        String userName = StpUtil.getLoginId().toString();
        String countdownName = map.get("countdownName");
        Boolean b = countDownService.deleteCountDown(userName, countdownName);
        if (b){
            return SaResult.ok("删除成功");
        }else {
            return SaResult.error("删除失败");
        }
    }


    //列出倒计时
    @RequestMapping(value = "/listCountDown",method = RequestMethod.GET)
    public SaResult listCountDown() {
        String userName = StpUtil.getLoginId().toString();
        List<CountDown> data = countDownService.finderCountDown(userName);
        if (data != null) {
            return SaResult.data(data);
        }
        return SaResult.error("列出失败");
    }

    //列出未完成的倒计时
    @RequestMapping(value = "/listCountDownOverFalse",method = RequestMethod.GET)
    public SaResult listCountDownOverFalse() {
        String userName = StpUtil.getLoginId().toString();
        List<CountDown> data = countDownService.finderCountDownOverFalse(userName);
        if (data != null) {
            return SaResult.data(data);
        }
        return SaResult.error("列出失败");
    }

    //列出完成的倒计时
    @RequestMapping(value = "/listCountDownOverTrue",method = RequestMethod.GET)
    public SaResult listCountDownOverTrue() {
        String userName = StpUtil.getLoginId().toString();
        List<CountDown> data = countDownService.finderCountDownOverTrue(userName);
        if (data != null) {
            return SaResult.data(data);
        }
        return SaResult.error("列出失败");
    }

    //修改倒计时
    @PutMapping(value = "/updateCountDown")
    public SaResult updateCountDown(@RequestBody Map<String, String> map) {
        String userName = StpUtil.getLoginId().toString();
        String countdownName = map.get("countdownName");
        Long countdownDay = Long.parseLong(map.get("countdownDay"));
        Boolean b = countDownService.updateCountDown(userName, countdownName, countdownDay);
        if (b){
            return SaResult.ok("修改成功");
        }else {
            return SaResult.error("修改失败");
        }
    }

    //更新倒计时剩余天数
    @GetMapping(value = "/updateCountDownPastDay")
    public SaResult updateCountDownPastDay() throws ParseException {
        String userName = StpUtil.getLoginId().toString();
        Boolean b = countDownService.updateCountDownPastDay(userName);
        if (b){
            return SaResult.ok("修改成功");
        }else {
            return SaResult.error("修改失败");
        }
    }
}
