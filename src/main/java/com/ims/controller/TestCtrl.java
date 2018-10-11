package com.ims.controller;

import com.alibaba.fastjson.JSONObject;
import com.ims.comman.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 * @date 2018/9/29
 */
@RestController
@RequestMapping("/api/test")
public class TestCtrl {


    @GetMapping(produces = "application/json")
    public Object test() {
        Response response = null;
        try {
            JSONObject data = new JSONObject();
            data.put("k1","1");
            data.put("k2","2");
            response = Response.success(data);
        } catch (Exception e) {
            e.printStackTrace();
            response = Response.error(e.getMessage());
        }
        return response;
    }
}
