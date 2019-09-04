package com.cx.basecode.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cx.basecode.generator.entity.GeneratorConfig;

/**
 * @author: cx
 * @date: 2019/9/4
 */
public interface IGeneratorConfigService extends IService<GeneratorConfig> {
    /**
     * 查询
     *
     * @return
     */
    GeneratorConfig findGeneratorConfig();

    /**
     * 修改
     *
     * @param generatorConfig
     */
    void updateGeneratorConfig(GeneratorConfig generatorConfig);
}
