package com.cx.basecode.system.controller;

import com.cx.basecode.common.annotation.Log;
import com.cx.basecode.common.utils.MyUtil;
import com.cx.basecode.common.entity.MyConstant;
import com.cx.basecode.common.controller.BaseController;
import com.cx.basecode.common.entity.BaseResponse;
import com.cx.basecode.common.entity.QueryRequest;
import com.cx.basecode.common.exception.MyException;
import ${basePackage}.${entityPackage}.${className};
import ${basePackage}.${servicePackage}.I${className}Service;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * ${tableComment} Controller
 *
 * @author ${author}
 * @date ${date}
 */
@Slf4j
@Validated
@Controller
public class ${className}Controller extends BaseController {

    @Autowired
    private I${className}Service ${className?uncap_first}Service;

    @GetMapping(MyConstant.VIEW_PREFIX + "${className?uncap_first}")
    public String ${className?uncap_first}Index(){
        return MyUtil.view("${className?uncap_first}/${className?uncap_first}");
    }

    @GetMapping("${className?uncap_first}")
    @ResponseBody
    @RequiresPermissions("${className?uncap_first}:list")
    public BaseResponse getAll${className}s(${className} ${className?uncap_first}) {
        return new BaseResponse().success().data(${className?uncap_first}Service.find${className}s(${className?uncap_first}));
    }

    @GetMapping("${className?uncap_first}/list")
    @ResponseBody
    @RequiresPermissions("${className?uncap_first}:list")
    public BaseResponse ${className?uncap_first}List(QueryRequest request, ${className} ${className?uncap_first}) {
        Map<String, Object> dataTable = getDataTable(this.${className?uncap_first}Service.find${className}s(request, ${className?uncap_first}));
        return new BaseResponse().success().data(dataTable);
    }

    @Log("新增${className}")
    @PostMapping("${className?uncap_first}")
    @ResponseBody
    @RequiresPermissions("${className?uncap_first}:add")
    public BaseResponse add${className}(@Valid ${className} ${className?uncap_first}) throws MyException {
        try {
            this.${className?uncap_first}Service.create${className}(${className?uncap_first});
            return new BaseResponse().success();
        } catch (Exception e) {
            String message = "新增${className}失败";
            log.error(message, e);
            throw new MyException(message);
        }
    }

    @Log("删除${className}")
    @GetMapping("${className?uncap_first}/delete")
    @ResponseBody
    @RequiresPermissions("${className?uncap_first}:delete")
    public BaseResponse delete${className}(${className} ${className?uncap_first}) throws MyException {
        try {
            this.${className?uncap_first}Service.delete${className}(${className?uncap_first});
            return new BaseResponse().success();
        } catch (Exception e) {
            String message = "删除${className}失败";
            log.error(message, e);
            throw new MyException(message);
        }
    }

    @Log("修改${className}")
    @PostMapping("${className?uncap_first}/update")
    @ResponseBody
    @RequiresPermissions("${className?uncap_first}:update")
    public BaseResponse update${className}(${className} ${className?uncap_first}) throws MyException {
        try {
            this.${className?uncap_first}Service.update${className}(${className?uncap_first});
            return new BaseResponse().success();
        } catch (Exception e) {
            String message = "修改${className}失败";
            log.error(message, e);
            throw new MyException(message);
        }
    }

    @PostMapping("${className?uncap_first}/excel")
    @ResponseBody
    @RequiresPermissions("${className?uncap_first}:export")
    public void export(QueryRequest queryRequest, ${className} ${className?uncap_first}, HttpServletResponse response) throws MyException {
        try {
            List<${className}> ${className?uncap_first}s = this.${className?uncap_first}Service.find${className}s(queryRequest, ${className?uncap_first}).getRecords();
            ExcelKit.$Export(${className}.class, response).downXlsx(${className?uncap_first}s, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new MyException(message);
        }
    }
}
