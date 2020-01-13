package com.firecontrol.mapper.iotmapper;

import com.firecontrol.domain.entity.TaskDetail;
import javafx.concurrent.Task;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by mary on 2020/1/6.
 */
public interface TaskDetailMapper {

    TaskDetail getById(@Param("id") Long id);

    Integer deleteById(@Param("id") Long id);

    Integer insert(TaskDetail detail);

    List<TaskDetail> getDetailByTaskId(@Param("vendorId") Long vendorId, @Param("taskId") Long taskId);

    Integer updateTaskDetail(@Param("vendorId") Long vendorId, @Param("id") Long taskDetailId, @Param("description") String description,
                             @Param("deviceCount") Integer deviceCount, @Param("state") Integer state);


    Long getDetailId(@Param("vendorId") Long vendorId, @Param("taskId")Long taskId, @Param("checkItemId")Long checkItemId);

}
