package com.firecontrol.mapper.iotmapper;

import com.firecontrol.domain.entity.PatrolCheckItem;
import com.firecontrol.domain.entity.PatrolTask;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by mariry on 2020/1/6.
 */
public interface PatrolTaskMapper {

    PatrolTask getById(@Param("id") Long id);

    Long insert(PatrolTask task);

    Integer deleteById(@Param("id") Long id);

    Integer updateTaskStop(@Param("id") Long taskId);

    List<PatrolTask> getTaskListByVendorId(@Param("vendorId") Long vendorId);



}
