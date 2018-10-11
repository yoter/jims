package com.ims.service;

import com.ims.model.ProjectEntity;
import org.springframework.data.domain.Page;

/**
 * @author admin
 * @date 2018/9/29
 */
public interface ProjectService {


    /**
     * 保存
     *
     * @param entity
     * @return
     * @throws Exception
     */
    ProjectEntity save(ProjectEntity entity) throws Exception;


    /**
     * 删除
     *
     * @param id
     * @throws Exception
     */
    void delete(Integer id) throws Exception;


    /**
     * 获取项目列表
     *
     * @param page
     * @param size
     * @param entity
     * @return
     * @throws Exception
     */
    Page<ProjectEntity> list(int page, int size, ProjectEntity entity) throws Exception;
}
