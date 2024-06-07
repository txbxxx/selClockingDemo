package fun.tanc.selfclocking.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("usertask")
public class UserTask {
    @TableId(value = "user_id")
    private long id;

    @TableField(value = "task_name")
    private String taskName;

    @TableField(value = "level")
    private int taskLevel;

    @TableField(value = "task_field")
    private String taskField;



    public UserTask(long id, String taskName, String taskField) {
        this.id = id;
        this.taskName = taskName;
        this.taskField = taskField;
    }
}
