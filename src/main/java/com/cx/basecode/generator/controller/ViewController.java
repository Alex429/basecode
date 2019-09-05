package com.cx.basecode.generator.controller;

import com.cx.basecode.common.entity.MyConstant;
import com.cx.basecode.common.util.MyUtil;
import com.cx.basecode.generator.entity.GeneratorConfig;
import com.cx.basecode.generator.service.IGeneratorConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面视图 待完善
 *
 * @author: cx
 * @date: 2019/9/5
 */
@Controller("generatorViews")
@RequestMapping(MyConstant.VIEW_PREFIX + "generator")
public class ViewController {
    @Autowired
    private IGeneratorConfigService generatorConfigService;

    @GetMapping("generator")
    public String generator() {
        return MyUtil.view("generator/generator");
    }

    @GetMapping("configure")
    public String generatorConfigure(Model model) {
        GeneratorConfig generatorConfig = generatorConfigService.findGeneratorConfig();
        model.addAttribute("config", generatorConfig);
        return MyUtil.view("generator/configure");
    }

}
