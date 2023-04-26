package io.kungfu.admin;

import io.kungfu.admin.config.AppConfig;
import com.jfinal.server.undertow.UndertowServer;

/**
 * 应用程序启动类
 *
 * @author yfq
 */
public class Application {
    public static void main(String[] args) {
        UndertowServer.start(AppConfig.class);
    }
}
