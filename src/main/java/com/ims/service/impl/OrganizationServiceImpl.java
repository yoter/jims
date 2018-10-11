package com.ims.service.impl;

import com.ims.model.OrganizationEntity;
import com.ims.repository.OrganizationRepository;
import com.ims.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @date 2018/10/9
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {


    @Autowired
    private OrganizationRepository organizationRepository;


    /**
     * 保存
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public OrganizationEntity save(OrganizationEntity entity) throws Exception {
        return organizationRepository.save(entity);
    }


    /**
     * 获取组织及其下属组织的主键列表
     *
     * @param project
     * @param orgId
     * @return
     * @throws Exception
     */
    @Override
    public List<Integer> getList(String project, int orgId) throws Exception {
        return treeToList(getTree(project, orgId));
    }


    /**
     * 获取树形列表
     *
     * @param project
     * @param orgId
     * @return
     * @throws Exception
     */
    @Override
    public OrganizationEntity getTree(String project, int orgId) throws Exception {
        List<OrganizationEntity> organizationEntities = organizationRepository.findByProject(project);
        if (organizationEntities == null) {
            return null;
        }
        for (OrganizationEntity organizationEntity : organizationEntities) {
            if (organizationEntity == null || organizationEntity.getId() != orgId) {
                continue;
            }
            buildChildren(organizationEntity, organizationEntities);
            return organizationEntity;
        }
        return null;
    }


    /**
     * 获取树形列表
     *
     * @param project
     * @return
     * @throws Exception
     */
    @Override
    public List<OrganizationEntity> getTree(String project) throws Exception {
        List<OrganizationEntity> result = new ArrayList<>();
        List<OrganizationEntity> organizationEntities = organizationRepository.findByProject(project);
        if (organizationEntities == null) {
            return result;
        }
        organizationEntities.forEach(organizationEntity -> {
            if (organizationEntity != null && organizationEntity.getParent() == 0) {
                buildChildren(organizationEntity, organizationEntities);
                result.add(organizationEntity);
            }
        });
        return result;
    }


    /**
     * 构建子元素
     *
     * @param organizationEntity
     * @param organizationEntities
     */
    private void buildChildren(OrganizationEntity organizationEntity, List<OrganizationEntity> organizationEntities) {
        for (OrganizationEntity entity : organizationEntities) {
            if (entity == null || entity.getParent() != organizationEntity.getId()) {
                continue;
            }
            List<OrganizationEntity> children = organizationEntity.getChildren();
            children.add(entity);
            organizationEntity.setChildren(children);
            buildChildren(entity, organizationEntities);
        }
    }


    /**
     * 树形结构转列表
     *
     * @param organizationEntity
     * @return
     */
    private List<Integer> treeToList(OrganizationEntity organizationEntity) {
        List<Integer> result = new ArrayList<>();
        if (organizationEntity == null) {
            return null;
        }
        result.add(organizationEntity.getId());
        if (organizationEntity.getChildren() != null && organizationEntity.getChildren().size() > 0) {
            for (OrganizationEntity entity : organizationEntity.getChildren()) {
                if (entity == null) {
                    continue;
                }
                List<Integer> idArray = treeToList(entity);
                if (idArray == null) {
                    continue;
                }
                result.addAll(idArray);
            }
        }
        return result;
    }
}
