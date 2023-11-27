package com.hanxi.spzx.common.config.properties;

import com.hanxi.spzx.common.enums.CaptchaCategory;
import com.hanxi.spzx.common.enums.CaptchaType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "captcha")
public class CaptchaProperties {

    /**
     * 验证码类型
     */
    private CaptchaType type;

    /**
     * 验证码类别
     */
    private CaptchaCategory category;

    /**
     * 数字验证码位数
     */
    private Integer numberLength;

    /**
     * 字符验证码长度
     */
    private Integer charLength;
    /**
     * 验证码宽度
     */
    private Integer width;
    /**
     * 验证码高度
     */
    private Integer height;
}
