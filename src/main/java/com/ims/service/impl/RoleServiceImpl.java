package com.ims.service.impl;

import com.ims.model.RoleEntity;
import com.ims.repository.RoleRepository;
import com.ims.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
 * @date 2018/10/9
 */
@Service
public class RoleServiceImpl implements RoleService {


    @Autowired
    private RoleRepository roleRepository;


    /**
     * 保存
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public RoleEntity save(RoleEntity entity) throws Exception {
        if (entity.getId() < 1) {
            entity.setGenTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        return roleRepository.save(entity);
    }


    /**
     * 获取角色列表
     *
     * @param page
     * @param size
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public Page<RoleEntity> list(int page, int size, RoleEntity entity) throws Exception {
        if (page == 0 && size == 0) {
            return new PageImpl<RoleEntity>(roleRepository.findAll());
        } else {
            return roleRepository.findAll(whereClause(entity), PageRequest.of(page - 1, size));
        }
    }


    private Specification<RoleEntity> whereClause(final RoleEntity roleEntity) {
        return new Specification<RoleEntity>() {
            @Override
            public Predicate toPredicate(Root<RoleEntity> r, CriteriaQuery<?> q, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (roleEntity == null) {
                    return predicate;
                }
                if (StringUtils.hasLength(roleEntity.getProject())) {
                    predicate.getExpressions().add(
                            cb.equal(r.<String>get("project"), roleEntity.getProject())
                    );
                }
                if (StringUtils.hasLength(roleEntity.getName())) {
                    predicate.getExpressions().add(
                            cb.like(r.<String>get("name"), "%" + roleEntity.getName() + "%")
                    );
                }
                return predicate;
            }
        };
    }

}
