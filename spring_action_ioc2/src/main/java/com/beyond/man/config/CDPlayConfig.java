package com.beyond.man.config;

import com.beyond.man.music.CompactDisc;
import com.beyond.man.music.impl.SgtPapers;
import com.beyond.man.music.impl.SptPapers;
import com.beyond.man.play.MusicPlay;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 使用配置类的方式自动发现和装配Bean
 */
@Configuration
@ComponentScan(basePackages = "com.beyond")
public class CDPlayConfig {

    @Bean("sptPapers")
    public CompactDisc sgtPapers() {
        return new SgtPapers();
    }
    @Bean("sptPapers")
    public CompactDisc sptPapers() {
        return new SptPapers();
    }

    /**
     * 如果参数beand有多类型匹配则需要@Qualifier指定beanName
     * @param compactDisc
     * @return
     */
    @Bean("musicPlay")
    public MusicPlay musicPlay(@Qualifier("sptPapers") CompactDisc compactDisc) {
        return new MusicPlay(compactDisc);
    }
}
