package com.cx.basecode.generator.entity;

/**
 * 代码生成常量
 *
 * @author: cx
 * @date: 2019/9/4
 */
public class GeneratorConstant {
    /**
     * 数据库类型
     */
    public static final String DATABASE_TYPE = "mysql";
    /**
     * 数据库名称
     */
    public static final String DATABASE_NAME = "febs_base";

    /**
     * 生成代码的临时目录
     */
    public static final String TEMP_PATH = "febs_gen_temp/";
//    /**
//     * 生成代码的临时目录
//     * 警告，用完必须注释！
//     * 代码直接生成到项目里，仅供开发使用，
//     */
//    public static final String TEMP_PATH = ".";

    /**
     * java类型文件后缀
     */
    public static final String JAVA_FILE_SUFFIX = ".java";
    /**
     * mapper文件类型后缀
     */
    public static final String MAPPER_FILE_SUFFIX = "Mapper.java";
    /**
     * service文件类型后缀
     */
    public static final String SERVICE_FILE_SUFFIX = "Service.java";
    /**
     * service impl文件类型后缀
     */
    public static final String SERVICEIMPL_FILE_SUFFIX = "ServiceImpl.java";
    /**
     * controller文件类型后缀
     */
    public static final String CONTROLLER_FILE_SUFFIX = "Controller.java";
    /**
     * mapper xml文件类型后缀
     */
    public static final String MAPPERXML_FILE_SUFFIX = "Mapper.xml";
    /**
     * entity模板
     */
    public static final String ENTITY_TEMPLATE = "entity.ftl";
    /**
     * mapper模板
     */
    public static final String MAPPER_TEMPLATE = "mapper.ftl";
    /**
     * service接口模板
     */
    public static final String SERVICE_TEMPLATE = "service.ftl";
    /**
     * service impl接口模板
     */
    public static final String SERVICEIMPL_TEMPLATE = "serviceImpl.ftl";
    /**
     * controller接口模板
     */
    public static final String CONTROLLER_TEMPLATE = "controller.ftl";
    /**
     * mapper xml接口模板
     */
    public static final String MAPPERXML_TEMPLATE = "mapperXml.ftl";
}
