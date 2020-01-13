package com.firecontrol.mapper.iotmapper;

import com.firecontrol.domain.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by mariry on 2020/1/6.
 */
public interface UserEntityMapper {

    UserEntity getById(@Param("id") Long id);

    UserEntity getUserByUserName(@Param("userName") String userName, @Param("vendorId") Long vendorId);

    Integer deleteById(@Param("id") Long id);

    Integer insert(UserEntity userEntity);

    Integer updateUserToken(@Param("id")Long id, @Param("token") String token);

    Integer countUserName(@Param("vendorId") Long vendorId, @Param("userName") String userName);

    List<UserEntity> getUserList(@Param("vendorId") Long vendorId, @Param("roleId") Long roleId);

    Integer resetPw(@Param("vendorId") Long vendorId, @Param("targetUserId") Long targetUserId, @Param("newPw") String newPw);


}
