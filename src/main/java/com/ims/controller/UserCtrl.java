package com.ims.controller;

import com.ims.comman.Response;
import com.ims.model.UserEntity;
import com.ims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author admin
 * @date 2018/10/9
 */
@RestController
@RequestMapping("/api/user")
public class UserCtrl {



    @Autowired
    private UserService userService;




    /**
     * 保存
     * @param entity
     * @return
     */
    @PostMapping(produces = "application/json")
    public Response save(@RequestBody UserEntity entity) {
        Response response = null;
        try {
            response = Response.success(userService.save(entity));
        } catch (Exception e) {
            e.printStackTrace();
            response = Response.error(e.getMessage());
        }
        return response;
    }


    /**
     * 获取用户列表
     * @param page
     * @param size
     * @param entity
     * @return
     */
    @PostMapping(value = "/list", produces = "application/json")
    public Response list(@RequestParam(required = true, defaultValue = "0") int page, @RequestParam(required = true, defaultValue = "0") int size,
                         @RequestBody UserEntity entity) {
        Response response = null;
        try {
            response = Response.success(userService.list(page, size, entity));
        } catch (Exception e) {
            e.printStackTrace();
            response = Response.error(e.getMessage());
        }
        return response;
    }
}
