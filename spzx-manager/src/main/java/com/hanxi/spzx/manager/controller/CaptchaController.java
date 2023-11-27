package com.hanxi.spzx.manager.controller;


import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.generator.CodeGenerator;
import cn.hutool.core.util.IdUtil;
import com.hanxi.spzx.common.config.properties.CaptchaProperties;
import com.hanxi.spzx.common.constant.GlobalConstants;
import com.hanxi.spzx.common.enums.CaptchaType;
import com.hanxi.spzx.common.utils.ReflectUtils;
import com.hanxi.spzx.model.vo.common.R;
import com.hanxi.spzx.model.vo.system.ValidateCodeVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 验证码操作处理
 */

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/admin/system/index")
public class CaptchaController {

    private final CaptchaProperties captchaProperties;
    private final RedisTemplate<String , String> redisTemplate ;

    /**
     * 生成验证码
     */
    @GetMapping("/generateValidateCode")
    public R<ValidateCodeVo> getCode() {
        ValidateCodeVo ValidateCodeVo = new ValidateCodeVo();
        // 保存验证码信息
        String uuid = IdUtil.simpleUUID();
        String verifyKey = GlobalConstants.CAPTCHA_CODE_KEY + uuid;
        // 生成验证码
        CaptchaType captchaType = captchaProperties.getType();
        boolean isMath = CaptchaType.MATH == captchaType;
        Integer length = isMath ? captchaProperties.getNumberLength() : captchaProperties.getCharLength();
        CodeGenerator codeGenerator = ReflectUtils.newInstance(captchaType.getClazz(), length);

//        //获取Bean出了问题，
//        AbstractCaptcha captcha = SpringUtils.getBean(captchaProperties.getCategory().getClazz());
//        //通过switch手动创建吧，但不够优雅，
//        AbstractCaptcha captcha = null;
//        switch (captchaProperties.getCategory()){
//            case LINE -> captcha = new LineCaptcha(150,48);
//            case SHEAR -> captcha = new ShearCaptcha(150,48);
//            case CIRCLE -> captcha = new CircleCaptcha(150,48);
//        }
        //于是通过反射创建
        AbstractCaptcha captcha = ReflectUtils.newInstance(captchaProperties.getCategory().getClazz(),
                captchaProperties.getWidth(),captchaProperties.getHeight());

        captcha.setGenerator(codeGenerator);
        captcha.createCode();
        String code = captcha.getCode();
        if (isMath) {
            ExpressionParser parser = new SpelExpressionParser();
            Expression exp = parser.parseExpression(StringUtils.remove(code, "="));
            code = exp.getValue(String.class);
        }
        if (code != null) {
            redisTemplate.opsForValue().set(verifyKey , code , 2 , TimeUnit.MINUTES);
        }
        ValidateCodeVo.setCodeKey(uuid);
        ValidateCodeVo.setCodeValue(GlobalConstants.VERIFICATION_CODE_PREFIX+captcha.getImageBase64());
        return R.ok(ValidateCodeVo);
    }

}
