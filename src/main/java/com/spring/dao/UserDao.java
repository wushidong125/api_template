package com.spring.dao;

import com.spring.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by shidong.wu on 2017/9/24.
 */
public interface UserDao extends JpaRepository<UserEntity,Long> {

    UserEntity findUserInfoById(long id);
    List<UserEntity> findUserInfoByRole(String role);

    @Query(value = "select * from t_user limit ?1", nativeQuery =true)
    List<UserEntity> findAllUsersByCount(int count);

}
