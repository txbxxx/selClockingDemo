package fun.tanc.selfclocking.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("countdown")
public class CountDown {
    @TableField(value = "user_name")
    String userName;

    @TableField(value = "countdown_name")
    String countdownName;

    @TableField(value = "countdown_day")
    Long countdownDay;

    @TableField(value = "countdown_past")
    Long countdownPast;

    @TableField(value = "countdown_over")
    int countdown_over;

    @TableField(value = "countdown_start")
    String countdownStart;

    @TableField(value = "countdown_end")
    String countdownEnd;
}
