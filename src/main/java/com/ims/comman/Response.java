package com.ims.comman;

/**
 * @author admin
 * @date 2018/9/29
 */
public class Response {

    private int status;
    private String message;
    private Object data;


    public Response () {

    }

    public Response (int status,String message,Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static Response success (Object data) {
        return new Response(200,"操作成功!",data);
    }

    public static Response error (String message) {
        return new Response(400,message,null);
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
