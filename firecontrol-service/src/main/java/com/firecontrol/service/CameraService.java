package com.firecontrol.service;

import com.firecontrol.common.OpResult;
import com.firecontrol.domain.entity.CameraEntity;
import org.springframework.expression.spel.ast.OpEQ;

/**
 * Created by mariry on 2019/8/14.
 */
public interface CameraService {

    public OpResult insertCamera(CameraEntity cameraEntity);

    public CameraEntity getCameraById(Long id);

    public OpResult getCameraListByTerminalId(String terminalId);

    public OpResult updateCamera(CameraEntity camera);

    public Integer getCameraIpCountByTerminalId(String ip, String terminalId);

    public OpResult getCameraList(CameraEntity camera, Integer currentPage, Integer length);

    public OpResult deleteCamera(String ids);

    public OpResult getAllCamera();

    public OpResult dupCameraName(CameraEntity c);

}
