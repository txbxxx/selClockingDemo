package fun.tanc.selfclocking.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.tanc.selfclocking.model.UserTask;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserTaskDao  extends  BaseMapper<UserTask>{
//    //创建任务表
//    @Insert("CREATE TABLE ${tableName} ( user_id INT PRIMARY KEY, foreign key(user_id) references user(id) on delete cascade on update cascade)")
//    void createTaskTable(@Param("tableName") String tableName) throws Exception;
//
//    //插入用户id
//    @Insert("insert into ${tableName} (user_id) values (${userId})")
//    void insertUserId(@Param("userId") long userId,@Param("tableName") String tableName) throws Exception;
//
//    //插入任务名
//    @Insert("alter table ${tableName} add column ${taskName} varchar(255)")
//    void alterTaskTable(@Param("tableName") String tableName,@Param("taskName") String taskName) throws Exception;
//
//    //插入任务内容
//    @Update("update ${tableName} set ${taskName} = ${taskStr} where user_id = ${user_id}")
//    void insertTask(@Param("tableName") String tableName,@Param("taskName") String taskName,@Param("user_id") long userId,@Param("taskStr") String taskStr) throws Exception;
//
//    //删除用户任务
//    @Insert("alter table $(tableNmae) drop $(taskName)")
//    void deleteUserTask(@Param("tableName") String tableName,@Param("taskName") String taskName) throws Exception;
//
//    //删除用户表
//    @Delete("drop table ${tableName}")
//    void deleteUserTable(@Param("tableName") String tableName) throws Exception;
//
//    //列出任务表
//    @Select("<script>" +
//            "SELECT " +
//            "<if test= 'false'> user_id,</if>" + // 根据条件决定是否包含 password 字段
//            "* " + // 其他字段
//            "FROM ${tableName}" +
//            "</script>")
//    List<UserTask> listUserTask(@Param("tableName") String tableName) throws Exception;
}

