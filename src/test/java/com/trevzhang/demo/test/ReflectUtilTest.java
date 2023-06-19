package com.trevzhang.demo.test;

import cn.hutool.core.util.ReflectUtil;
import com.alibaba.fastjson.JSON;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.junit.Test;

/**
 * @author zhangchunguang.zcg
 * @since 2023/6/19 11:12
 */
public class ReflectUtilTest {

    @Test
    public void testInvokeMethod() {
        Method[] methods = ReflectUtil.getMethodsDirectly(ReflectUtilTest.class, false);
        for (Method method : methods) {
            Class<?> returnType = method.getReturnType();
            String methodName = method.getName();
            Class<?>[] parameterTypes = method.getParameterTypes();

            Map<String, Object> valueMap = new HashMap<>();
            valueMap.put("rt", returnType);
            valueMap.put("mn", methodName);
            valueMap.put("pt", JSON.toJSONString(parameterTypes));
            StrSubstitutor strSubstitutor = new StrSubstitutor(valueMap);
            String msg = strSubstitutor.replace("${rt} ${mn}(${pt});");
            System.out.println(msg);
        }
    }

    public void dumb() {

    }
    public Void dumbVoid() {
        return null;
    }
    public String dumbString(String input) {
        return "";
    }

    public int dumbInt(int input) {
        return 0;
    }

    public Integer dumbInteger(Integer input) {
        return null;
    }
}
