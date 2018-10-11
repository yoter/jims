package com.ims.controller;

import com.ims.comman.Response;
import com.ims.model.RoleEntity;
import com.ims.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author admin
 * @date 2018/10/9
 */
@RestController
@RequestMapping("/api/role")
public class RoleCtrl {


    @Autowired
    private RoleService roleService;


    /**
     * 保存
     * @param entity
     * @return
     */
    @PostMapping(produces = "application/json")
    public Response save(@RequestBody RoleEntity entity) {
        Response response = null;
        try {
            response = Response.success(roleService.save(entity));
        } catch (Exception e) {
            e.printStackTrace();
            response = Response.error(e.getMessage());
        }
        return response;
    }


    /**
     * 获取角色列表
     * @param page
     * @param size
     * @param entity
     * @return
     */
    @PostMapping(value = "/list", produces = "application/json")
    public Response list(@RequestParam(required = true, defaultValue = "0") int page, @RequestParam(required = true, defaultValue = "0") int size,
                         @RequestBody RoleEntity entity) {
        Response response = null;
        try {
            response = Response.success(roleService.list(page, size, entity));
        } catch (Exception e) {
            e.printStackTrace();
            response = Response.error(e.getMessage());
        }
        return response;
    }
}
