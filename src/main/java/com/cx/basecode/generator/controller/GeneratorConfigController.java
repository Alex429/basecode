package com.cx.basecode.generator.controller;

import com.cx.basecode.common.entity.BaseResponse;
import com.cx.basecode.generator.service.IGeneratorConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: cx
 * @date: 2019/9/4
 */
@Slf4j
@RestController
@RequestMapping("generatorConfig")
public class GeneratorConfigController {
    @Autowired
    IGeneratorConfigService generatorConfigService;

    @GetMapping
    public BaseResponse getGeneratorConfig() {
        return new BaseResponse().success().data(generatorConfigService.findGeneratorConfig());
    }

}
