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


}
