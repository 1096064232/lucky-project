package com.lucky.core.security.controller;

import com.lucky.core.property.constant.SecurityConstants;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.lang.reflect.Field;

@RestController
public class SecurityController {

    /**
     *  获取当前认证的用户信息
     * @return
     */
    @GetMapping(SecurityConstants.PRINCIPAL_URL)
    public Object getAuthentication() throws NoSuchFieldException, IllegalAccessException {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Field field = principal.getClass().getDeclaredField("password");
        field.setAccessible(true);
        field.set(principal,"");
        return principal;
    }
}
