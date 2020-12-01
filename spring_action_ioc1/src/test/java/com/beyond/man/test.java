package com.beyond.man;

import com.beyond.man.configer.CDPlayConfiger;
import com.beyond.man.music.CompactDisc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDPlayConfiger.class)
public class test {
    /**
     * @Resource 默认按名称查找。如果没有对应名称的bean则按类型查找，
     * 如果有多个类型则报错
     */
  /*  @Resource
    private CompactDisc sgtPapers;*/

    /**
     *  @Autowired默认按类型查找。如果有多个类型则报错
     *  @Autowired (required = false) 则没有匹配类型不装配。
     *  不会报错
     *  @Qualifier 配合@Autowired使用。如果有多个类型
     *  则按默认值sptPapers名称查找，必须有默认值sptPapers
     */
    @Autowired
    @Qualifier("sptPapers")
    private CompactDisc sptPapers;

    @Test
    public void cdShuldNotBeNull() {
        System.out.println("compactDisc = " + sptPapers);
    }



}
