package fun.tanc.selfclocking.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.tanc.selfclocking.model.Relationship;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RelationshipDao extends BaseMapper<Relationship> {
}
