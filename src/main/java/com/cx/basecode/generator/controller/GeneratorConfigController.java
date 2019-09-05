package com.cx.basecode.generator.controller;

import com.cx.basecode.common.entity.BaseResponse;
import com.cx.basecode.common.entity.MyConstant;
import com.cx.basecode.common.exception.MyException;
import com.cx.basecode.generator.entity.GeneratorConfig;
import com.cx.basecode.generator.service.IGeneratorConfigService;
import freemarker.template.utility.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

    @PostMapping("update")
    public BaseResponse updateGeneratorConfig(@Valid GeneratorConfig generatorConfig) throws MyException {
        try {
            if (StringUtils.isBlank(generatorConfig.getId())) {
                throw new MyException(MyConstant.TIP_ID_CANNOT_BE_NULL);
            }
            this.generatorConfigService.updateGeneratorConfig(generatorConfig);
            return new BaseResponse().success();
        } catch (Exception e) {
            String message = "修改generatorConfig错误";
            log.error(message, e);
            throw new MyException(message);
        }
    }

}
