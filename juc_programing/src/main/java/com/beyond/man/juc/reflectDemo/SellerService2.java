package com.beyond.man.juc.reflectDemo;

public class SellerService2 {
    @AutoWired
    private UserService userService;


    public UserService getUserService() {
        return userService;
    }
}
