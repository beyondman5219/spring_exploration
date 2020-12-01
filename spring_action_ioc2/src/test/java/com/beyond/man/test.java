package com.beyond.man;

import com.beyond.man.config.CDPlayConfig;
import com.beyond.man.music.CompactDisc;
import com.beyond.man.play.MusicPlay;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

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
    @Qualifier("sptPapers")
    private CompactDisc sptPapers;

    @Resource
    private MusicPlay musicPlay;

    @Test
    public void cdShuldNotBeNull() {
        System.out.println("compactDisc = " + sptPapers);
    }

    @Test
    public void MusicPlay() {
        System.out.println("MusicPlay = " + musicPlay);
    }
}
