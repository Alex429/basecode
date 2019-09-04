package com.cx.basecode.common.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cx.basecode.common.entity.MyConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: cx
 * @date: 2019/9/4
 */
public class BaseController {

    protected Map<String, Object> getDataTable(IPage<?> pageInfo) {
        return getDataTable(pageInfo, MyConstant.DATA_MAP_INITIAL_CAPACITY);
    }

    protected Map<String, Object> getDataTable(IPage<?> pageInfo, int dataMapInitialCapacity) {
        Map<String, Object> data = new HashMap<>(dataMapInitialCapacity);
        data.put("rows", pageInfo.getRecords());
        data.put("total", pageInfo.getTotal());
        return data;
    }
}
