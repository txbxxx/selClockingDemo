package fun.tanc.selfclocking.control;

import fun.tanc.selfclocking.model.UserModel;
import fun.tanc.selfclocking.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserControl {
    @Autowired
    UserServiceImpl userService;

    //注册用户
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public Boolean addUser(@RequestParam("name") String name,
                           @RequestParam("password") String password){
        return userService.addUser(name,password);
    }


    //删除用户
    @RequestMapping(value = "/deleteUser",method = RequestMethod.DELETE)
    public Boolean deleteUser(@RequestParam("name") String name){
        return userService.deleteUser(name);
    }


    //查找用户(精确)
    @RequestMapping(value = "/findUser",method = RequestMethod.GET)
    public UserModel findUser(@RequestParam("name") String name){
        return userService.findUser(name);
    }

    //查找用户(模糊)
    @RequestMapping(value = "/finderUserName",method = RequestMethod.GET)
    public List<UserModel> finderUserName(@RequestParam("name") String name){
        return userService.finderUserName(name);
    }
}
