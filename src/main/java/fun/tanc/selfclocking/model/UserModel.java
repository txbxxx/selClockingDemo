package fun.tanc.selfclocking.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class UserModel {
    /*
    * id
    * name
    * password
    * learnDate
    * task
    * */
    @TableId(value = "id", type = IdType.ASSIGN_UUID) // 指定主键的生成方式
    private Long id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "password")
    private String password;

    @TableField(value = "learn_date")
    private int learnDate;

    @TableField(value = "task")
    private String task;

    public UserModel(long id,String name, String password) {
        this.name = name;
        this.password = password;
        this.id = id;
    }

    public UserModel(long id,String name, String password,int learnDate) {
        this.name = name;
        this.password = password;
        this.id = id;
        this.learnDate = learnDate;
    }
}
