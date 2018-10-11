package com.ims.service;

import com.ims.model.UserEntity;
import org.springframework.data.domain.Page;

/**
 * @author admin
 * @date 2018/10/9
 */
public interface UserService {


    /**
     * 保存
     *
     * @param entity
     * @return
     * @throws Exception
     */
    UserEntity save(UserEntity entity) throws Exception;


    /**
     * 获取用户列表
     *
     * @param page
     * @param size
     * @param entity
     * @return
     * @throws Exception
     */
    Page<UserEntity> list(int page, int size, UserEntity entity) throws Exception;
}
