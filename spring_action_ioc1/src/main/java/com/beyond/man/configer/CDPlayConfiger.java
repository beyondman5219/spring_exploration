package com.beyond.man.configer;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 组件扫描默认是不启用的，我们需要显示的配置
 */
@Configuration
//如果配置basePackages 默认之后扫描与配置类相同的包
@ComponentScan(basePackages = "com.beyond")
public class CDPlayConfiger {
}
