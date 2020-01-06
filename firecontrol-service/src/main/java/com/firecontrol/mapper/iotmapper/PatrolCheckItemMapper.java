package com.firecontrol.mapper.iotmapper;

import com.firecontrol.domain.entity.PatrolCheckItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by mariry on 2020/1/6.
 */
public interface PatrolCheckItemMapper {

    PatrolCheckItem getById(@Param("id") Long id);

    Integer insert(PatrolCheckItem checkItem);

    Integer deleteById(@Param("id") Long id);

    List<PatrolCheckItem> getCheckItemsByNodeId(@Param("vendorId") Long vendorId, @Param("nodeId") Long nodeId);


}
