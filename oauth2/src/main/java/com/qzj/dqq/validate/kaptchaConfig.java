package com.qzj.dqq.validate;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author qzj 2017年11月28日
 */
@Configuration
public class kaptchaConfig {
    @Bean(name = "captchaProducer")
    public DefaultKaptcha getKaptchaBean() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", "yes");
        properties.setProperty("kaptcha.border.color", "220,220,220");
        properties.setProperty("kaptcha.textproducer.font.color", "0,134,139");
        properties.setProperty("kaptcha.image.width", "125");
        properties.setProperty("kaptcha.image.height", "36");
        properties.setProperty("kaptcha.session.key", "code");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        properties.setProperty("kaptcha.textproducer.char.string","1234560789");
        properties.setProperty("kaptcha.textproducer.font.names", "Console");
        properties.setProperty("kaptcha.textproducer.font.size", "36");
        properties.setProperty("kaptcha.textproducer.char.space", "8");//文字间隔
        properties.setProperty("kaptcha.noise.color", "70,130,180");//干扰颜色
        /**
         * 图片样式： 水纹com.google.code.kaptcha.impl.WaterRipple
         鱼眼com.google.code.kaptcha.impl.FishEyeGimpy
         阴影com.google.code.kaptcha.impl.ShadowGimpy
         */
        properties.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.WaterRipple");
        properties.setProperty("kaptcha.word.impl", "com.google.code.kaptcha.text.impl.DefaultWordRenderer");//文字渲染器
        properties.setProperty("kaptcha.background.clear.from", "220,220,220");//背景颜色渐变，开始颜色
        properties.setProperty("kaptcha.background.clear.to", "white");//背景颜色渐变，结束颜色
        properties.setProperty("kaptcha.textproducer.impl", "com.google.code.kaptcha.text.impl.DefaultTextCreator");//文本实现类
        properties.setProperty("kaptcha.background.impl", "com.google.code.kaptcha.impl.DefaultBackground");//背景实现类
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.DefaultNoise");//干扰实现类
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}
