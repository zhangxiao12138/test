package com.firecontrol.mapper.ydmapper;

import com.firecontrol.domain.entity.Floor;
import org.apache.ibatis.annotations.Param;

/**
 * Created by mariry on 2019/11/8.
 */
public interface FloorMapper {
    Floor selectById(@Param("id") String id);
}
