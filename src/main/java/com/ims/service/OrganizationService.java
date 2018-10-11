package com.ims.service;

import com.ims.model.OrganizationEntity;

import java.util.List;

/**
 * @author admin
 * @date 2018/10/9
 */
public interface OrganizationService {


    /**
     * 保存
     *
     * @param entity
     * @return
     * @throws Exception
     */
    OrganizationEntity save(OrganizationEntity entity) throws Exception;


    /**
     * 获取组织及其下属组织的主键列表
     *
     * @param project
     * @param orgId
     * @return
     * @throws Exception
     */
    List<Integer> getList(String project, int orgId) throws Exception;


    /**
     * 获取树形列表
     *
     * @param project
     * @param orgId
     * @return
     * @throws Exception
     */
    OrganizationEntity getTree(String project, int orgId) throws Exception;


    /**
     * 获取树形列表
     *
     * @param project
     * @return
     * @throws Exception
     */
    List<OrganizationEntity> getTree(String project) throws Exception;
}
