package io.kungfu.admin.config;

import com.jfinal.aop.Aop;
import com.jfinal.plugin.ehcache.CacheKit;
import io.kungfu.admin.common.Constant;
import io.kungfu.admin.handler.TimeHandler;
import io.kungfu.admin.interceptor.LogInterceptor;
import com.jfinal.config.*;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.SqlReporter;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.template.Engine;
import com.lastb7.swagger.handler.SwaggerHandler;
import com.lastb7.swagger.plugin.SwaggerPlugin;
import com.lastb7.swagger.routes.SwaggerRoutes;
import io.kungfu.admin.modules.system.service.SysDictItemService;
import io.kungfu.admin.modules.system.service.SysMenuService;
import org.kungfu.interceptor.AuthInterceptor;
import org.kungfu.interceptor.PostRequestInterceptor;
import org.kungfu.validator.HeaderValidator;


/**
 * API 引导式配置
 */
public class AppConfig extends JFinalConfig {

    private static Prop prop;

    private static final SysMenuService menuService = Aop.get(SysMenuService.class);

    private static final SysDictItemService dictItemService = Aop.get(SysDictItemService.class);

    /**
     * PropKit.useFirstFound(...) 使用参数中从左到右最先被找到的配置文件
     * 从左到右依次去找配置，找到则立即加载并立即返回，后续配置将被忽略
     */
    static void loadConfig() {
        if (prop == null) {
            prop = PropKit.useFirstFound("app-dev.txt","app-prod.txt");
        }
    }

    @Override
    public void configConstant(Constants me) {
        //加载配置文件
        loadConfig();

        me.setDevMode(true);
        me.setInjectDependency(true);
        me.setMaxPostSize(104857600);

        // 配置对超类中的属性进行注入
        me.setInjectSuperClass(true);
        // 注入Proxy 动态代理
        me.setToCglibProxyFactory();
    }

    //配置路由
    @Override
    public void configRoute(Routes me) {
        me.scan(Constant.MODULE_PACKAGE);

        // swagger路由
        me.add(new SwaggerRoutes());
    }

    @Override
    public void configEngine(Engine engine) {

    }

    // 配置插件
    @Override
    public void configPlugin(Plugins me) {
        // 配置缓存插件
        me.add(new EhCachePlugin());

        // 配置 druid 数据库连接池插件
        DruidPlugin druidPlugin = new DruidPlugin(prop.get("mysql.url"), prop.get("mysql.username"), prop.get("mysql.password").trim());
        me.add(druidPlugin);

        // 配置ActiveRecord插件
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);

        MappingKit.mapping(arp);
        // sql输出到日志
        //SqlReporter.setLog(prop.getBoolean("devMode"));
        //arp.setShowSql(prop.getBoolean("devMode"));
        SqlReporter.setLog(true);
        arp.setShowSql(true);

        me.add(arp);

        //加载定时任务配置
        //Cron4jPlugin cp = new Cron4jPlugin("cron4j.txt", "cron4j");
        //me.add(cp);

        me.add(new SwaggerPlugin());
    }

    // 配置全局拦截器
    @Override
    public void configInterceptor(Interceptors me) {
        me.add(new AuthInterceptor());
        me.add(new PostRequestInterceptor());
        me.add(new HeaderValidator());
        me.add(new LogInterceptor());
    }

    /**
             * 配置处理器
     */
    @Override
    public void configHandler(Handlers me) {
        me.add(new TimeHandler());
        //配置数据库监控
        //me.add(new DruidStatViewHandler("/druid", new DruidConfig()));
        me.add(new SwaggerHandler());
    }

    @Override
    public void onStart() {
        System.out.println("onStart ....");
        System.out.println("loading menu tree into cache ....");
        CacheKit.put("sysMenu", "menuTree", menuService.buildTree(null));
        System.out.println("loading dict data into cache ....");
        dictItemService.initDictCacheData();
    }

    @Override
    public void onStop() {
        System.out.println("onShutdown ....");
    }

}
