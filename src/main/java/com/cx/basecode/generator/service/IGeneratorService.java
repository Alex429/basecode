package com.cx.basecode.generator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cx.basecode.common.entity.QueryRequest;
import com.cx.basecode.generator.entity.Column;
import com.cx.basecode.generator.entity.Table;

import java.util.List;

/**
 * @author: cx
 * @date: 2019/9/4
 */
public interface IGeneratorService {
    List<String> getDatabases(String databaseType);

    IPage<Table> getTables(String tableName, QueryRequest request, String databaseType, String schemaName);

    List<Column> getColumns(String databaseType, String schemaName, String tableName);
}
