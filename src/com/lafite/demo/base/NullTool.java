package com.lafite.demo.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;

/**
 * @author LafiteHao
 * @create 2017-05-19 11:52
 **/
public class NullTool {
    public static Object dealNull (Object object) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Class<?> clazz = object.getClass();
        String json = "";
        Method[] methods = clazz.getMethods();
        int length = methods.length;
        for (int i = 0; i < length; i ++ ) {
            Method method = methods[i];
            String methodNmae = method.getName();
            if (methodNmae.contains("get")) {
                String fieldName = methodNmae.substring(3);
                Object result = method.invoke(object);
                if (result == null) {
                    if (method.getReturnType() == String.class) {
                        clazz.getMethod("set" + fieldName, String.class).invoke(object, "");
                    } else if (method.getReturnType() == Integer.class){
                        clazz.getMethod("set" + fieldName, Integer.class).invoke(object, 0);
                    } else if (method.getReturnType() == Character.class){
                        clazz.getMethod("set" + fieldName, Character.class).invoke(object, "".charAt(0));
                    } else if (method.getReturnType() == Date.class){
                        clazz.getMethod("set" + fieldName, Date.class).invoke(object, new Date(new java.util.Date().getTime()));
                    }
                }
            }
        }
        return object;
    }
}
