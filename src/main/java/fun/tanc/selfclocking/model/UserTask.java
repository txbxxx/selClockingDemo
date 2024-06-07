package fun.tanc.selfclocking.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("usertask")
public class UserTask {

    //自动增长无序构建
    @TableId(value = "task_id",type = IdType.AUTO)
    private long taskId;

    @TableField(value = "user_id")
    private long id;

    @TableField(value = "task_name")
    private String taskName;

    @TableField(value = "level")
    private int taskLevel;

    @TableField(value = "task_field")
    private String taskField;

    @TableField(value = "isover")
    private int isOver;

    public UserTask(long id, String taskName, String taskField) {
        this.id = id;
        this.taskName = taskName;
        this.taskField = taskField;
    }

    public UserTask(long userID, String taskName, int taskLevel, String taskStr, int i) {
        this.id = userID;
        this.taskName = taskName;
        this.taskField = taskStr;
        this.taskLevel = taskLevel;
    }

    public UserTask(Long id, String taskName, int taskLevel, String taskStr) {
        this.id = id;
        this.taskName = taskName;
        this.taskField = taskStr;
        this.taskLevel = taskLevel;
    }
}
