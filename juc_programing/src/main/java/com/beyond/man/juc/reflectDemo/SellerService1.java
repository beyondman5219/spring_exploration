package com.beyond.man.juc.reflectDemo;


public class SellerService1 {

    private UserService userService;

    private void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }
}
