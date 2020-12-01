package com.beyond.man.juc.reflectDemo;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * 注解方式实现依赖注入
 */
public class ReflectAction2 {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("com.beyond.man.juc.reflectDemo.SellerService2");
        SellerService2 SellerService2 = (SellerService2) clazz.newInstance();

        Stream.of(clazz.getDeclaredFields()).forEach(filed -> {

            Class<?> type = filed.getType();
            if ("com.beyond.man.juc.reflectDemo.UserService".equals(type.getName())) {
                AutoWired annotation = filed.getAnnotation(AutoWired.class);
                if (Objects.nonNull(annotation)) {
                    try {
                        UserService userService = (UserService) type.newInstance();
                        filed.setAccessible(true);
                        filed.set(SellerService2, userService);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        System.out.println(SellerService2.getUserService());
    }
}
