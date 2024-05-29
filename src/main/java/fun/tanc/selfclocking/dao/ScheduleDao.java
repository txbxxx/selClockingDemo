package fun.tanc.selfclocking.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.tanc.selfclocking.model.Relationship;
import fun.tanc.selfclocking.model.Schedule;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScheduleDao  extends BaseMapper<Schedule> {
}
