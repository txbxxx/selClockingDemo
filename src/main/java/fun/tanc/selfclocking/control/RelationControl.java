package fun.tanc.selfclocking.control;

import cn.dev33.satoken.annotation.SaCheckLogin;
import fun.tanc.selfclocking.service.RelationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RelationControl {
    @Autowired
    RelationServiceImpl relationService;

    //绑定关系
    @SaCheckLogin
    @RequestMapping(value ="/bindRelationship", method = RequestMethod.POST)
    public Boolean bindRelationship(@RequestParam("username") String userName,
                                    @RequestParam("friendname") String friendName,
                                    @RequestParam("relationship") String relationship)
    {
        return relationService.addRelation(userName,friendName,relationship);
    }

    //解绑关系
    @SaCheckLogin
    @RequestMapping(value = "/deleteRelationship",method = RequestMethod.DELETE)
    public Boolean deleteRelationship(@RequestParam("username") String userName,
                                      @RequestParam("friendname") String friendName)
    {
        return relationService.deleteRelation(userName,friendName);
    }
}
