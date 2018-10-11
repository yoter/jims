package com.ims.repository;

import com.ims.model.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author admin
 * @date 2018/9/29
 */
@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity,Integer>,JpaSpecificationExecutor<ProjectEntity>{
}
