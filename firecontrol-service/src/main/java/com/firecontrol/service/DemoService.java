package com.firecontrol.service;

import com.firecontrol.common.OpResult;
import com.firecontrol.common.VideoOpResult;
import com.firecontrol.domain.dto.VideoUrlQueryDto;
import com.firecontrol.domain.entity.DemoEntity;

import java.util.List;

/**
 * Created by mariry on 2019/6/21.
 */
public interface DemoService {

    List<DemoEntity> getAllDemoEntity();


    VideoOpResult getVideoAddr(VideoUrlQueryDto query);

    String getVideoUrl();

}
