package com.ims.service.impl;

import com.ims.model.UserEntity;
import com.ims.model.UserRoleEntity;
import com.ims.repository.UserRepository;
import com.ims.repository.UserRoleRepository;
import com.ims.service.OrganizationService;
import com.ims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @date 2018/10/9
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private UserRoleRepository userRoleRepository;



    /**
     * 保存
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public UserEntity save(UserEntity entity) throws Exception {
        if (entity.getId() < 1) {
            entity.setGenTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        UserEntity userEntity = userRepository.save(entity);
        //添加用户关联角色
        userRoleRepository.deleteByUserId(entity.getId());
        if (entity.getRoles() != null && entity.getRoles().size() > 0) {
            List<UserRoleEntity> userRoleEntities = new ArrayList<>();
            entity.getRoles().forEach(e -> {
                UserRoleEntity userRoleEntity = new UserRoleEntity();
                userRoleEntity.setUserId(userEntity.getId());
                userRoleEntity.setRoleId(e);
                userRoleEntities.add(userRoleEntity);
            });
            userRoleRepository.saveAll(userRoleEntities);
        }
        return userEntity;
    }


    /**
     * 获取用户列表
     *
     * @param page
     * @param size
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public Page<UserEntity> list(int page, int size, UserEntity entity) throws Exception {
        Page<UserEntity> result = null;
        if (page == 0 && size == 0) {
            List<UserEntity> content = buildRoles(filter(entity,userRepository.findAll()));
            if (content != null) {
                result = new PageImpl<UserEntity>(content);
            }
        } else {
            List<UserEntity> content = buildRoles(filter(entity, userRepository.findAll(whereClause(entity))));
            if (content != null) {
                result = new PageImpl<UserEntity>(content, PageRequest.of(page - 1, size), content.size());
            }
        }
        return result;
    }


    private List<UserEntity> buildRoles(List<UserEntity> content) {
        if (content != null && content.size() > 0) {
            content.forEach(e -> {
                e.getRoleEntities().forEach(e1 -> {
                    e.getRoles().add(e1.getId());
                });
            });
        }
        return content;
    }

    private Specification<UserEntity> whereClause(final UserEntity userEntity) {
        return new Specification<UserEntity>() {
            @Override
            public Predicate toPredicate(Root<UserEntity> r, CriteriaQuery<?> q, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (userEntity == null) {
                    return predicate;
                }
                if (StringUtils.hasLength(userEntity.getProject())) {
                    predicate.getExpressions().add(
                            cb.equal(r.<String>get("project"), userEntity.getProject())
                    );
                }
                return predicate;
            }
        };
    }


    private List<UserEntity> filter(UserEntity entity, List<UserEntity> userEntities) throws Exception {
        List<UserEntity> result = new ArrayList<>();
        if (entity.getOrgId() > 0) {
            List<Integer> orgIdArray = organizationService.getList(entity.getProject(), entity.getOrgId());
            if (orgIdArray == null || orgIdArray.size() < 1) {
                return null;
            }
            userEntities.forEach(e -> {
                if (orgIdArray.contains(e.getOrgId())) {
                    result.add(e);
                }
            });
            return result;
        } else {
            return userEntities;
        }
    }

}
