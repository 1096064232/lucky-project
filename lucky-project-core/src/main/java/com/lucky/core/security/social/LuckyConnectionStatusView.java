package com.lucky.core.security.social;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucky.common.util.CommonUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 社交登录的解绑和绑定
 */
@Component("connect/status")
public class LuckyConnectionStatusView extends AbstractView {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ObjectMapper objectMapper;

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        Map<String, List<Connection<?>>> connection = (Map<String, List<Connection<?>>>) model.get("connectionMap");
        for (String key:connection.keySet()) {
            logger.debug("key is :{},value is:{}",key, CommonUtils.toString(connection.get(key)));
        }
        Map<String, Boolean> result  = new HashMap<>();
        for (String key:connection.keySet()) {
            result.put(key, CollectionUtils.isNotEmpty(connection.get(key)));
        }
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
