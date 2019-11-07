package com.firecontrol.mapper;

import com.firecontrol.common.RunningStateCount;
import com.firecontrol.domain.dto.DeviceSearch;
import com.firecontrol.domain.entity.TpsonDeviceEntity;
import com.firecontrol.domain.entity.TpsonDeviceLogEntity;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by mariry on 2019/9/30.
 */
public interface TpsonDeviceMapper {

    public TpsonDeviceEntity selectById(@Param("id") Long id);

    public Integer deleteById(@Param("id") Long id);

    public Integer insert(TpsonDeviceEntity log);

    public Integer insertSelective(TpsonDeviceEntity log);

    public Integer updateById(TpsonDeviceEntity log);

    public Integer getTotal(DeviceSearch search);

    public List<TpsonDeviceEntity> getDeviceListBySearch(DeviceSearch search);

    public Integer changeDeviceStatus(@Param("idList") List<String> ids, @Param("status") Integer status);

    public Integer deleteDevice(@Param("idList")List<String> idList);

    public List<String> getDeviceCodeByIds(@Param("idList")List<String> idList);

    public Integer countDeviceNumByDeviceType(@Param("deviceTypeList") List<Long> deviceTypeList);

    public TpsonDeviceEntity selectByDeviceCode(@Param("code") String code);

    public List<RunningStateCount> getRunningStateDistByDeviceType(@Param("deviceTypeList") List<Long> deviceTypeList);

    public Integer getOfflineDeviceCountByDeviceType(@Param("deviceTypeList") List<Long> deviceTypeList);

    public Integer getTotalBySystemType(@Param("deviceTypeList") List<Long> deviceTypeList);


}
