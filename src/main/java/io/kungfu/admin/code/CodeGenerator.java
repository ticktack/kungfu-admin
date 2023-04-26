package io.kungfu.admin.code;

import org.kungfu.generator.KungfuGenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * 代码生成器
 * 使用说明详见：<a href="https://kungfu.wang/guide/code-generator.html#kungfu-code-generator-use-config">配置说明</a>
 */
public class CodeGenerator {

    public static void main(String[] args) {
        String databaseHost = "127.0.0.1:3306";
        String databaseName = "youya_gen";
        String username = "root";
        String password = "Mysql_20210513";

        String basePackage = "io.kungfu.panda";
        String moduleName = "sys";

        String includeTables = "sys_api";
        String excludeTables = "";

        String[] genLayers = new String[]{"base", "model", "dto", "validate", "service", "controller"};
        //genLayers = new String[]{"dto"};
        //genLayers = new String[]{"service", "controller"};
        //genLayers = new String[]{"base", "model", "dto", "validate"};
        //genLayers = new String[]{"dto", "validate"};
        //genLayers = new String[]{"validate"};
        //genLayers = new String[]{"web_api"};

        // 自定义模板
        Map<String, String> templateMap = new HashMap<>();
        templateMap.put("base", "code/basemodel.tpl");
        templateMap.put("model", "code/model.tpl");
        templateMap.put("dto", "code/dto.tpl");
        templateMap.put("validate", "code/validate.tpl");
        templateMap.put("service", "code/service.tpl");
        templateMap.put("controller", "code/controller.tpl");
        //templateMap.put("service", "code/tree_service.tpl");
        //templateMap.put("controller", "code/tree_controller.tpl");
        templateMap.put("web_api", "code/web_api.tpl");
        templateMap.put("web_index", "code/web_index.tpl");
        templateMap.put("web_popup", "code/web_popup.tpl");

        KungfuGenerator generator = new KungfuGenerator();

        generator.init(databaseHost, databaseName, username, password);

        generator.doGenerate(databaseName, basePackage, moduleName, includeTables, excludeTables, genLayers, templateMap);

    }
}
