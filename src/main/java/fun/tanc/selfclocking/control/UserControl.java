package fun.tanc.selfclocking.control;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import fun.tanc.selfclocking.model.UserModel;
import fun.tanc.selfclocking.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class UserControl {
    @Autowired
    UserServiceImpl userService;

    //用户登录
    @PostMapping(value = "/loginUser")
    @CrossOrigin
    public SaResult loginUser(@RequestBody Map<String,String> map){
        String name = map.get("username");
        String password = map.get("password");

        //查询用户是否存在
        UserModel user = userService.findUser(name);
        if (user == null){
            System.out.println("用户不存在");
            return SaResult.error("登录失败:用户名或者密码错误");
        }

        //查看密码是否正确
        if (user.getPassword().equals(password)){
            StpUtil.login(user.getName());
            String tokenValue = StpUtil.getTokenValue();
            return SaResult.data(tokenValue);
        }else {
            return SaResult.error("登录失败:用户名或者密码错误");
        }
    }

    //判断用户是否登录
    @RequestMapping(value = "/checkLogin",method = RequestMethod.GET)
    @CrossOrigin
    public boolean checkLogin(){
        return StpUtil.isLogin();
    }

    //获取当前会话Token
    @RequestMapping(value = "/getToken",method = RequestMethod.GET)
    @CrossOrigin
    public String getToken(){
        return StpUtil.getTokenValue();
    }



    //用户登出
    @RequestMapping(value = "/logoutUser",method = RequestMethod.GET)
    @CrossOrigin
    public SaResult logoutUser(){
        StpUtil.logout();
        return SaResult.ok("退出成功");
    }


    //注册用户
    @RequestMapping(value = "/registerUser",method = RequestMethod.POST)
    @CrossOrigin
    public SaResult addUser(@RequestBody Map<String,String> map){
        Boolean b = userService.addUser(map.get("username"), map.get("password"));
        if (b){
            return SaResult.ok("注册成功");
        }else {
            return SaResult.error("注册失败");
        }
    }


    //删除用户
    @RequestMapping(value = "/deleteUser",method = RequestMethod.DELETE)
    @CrossOrigin
    public Boolean deleteUser(@RequestParam("username") String username){
        return userService.deleteUser(username);
    }



    //查找用户(精确)
    @RequestMapping(value = "/findUser",method = RequestMethod.GET)
    @CrossOrigin
    public UserModel findUser(@RequestParam("username") String username){
        return userService.findUser(username);
    }



    //查找用户(模糊)
    @SaCheckLogin
    @RequestMapping(value = "/finderUserName",method = RequestMethod.GET)
    @CrossOrigin
    public List<UserModel> finderUserName(@RequestParam("name") String name){
        return userService.finderUserName(name);
    }

    @SaCheckLogin
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    @CrossOrigin
    public SaResult hello(){
        return SaResult.ok("hello");
    }
}
