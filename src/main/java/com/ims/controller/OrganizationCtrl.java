package com.ims.controller;

import com.ims.comman.Response;
import com.ims.model.OrganizationEntity;
import com.ims.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author admin
 * @date 2018/10/9
 */
@RestController
@RequestMapping("/api/organization")
public class OrganizationCtrl {


    @Autowired
    private OrganizationService organizationService;


    /**
     * 保存
     *
     * @param entity
     * @return
     */
    @PostMapping(produces = "application/json")
    public Response add(@RequestBody OrganizationEntity entity) {
        Response response = null;
        try {
            response = Response.success(organizationService.save(entity));
        } catch (Exception e) {
            e.printStackTrace();
            response = Response.error(e.getMessage());
        }
        return response;
    }


    /**
     * 获取树形列表
     *
     * @param project
     * @return
     */
    @GetMapping("/list/tree")
    public Response getTree(@RequestParam(value = "project", required = true, defaultValue = "") String project) {
        Response response = null;
        try {
            response = Response.success(organizationService.getTree(project));
        } catch (Exception e) {
            e.printStackTrace();
            response = Response.error(e.getMessage());
        }
        return response;
    }
}
