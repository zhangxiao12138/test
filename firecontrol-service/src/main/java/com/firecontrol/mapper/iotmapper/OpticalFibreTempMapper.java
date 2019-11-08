package com.firecontrol.mapper.iotmapper;

import com.firecontrol.domain.entity.OpticalFibreTempEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by mariry on 2019/7/18.
 */
public interface OpticalFibreTempMapper {

    public void insert(OpticalFibreTempEntity opticalFibreTemp);

    public List<OpticalFibreTempEntity> getData(@Param("channelNo")Integer channelNo);




}
