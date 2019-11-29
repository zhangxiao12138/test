package com.firecontrol.mapper.ydmapper;

import com.firecontrol.domain.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by mariry on 2019/11/26.
 */
public interface UserMapper {

    public Integer getTotal();

    public User getById(@Param("id") String id);



}
