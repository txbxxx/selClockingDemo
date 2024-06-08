package fun.tanc.selfclocking.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import fun.tanc.selfclocking.dao.CountDownDao;
import fun.tanc.selfclocking.model.CountDown;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class CountDownServiceImpl {

    @Autowired
    private CountDownDao countDownDao;

    //添加当前用户的倒计时
    public Boolean addCountDown(String userName,String countdownName,Long countdownDay)
    {
        //查询此任务是否存在
        CountDown countDown = countDownDao.selectOne(new QueryWrapper<CountDown>().eq("user_name", userName).eq("countdown_name", countdownName));
        if (Objects.nonNull(countDown))
        {
            System.out.println("任务已存在");
            return false;
        }
        if (countdownDay == 0)
        {
            System.out.println("倒计时天数不能为0");
            return false;
        }
        // 获取当前时间的毫秒数
        long currentTimeMillis = System.currentTimeMillis();

        // 将毫秒数转换为日期对象
        Date date = new Date(currentTimeMillis);

        // 定义日期格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // 格式化日期,获取当前时间
        String countdownStart = dateFormat.format(date);

        // 计算结束日期
        long endTimeMillis = currentTimeMillis + countdownDay * 24 * 60 * 60 * 1000;

        // 格式化结束日期
        String countdownEnd = dateFormat.format(new Date(endTimeMillis));


        //更新倒计时
        int insert = countDownDao.insert(new CountDown(userName, countdownName, countdownDay, 0L,0,countdownStart,countdownEnd));
        if (insert == 1)
        {
            return true;
        }
        return false;
    }


    //删除当前用户的倒计时
    public Boolean deleteCountDown(String userName,String countdownName)
    {
        int delete = countDownDao.delete(new QueryWrapper<CountDown>().eq("user_name", userName).eq("countdown_name", countdownName));
        if (delete == 1)
        {
            return true;
        }
        return false;
    }



    //每日更新倒计时（获取当前countdownPast+1即可）
    public Boolean updateCountDownPastDay(String userName) throws ParseException {
        //列出所有未完成的倒计时
        List<CountDown> countDowns = finderCountDownOverFalse(userName);
        //获取当前日期
        Date date = new Date();
        System.out.println(date.getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //遍历列表（语法糖）
        for (CountDown countDown : countDowns) {
            //将日期开始日期解析为date
            String Start = countDown.getCountdownStart();
            Date countdownStart = dateFormat.parse(Start);
            System.out.println(countdownStart.getTime());

            // 计算日期差
            long diffInMillies = Math.abs(date.getTime() - countdownStart.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            System.out.println(diff);


            //更新当前倒计时的天数
            countDown.setCountdownPast(diff);
            if (Objects.equals(countDown.getCountdownPast(), countDown.getCountdownDay())){
                countDown.setCountdown_over(1);
            }
            int update = countDownDao.update(countDown, new QueryWrapper<CountDown>().eq("user_name", userName).eq("countdown_name", countDown.getCountdownName()));
            if (update == 1)
            {
                return true;
            }
            return false;
        }
        return false;
    }

    //计算倒计时还剩多少天
    public Long countDownDay(String userName,String countdownName)
    {
        //获取当前倒计时
        CountDown countDown = countDownDao.selectOne(new QueryWrapper<CountDown>().eq("user_name", userName).eq("countdown_name", countdownName));
        //计算倒计时还剩多少天
        return countDown.getCountdownDay() - countDown.getCountdownPast();
    }


    //列出当前用户的所有倒计时
    public List<CountDown> finderCountDown(String userName)
    {
        //获取当前用户的所有倒计时
        List<CountDown> userName1 = countDownDao.selectList(new QueryWrapper<CountDown>().eq("user_name", userName));
        //如果此用户没有倒计时
        if (userName1.isEmpty()){
            return null;
        }
        return userName1;
    }

    //修改倒计时内容
    public Boolean updateCountDown(String userName,String countdownName,Long countdownDay)
    {
        //获取当前倒计时
        CountDown countDown = countDownDao.selectOne(new QueryWrapper<CountDown>().eq("user_name", userName).eq("countdown_name", countdownName));
        //更新当前倒计时的天数
        countDown.setCountdownDay(countdownDay);
        int update = countDownDao.update(countDown, new QueryWrapper<CountDown>().eq("user_name", userName).eq("countdown_name", countdownName));
        if (update == 1)
        {
            return true;
        }
        return false;
    }

    //列出所有未完成的倒计时
    public List<CountDown> finderCountDownOverFalse(String userName)
    {
        //获取所有未完成的倒计时
        List<CountDown> countDowns = countDownDao.selectList(new QueryWrapper<CountDown>().eq("countdown_over", 0).eq("user_name", userName));
        //如果此用户没有倒计时
        if (countDowns.isEmpty()){
            return null;
        }
        return countDowns;
    }

    //列出所有完成的倒计时
    public List<CountDown> finderCountDownOverTrue(String userName)
    {
        //获取所有未完成的倒计时
        List<CountDown> countDowns = countDownDao.selectList(new QueryWrapper<CountDown>().eq("countdown_over", 1).eq("user_name", userName));
        //如果此用户没有倒计时
        if (countDowns.isEmpty()){
            return null;
        }
        return countDowns;
    }
}
