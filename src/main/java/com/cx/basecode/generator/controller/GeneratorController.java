package com.cx.basecode.generator.controller;

import com.cx.basecode.common.controller.BaseController;
import com.cx.basecode.common.entity.BaseResponse;
import com.cx.basecode.common.entity.QueryRequest;
import com.cx.basecode.common.exception.MyException;
import com.cx.basecode.common.utils.FileUtil;
import com.cx.basecode.common.utils.MyUtil;
import com.cx.basecode.generator.entity.Column;
import com.cx.basecode.generator.entity.GeneratorConfig;
import com.cx.basecode.generator.entity.GeneratorConstant;
import com.cx.basecode.generator.helper.GeneratorHelper;
import com.cx.basecode.generator.service.IGeneratorConfigService;
import com.cx.basecode.generator.service.IGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * @author: cx
 * @date: 2019/9/4
 */
@Slf4j
@RestController
@RequestMapping("generator")
public class GeneratorController extends BaseController {
    private static final String SUFFIX = "_code.zip";

    @Autowired
    private IGeneratorService generatorService;
    @Autowired
    private IGeneratorConfigService generatorConfigService;
    @Autowired
    private GeneratorHelper generatorHelper;

    @GetMapping("tables/info")
    public BaseResponse tablesInfo(String tableName, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(generatorService.getTables(tableName, request, GeneratorConstant.DATABASE_TYPE, GeneratorConstant.DATABASE_NAME));
        return new BaseResponse().success().data(dataTable);
    }

    @GetMapping
    public void generate(@NotBlank(message = "{required}") String name, String remark, HttpServletResponse response) {
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

            // 打包
            String zipFile = System.currentTimeMillis() + SUFFIX;
            FileUtil.compress(GeneratorConstant.TEMP_PATH + "src", zipFile);
            // 下载
            FileUtil.download(zipFile, name + SUFFIX, true, response);
            // 删除临时目录
            FileUtil.delete(GeneratorConstant.TEMP_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
