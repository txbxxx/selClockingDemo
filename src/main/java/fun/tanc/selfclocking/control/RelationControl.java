package fun.tanc.selfclocking.control;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import fun.tanc.selfclocking.model.Relationship;
import fun.tanc.selfclocking.model.UserModel;
import fun.tanc.selfclocking.service.RelationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class RelationControl {
    @Autowired
    RelationServiceImpl relationService;

    //绑定关系
    @SaCheckLogin
    @RequestMapping(value ="/bindRelationship", method = RequestMethod.POST)
    public SaResult bindRelationship(@RequestBody Map<String , String> map)
    {
        String userName = StpUtil.getLoginId().toString();
        String friendName = map.get("friendname");
        String relationship = map.get("relationship");
        Boolean b = relationService.addRelation(userName, friendName, relationship);
        if (!b){
            return  SaResult.error("绑定关系失败");
        }
        return SaResult.ok("绑定关系成功");
    }

    //解绑关系
    @SaCheckLogin
    @RequestMapping(value = "/deleteRelationship",method = RequestMethod.DELETE)
    public SaResult deleteRelationship(@RequestBody Map<String , String> map)
    {
        String userName = StpUtil.getLoginId().toString();
        String friendName = map.get("friendname");
        Boolean b = relationService.deleteRelation(userName, friendName);
        if (!b){
            return  SaResult.error("解绑关系失败");
        }
        return SaResult.ok("解定关系成功");
    }


    //判断用户是否有绑定关系
    @SaCheckLogin
    @RequestMapping(value = "/findRelationship",method = RequestMethod.GET)
    public SaResult findRelationship()
    {
        String userName = StpUtil.getLoginId().toString();
        try {
            Relationship relationship = relationService.findRelation(userName);
            if (relationship == null) {
                return SaResult.error("未绑定关系");
            }
            return  SaResult.data(relationship);
        }catch (Exception e){
            System.out.println("未绑定关系");
            return SaResult.error("未绑定关系");
        }

    }


    //列出和当前用户绑定关系的用户
    @SaCheckLogin
    @RequestMapping(value = "/listRelationship",method = RequestMethod.GET)
    public SaResult listRelationship()
    {
        String userName = StpUtil.getLoginId().toString();
        try {
        UserModel relationship = relationService.listRelation(userName);
            return SaResult.data(relationship);
        }catch (Exception e){
            return SaResult.error("未绑定关系");
        }

    }
}
