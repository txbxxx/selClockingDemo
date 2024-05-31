package fun.tanc.selfclocking.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.tanc.selfclocking.model.CountDown;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CountDownDao extends BaseMapper<CountDown> {
}
