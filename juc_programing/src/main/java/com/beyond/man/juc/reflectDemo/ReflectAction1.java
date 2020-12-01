package com.beyond.man.juc.reflectDemo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectAction1 {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("com.beyond.man.juc.reflectDemo.SellerService1");
        SellerService1 sellerService = (SellerService1) clazz.newInstance();
        Class<?> clazzUserService = Class.forName("com.beyond.man.juc.reflectDemo.UserService");
        UserService userServiceInstance = (UserService) clazzUserService.newInstance();

        Field userService = clazz.getDeclaredField("userService");

        userService.setAccessible(true);
        //setUserService
        String name = userService.getName();
        String subString = name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
        String methodName = "set" + subString;
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            if (methodName.equals(declaredMethod.getName())) {
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(sellerService, userServiceInstance);
            }
        }
        System.out.println(sellerService.getUserService());
    }
}
