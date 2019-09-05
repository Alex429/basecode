package com.cx.basecode.generator;

import com.cx.basecode.common.exception.MyException;
import com.cx.basecode.common.util.FileUtil;
import com.cx.basecode.common.util.MyUtil;
import com.cx.basecode.generator.entity.Column;
import com.cx.basecode.generator.entity.GeneratorConfig;
import com.cx.basecode.generator.entity.GeneratorConstant;
import com.cx.basecode.generator.helper.GeneratorHelper;
import com.cx.basecode.generator.service.IGeneratorConfigService;
import com.cx.basecode.generator.service.IGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author: alex
 * @date: 2019/9/5
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class GeneratorTable {
    private static final String SUFFIX = "_code.zip";

    @Autowired
    private IGeneratorConfigService generatorConfigService;
    @Autowired
    private GeneratorHelper generatorHelper;
    @Autowired
    private IGeneratorService generatorService;

    /**
     * 警告！！！
     * 直接生成代码放入项目
     */
    @Test
    public void generatorTable() {
        String name = "t_user";
        String remark = "";
        if (StringUtils.isBlank(name)) {
            return;
        }
        try {
            GeneratorConfig generatorConfig = generatorConfigService.findGeneratorConfig();
            if (generatorConfig == null) {
                throw new MyException("代码生成配置为空");
            }

            String className = name;
            if (GeneratorConfig.TRIM_YES.equals(generatorConfig.getIsTrim())) {
                className = name.replaceFirst(generatorConfig.getTrimValue(), "");
            }

            generatorConfig.setTableName(name);
            generatorConfig.setClassName(MyUtil.underscoreToCamel(className));
            generatorConfig.setTableComment(remark);
            // 生成代码到临时目录
            List<Column> columns = generatorService.getColumns(GeneratorConstant.DATABASE_TYPE, GeneratorConstant.DATABASE_NAME, name);
            generatorHelper.generateEntityFile(columns, generatorConfig);
            generatorHelper.generateMapperFile(columns, generatorConfig);
            generatorHelper.generateMapperXmlFile(columns, generatorConfig);
            generatorHelper.generateServiceFile(columns, generatorConfig);
            generatorHelper.generateServiceImplFile(columns, generatorConfig);
            generatorHelper.generateControllerFile(columns, generatorConfig);

            log.info("代码生成完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
