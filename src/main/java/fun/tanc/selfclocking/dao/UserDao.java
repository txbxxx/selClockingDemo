package fun.tanc.selfclocking.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.tanc.selfclocking.model.UserModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<UserModel> {
}
