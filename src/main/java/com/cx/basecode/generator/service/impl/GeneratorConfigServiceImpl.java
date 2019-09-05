package com.cx.basecode.generator.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cx.basecode.generator.entity.GeneratorConfig;
import com.cx.basecode.generator.mapper.GeneratorConfigMapper;
import com.cx.basecode.generator.service.IGeneratorConfigService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: cx
 * @date: 2019/9/4
 */
@Service
public class GeneratorConfigServiceImpl extends ServiceImpl<GeneratorConfigMapper, GeneratorConfig> implements IGeneratorConfigService {
    @Override
    public GeneratorConfig findGeneratorConfig() {
        List<GeneratorConfig> generatorConfigs = this.baseMapper.selectList(null);
        return CollectionUtils.isNotEmpty(generatorConfigs) ? generatorConfigs.get(0) : null;
    }

    @Override
    public void updateGeneratorConfig(GeneratorConfig generatorConfig) {
        this.saveOrUpdate(generatorConfig);
    }
}
