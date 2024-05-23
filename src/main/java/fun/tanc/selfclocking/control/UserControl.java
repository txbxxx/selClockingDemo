package fun.tanc.selfclocking.control;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
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

    //用户登录
    @RequestMapping(value = "/loginUser",method = RequestMethod.GET)
    public SaResult loginUser(@RequestParam("username") String name,
                              @RequestParam("password") String password){
        //查询用户是否存在
        UserModel user = userService.findUser(name);
        if (user == null){
            System.out.println("用户不存在");
            return SaResult.error("登录失败:用户名或者密码错误");
        }

        //查看密码是否正确
        if (user.getPassword().equals(password)){
            StpUtil.login(user.getId());
            String tokenValue = StpUtil.getTokenValue();
            return SaResult.ok("登录成功").setData(tokenValue);
        }else {
            return SaResult.error("登录失败:用户名或者密码错误");
        }
    }

    //用户登出
    @RequestMapping(value = "/logoutUser",method = RequestMethod.GET)
    public SaResult logoutUser(){
        StpUtil.logout();
        return SaResult.ok("退出成功");
    }

    //注册用户
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public Boolean addUser(@RequestParam("username") String name,
                           @RequestParam("password") String password){
        return userService.addUser(name,password);
    }


    //删除用户
    @RequestMapping(value = "/deleteUser",method = RequestMethod.DELETE)
    public Boolean deleteUser(@RequestParam("username") String username){
        return userService.deleteUser(username);
    }


    //查找用户(精确)
    @RequestMapping(value = "/findUser",method = RequestMethod.GET)
    public UserModel findUser(@RequestParam("username") String username){
        return userService.findUser(username);
    }

    //查找用户(模糊)
    @SaCheckLogin
    @RequestMapping(value = "/finderUserName",method = RequestMethod.GET)
    public List<UserModel> finderUserName(@RequestParam("name") String name){
        return userService.finderUserName(name);
    }

    @SaCheckLogin
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public SaResult hello(){
        return SaResult.ok("hello");
    }
}
