package com.lucky.common.util;

import java.io.IOException;
import java.io.Writer;

import com.lucky.common.exception.CommonException;
import org.springframework.util.Assert;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *  JSON工具类
 */
public class JsonUtils {


    /** ObjectMapper */
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * 不可实例化
     */
    private JsonUtils() {
    }

    /**
     * 将对象转换为JSON字符串
     *
     * @param value
     *            对象
     * @return JSOn字符串
     */
    public static String toJson(Object value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (Exception e) {
            throw new CommonException(e);
        }
    }

    /**
     * 将JSON字符串转换为对象
     *
     * @param json
     *            JSON字符串
     * @param valueType
     *            对象类型
     * @return 对象
     */
    public static <T> T toObject(String json, Class<T> valueType) {
        Assert.hasText(json,"json字符串参数必须有值不能为null或空值");
        Assert.notNull(valueType,"valueType是必需的；不能为空");
        try {
            return mapper.readValue(json, valueType);
        } catch (Exception e) {
            throw new CommonException(e);
        }
    }

    /**
     * 将JSON字符串转换为对象
     *
     * @param json
     *            JSON字符串
     * @param typeReference
     *            对象类型
     * @return 对象
     */
    public static <T> T toObject(String json, TypeReference<?> typeReference) {
        Assert.hasText(json,"json字符串参数必须有值不能为null或空值");
        Assert.notNull(typeReference,"typeReference；不能为空");
        try {
            return mapper.readValue(json, typeReference);
        } catch (Exception e) {
            throw new CommonException(e);
        }
    }

    /**
     * 将JSON字符串转换为对象
     *
     * @param json
     *            JSON字符串
     * @param javaType
     *            对象类型
     * @return 对象
     */
    public static <T> T toObject(String json, JavaType javaType) {
        Assert.hasText(json,"json字符串参数必须有值不能为null或空值");
        Assert.notNull(javaType,"javaType；不能为空");
        try {
            return mapper.readValue(json, javaType);
        } catch (Exception e) {
            throw new CommonException(e);
        }
    }

    /**
     * 将对象转换为JSON流
     *
     * @param writer
     *            writer
     * @param value
     *            对象
     */
    public static void writeValue(Writer writer, Object value) {
        try {
            mapper.writeValue(writer, value);
        } catch (JsonGenerationException e) {
            throw new CommonException(e);
        } catch (JsonMappingException e) {
            throw new CommonException(e);
        } catch (IOException e) {
            throw new CommonException(e);
        }
    }
}
