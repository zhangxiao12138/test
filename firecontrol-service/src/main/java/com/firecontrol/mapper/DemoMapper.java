package com.firecontrol.mapper;

import com.firecontrol.domain.entity.DemoEntity;

import java.util.List;

/**
 * Created by mariry on 2019/6/21.
 */
public interface DemoMapper {

    List<DemoEntity> getAll();

    int insert(DemoEntity demoEntity);

}
