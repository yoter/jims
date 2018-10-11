package com.ims.service;

import com.ims.model.RoleEntity;
import org.springframework.data.domain.Page;

/**
 * @author admin
 * @date 2018/10/9
 */
public interface RoleService {


    /**
     * 保存
     *
     * @param entity
     * @return
     * @throws Exception
     */
    RoleEntity save(RoleEntity entity) throws Exception;


    /**
     * 获取角色列表
     *
     * @param page
     * @param size
     * @param entity
     * @return
     * @throws Exception
     */
    Page<RoleEntity> list(int page, int size, RoleEntity entity) throws Exception;
}
