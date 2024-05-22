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
		userService.addUser("tanc", "123456");
//		userService.addUser("tancc", "123456");
//		userServic//		.addUser("tanccc", "123456");
//		userService.deleteUser("tanc");
//		userService.deleteUser("tanccc");
//		userService.deleteUser("tancc");
//		userService.findUser("tanccc");
////		userService.finderUserName("t");
//		userTaskService.addUserTask("tancc", "test", "test");
//		userTaskService.addUserTask("tancc", "test2", "test2");
//		userTaskService.addUserTask("tancc", "test3", "test3");

//		userTaskService.deleteUserTask("tancc", "test");
//		userTaskService.deleteUserTask("tancc", "test2");
//		userTaskService.deleteUserTask("tancc", "test3");

//		List<UserTask> tancc = userTaskService.findUserTask("tancc");
//		tancc.forEach(x -> System.out.println(x.getTaskField()));

//
		rsiServiceImpl.addRelation("tanc", "szy", "love");

	}

}
