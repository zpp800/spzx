package com.hanxi.spzx.manager.controller;

import com.hanxi.spzx.model.vo.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口
 */
@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * 测试接口
     */
    @GetMapping("/test01")
    public Result test(){
        return Result.build(null,200,"测试接口");
    }
}
