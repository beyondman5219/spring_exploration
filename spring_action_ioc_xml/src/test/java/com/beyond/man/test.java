package com.beyond.man;

import com.beyond.man.config.CDPlayConfig;
import com.beyond.man.music.CompactDisc;
import com.beyond.man.music.impl.SgtPapers;
import com.beyond.man.play.MusicPlay;
import com.beyond.man.utils.SpringContextUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.lang.reflect.Constructor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDPlayConfig.class)

public class test {
    /**
     * @Resource 默认按名称查找。如果没有对应名称的bean则按类型查找，
     * 如果有多个类型则报错
     */
  /*  @Resource
    private CompactDisc sgtPapers;*/

    /**
     * @Autowired默认按类型查找。如果有多个类型则报错
     * @Qualifier 配合@Autowired使用。如果有多个类型
     * 则按默认值sptPapers名称查找，必须有默认值sptPapers
     */
    @Autowired
    @Qualifier("bebePaper")
    private CompactDisc bebePaper;

    @Resource
    private MusicPlay musicPlay;


    @Test
    public void cdShuldNotBeNull() {
        System.out.println("compactDisc = " + bebePaper);
    }

    @Test
    public void MusicPlay() {
        System.out.println("MusicPlay = " + musicPlay);
    }


    @Test
    public void TestSpringContext() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("SpringBean2.xml");
        Object sptPapers = applicationContext.getBean("sptPapers");
        System.out.println("sptPapers = " + sptPapers);
    }

    @Test
    public void TestBeanUtills() throws NoSuchMethodException {
        /**
         * spring 框架提供的工具类ClassUtils、ObjectUtils、ReflectionUtils、
         */
        //IOC最顶层调用反射实现 BeanUtils
        Constructor<SgtPapers> constructor = SgtPapers.class.getConstructor();
        SgtPapers sgtPapers = BeanUtils.instantiateClass(constructor);
        System.out.println("sptPapers = " + sgtPapers);
    }

}
