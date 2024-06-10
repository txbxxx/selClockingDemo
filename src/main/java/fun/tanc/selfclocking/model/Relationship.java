package fun.tanc.selfclocking.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("relationship")
public class Relationship {
    @TableId(value = "r_id",type = IdType.AUTO)
    private long id;

    @TableField(value = "user_one")
    private long userOneId;

    @TableField("user_two")
    private long userTwoId;

    @TableField("relationship")
    private String relationship;

    @TableField(value = "day")
    private int day;

    @TableField(value = "start_day")
    private String startDay;


    public Relationship(Long id, Long id1, String relationship, int i, String startDay) {
        this.userOneId = id;
        this.userTwoId = id1;
        this.relationship = relationship;
        this.day = i;
        this.startDay = startDay;
    }
}
