package fun.tanc.selfclocking;

import fun.tanc.selfclocking.dao.UserTaskDao;
import fun.tanc.selfclocking.model.UserTask;
import fun.tanc.selfclocking.service.RelationServiceImpl;
import fun.tanc.selfclocking.service.UserServiceImpl;
import fun.tanc.selfclocking.service.UserTaskServiceImpl;
import org.apache.catalina.User;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

import java.util.List;

@SpringBootTest
class SelfClockingApplicationTests {

	@Autowired
	UserServiceImpl userService;

	@Autowired
	UserTaskServiceImpl userTaskService;

	@Autowired
	RelationServiceImpl rsiServiceImpl;



	@Test
	void contextLoads() {

	}

}
