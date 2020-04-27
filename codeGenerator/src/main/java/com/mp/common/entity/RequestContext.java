package com.mp.common.entity;

import lombok.Data;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lvlu
 * @date 2019-03-01 17:05
 **/
public class RequestContext {

    private static final String CURRENTUSER = "Current-User";

    public static void setCurrentUser(RequestUser user) {
        if (user != null) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            request.setAttribute(CURRENTUSER, user);
        }
    }


    public static RequestUser getCurrentUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return (RequestUser) request.getAttribute(CURRENTUSER);
    }

    @Data
    public static class RequestUser{

        private String id;

        private String username;

        private String password;

        private String nickName;

        private Integer type;

        private String uid;

        private String mobile;

        private String salt;

        private String storeMarkCode;

        private String cityCode;

        private String provinceCode;
    }
}
