package com.ims.controller;

import com.alibaba.fastjson.JSONObject;
import com.ims.comman.Response;
import com.ims.model.ProjectEntity;
import com.ims.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author admin
 * @date 2018/9/29
 */
@RestController
@RequestMapping("/api/project")
public class ProjectCtrl {


    @Autowired
    private ProjectService projectService;




    /**
     * 获取项目列表
     *
     * @param page
     * @param size
     * @param entity
     * @return
     */
    @PostMapping(value = "/list", produces = "application/json")
    public Response list(@RequestParam(required = true, defaultValue = "0") int page, @RequestParam(required = true, defaultValue = "0") int size,
                         @RequestBody ProjectEntity entity) {
        Response response = null;
        try {
            response = Response.success(projectService.list(page, size, entity));
        } catch (Exception e) {
            e.printStackTrace();
            response = Response.error(e.getMessage());
        }
        return response;
    }


    /**
     * 添加项目
     *
     * @param entity
     * @return
     */
    @PostMapping(produces = "application/json")
    public Response add(@RequestBody ProjectEntity entity) {
        Response response = null;
        try {
            response = Response.success(projectService.save(entity));
        } catch (Exception e) {
            e.printStackTrace();
            response = Response.error(e.getMessage());
        }
        return response;
    }


    /**
     * 修改项目
     *
     * @param entity
     * @return
     */
    @PutMapping(produces = "application/json")
    public Response put(@RequestBody ProjectEntity entity) {
        Response response = null;
        try {
            response = Response.success(projectService.save(entity));
        } catch (Exception e) {
            e.printStackTrace();
            response = Response.error(e.getMessage());
        }
        return response;
    }


    /**
     * 删除项目
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public Response put(@PathVariable int id) {
        Response response = null;
        try {
            projectService.delete(id);
            response = Response.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            response = Response.error(e.getMessage());
        }
        return response;
    }
}
