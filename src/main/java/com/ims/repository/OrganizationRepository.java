package com.ims.repository;

import com.ims.model.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author admin
 * @date 2018/10/9
 */
@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationEntity,Integer> {



    List<OrganizationEntity> findByProject(String project);
}
