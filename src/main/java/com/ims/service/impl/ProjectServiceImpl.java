package com.ims.service.impl;

import com.ims.model.ProjectEntity;
import com.ims.repository.ProjectRepository;
import com.ims.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author admin
 * @date 2018/9/29
 */
@Service
public class ProjectServiceImpl implements ProjectService {


    @Autowired
    private ProjectRepository projectRepository;


    /**
     * 保存
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public ProjectEntity save(ProjectEntity entity) throws Exception {
        if (entity == null) {
            throw new Exception("项目对象为空!");
        }
        if (entity.getId() < 1) {
            entity.setGenTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        return projectRepository.save(entity);
    }


    /**
     * 删除
     *
     * @param id
     * @throws Exception
     */
    @Override
    public void delete(Integer id) throws Exception {
        projectRepository.deleteById(id);
    }


    /**
     * 获取项目列表
     *
     * @param page
     * @param size
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public Page<ProjectEntity> list(int page, int size, ProjectEntity entity) throws Exception {
        if (page == 0 && size == 0) {
            return new PageImpl<ProjectEntity>(projectRepository.findAll());
        } else {
            return projectRepository.findAll(whereClause(entity), PageRequest.of(page - 1, size));
        }
    }


    private Specification<ProjectEntity> whereClause(final ProjectEntity projectEntity) {
        return new Specification<ProjectEntity>() {
            @Override
            public Predicate toPredicate(Root<ProjectEntity> r, CriteriaQuery<?> q, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (projectEntity == null) {
                    return predicate;
                }
                if (StringUtils.hasLength(projectEntity.getCode())) {
                    predicate.getExpressions().add(
                            cb.like(r.<String>get("code"), "%" + projectEntity.getCode() + "%")
                    );
                }
                if (StringUtils.hasLength(projectEntity.getName())) {
                    predicate.getExpressions().add(
                        cb.like(r.<String>get("name"), "%" + projectEntity.getName() + "%")
                    );
                }
                return predicate;
            }
        };
    }
}
