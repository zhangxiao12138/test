package com.firecontrol.mapper;

import com.firecontrol.domain.entity.CameraEntity;
import javafx.scene.Camera;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

/**
 * Created by mariry on 2019/8/14.
 */
public interface CameraMapper {

    public void insert(CameraEntity camera);

    public int update(CameraEntity camera);

    public CameraEntity getCameraById(@Param("id") Long id);

    public List<CameraEntity> getCameraListByTerminalId(@Param("terminalId") String terminalId);

    Integer getCameraIpCountByTerminalId(@Param("ip")String ip, @Param("terminalId") String terminalId);

    CameraEntity getCameraByIpTerminalId(@Param("ip")String ip, @Param("terminalId") String terminaId);

    Integer getTotal(CameraEntity camera);

    List<CameraEntity> getAllCameraList(@Param("start") Integer start, @Param("length")Integer length);

    Integer deleteCamera(@Param("idList") List idList);

    List<CameraEntity> getAllCamera();

    List<CameraEntity> getCameraListByName(CameraEntity c);

    List<CameraEntity> getCameraListByIds(@Param("idList") List idList);


}
