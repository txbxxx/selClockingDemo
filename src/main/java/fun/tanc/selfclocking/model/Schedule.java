package fun.tanc.selfclocking.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("schedule")
public class Schedule {
    @TableField(value = "user_id")
    Long id;

    @TableId(value = "schedule_id", type = IdType.AUTO)
    Long scheduleId;

    @TableField(value = "schedule_filed")
    String scheduleFiled;

    @TableField(value = "date")
    String date;

    @TableField(value = "start_time" )
    String startTime;

    @TableField(value = "end_time")
    String endTime;



}
