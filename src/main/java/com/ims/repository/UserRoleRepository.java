package com.ims.repository;

import com.ims.model.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author admin
 * @date 2018/10/11
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {


    /**
     * 删除用户关联的角色
     * @param userId
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    void deleteByUserId(int userId);



}
