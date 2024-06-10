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

    @TableField(value = "day",exist = false)
    private Date day;

    @TableField(value = "time")
    private LocalDateTime time;

    public Relationship(Long uid1, Long uid2, String relationship, LocalDateTime now) {
        this.userOneId = uid1;
        this.userTwoId = uid2;
        this.relationship = relationship;
        this.time = now;
    }
}
