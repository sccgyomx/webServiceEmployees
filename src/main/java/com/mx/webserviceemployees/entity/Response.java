package com.mx.webserviceemployees.entity;

public class Response {
    Integer id;

    boolean success;

    String message;

    public Response(Integer id, boolean success, String message) {
        this.id = id;
        this.success = success;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "id=" + id +
                ", success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
