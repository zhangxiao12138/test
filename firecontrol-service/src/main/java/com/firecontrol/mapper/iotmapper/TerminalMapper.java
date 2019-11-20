package com.firecontrol.mapper.iotmapper;

import com.firecontrol.domain.entity.TerminalEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by mariry on 2019/8/15.
 */
public interface TerminalMapper {

    public boolean insert(TerminalEntity terminalEntity);

    public int update(TerminalEntity terminalEntity);

    public List<TerminalEntity> getTerminalListByPage(TerminalEntity terminalEntity);

    public List<String> getAllTerminalIds(@Param("status") Integer status);

    public int getWeekTerminalCount(@Param("prefix") String prefix);

    public int updateHardwareId(@Param("terminalId") String terminalId, @Param("hardwareId") String hardwareId);

    public Integer getTerminalCount(TerminalEntity terminal);


}
