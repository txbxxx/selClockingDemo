package fun.tanc.selfclocking.config;

import cn.hutool.db.nosql.redis.RedisDS;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

/**
 * Jedis 配置类
 */
@Configuration
public class RedisConfig {
    private Jedis jedis;
    /**
     * 交由Spring来处理Bean对象创建Jedis
     * @return Jedis对象
     */
    @Bean
    public Jedis getJedis() {
        this.jedis =  RedisDS.create().getJedis();
        return jedis;
    }

    /**
     * 关闭Jedis连接
     */
    @PreDestroy
    public void close() {
        if (jedis != null) {
            jedis.close();
        }
    }
}
