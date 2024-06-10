package fun.tanc.selfclocking.control;


import cn.dev33.satoken.util.SaResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

@RestController
@RequestMapping("/TestJedis")
public class TestJedis {
    @Autowired
    private Jedis jedis;

        @GetMapping("/setJedis")
    public SaResult testSetJedis() {
        try {
            jedis.set("1212", "redis存入去除这么简单");
        } catch (Exception e) {
            System.out.println(e);
            return SaResult.error("redis存入失败");
        }
        return SaResult.ok("存入成功");
    }

    @GetMapping("/getJedis")
    public SaResult testGetJedis() {
        String s = null;
        try {
            s = jedis.get("1212");
            System.out.println(s);
        } catch (Exception e) {
            System.out.println(e);
            return SaResult.error("redis取出失败");
        }
        return SaResult.data(s);
    }
}
