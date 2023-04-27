package io.kungfu.admin.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.render.JsonRender;
import io.kungfu.admin.common.LogKit;
import io.kungfu.admin.modules.system.model.SysLog;
import io.kungfu.admin.util.IpKit;
import org.kungfu.core.KungfuConstant;
import org.kungfu.util.DateKit;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

public class LogInterceptor  implements Interceptor {

    public static JSONObject sendGet(String url) {
        JSONObject jsonObject = null;
        StringBuilder sb = new StringBuilder();
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setConnectTimeout(10000);
            // 建立实际的连接
            conn.connect();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            jsonObject = JSON.parseObject(sb.toString());
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                System.out.println("关闭流异常");
            }
        }
        return jsonObject;
    }
    private String getAddress(String ip) {
        // 缓存
        return CacheKit.get("ipAddress", ip, () -> {
            JSONObject jsonObject = sendGet(String.format("https://api.map.baidu.com/location/ip?ak=%s&ip=%s&coor=bd09ll", PropKit.get("ak"), ip));
            JSONObject contentJsonObject = jsonObject.getJSONObject("content");

            if (contentJsonObject == null) {
                return "未知地区";
            }
            return contentJsonObject.getString("address");
        });

    }
    @Override
    public void intercept(Invocation inv) {
        Controller controller = inv.getController();
        String method = controller.getRequest().getMethod();
        // sys log
        SysLog log = new SysLog();
        String visitor = controller.getHeader("userName");
        String visitorIdStr = controller.getHeader("userId");
        long visitorId = 0L;
        try {
            if (StrKit.notBlank(visitor)) {
                visitor = URLDecoder.decode(visitor, KungfuConstant.UTF8);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (StrKit.notBlank(visitorIdStr)) {
            visitorId = Long.parseLong(visitorIdStr);
        }
        log.setVisitorId(visitorId);
        log.setVisitor(StrKit.isBlank(visitor) ? "游客" : visitor);
        log.setApiUrl(controller.getViewPath() + inv.getMethodName());
        String methodName = inv.getMethodName();
        log.setApiMethod(methodName);
        log.setApiType(method);
        String ip = IpKit.getIpAddr(controller.getRequest());
        log.setVisitPort(controller.getRequest().getLocalPort());
        log.setVisitIp(ip);


        if (method.equals(KungfuConstant.HTTP_METHOD_POST)) {
            String json = controller.getAttr(KungfuConstant.JSON_REQUEST_BODY);
            log.setApiParam(json);
        }
        else {
            log.setApiParam(controller.getKv().toJson());
        }
        log.setCreateYear(DateKit.getYear());
        log.setCreateMonth(DateKit.getMonth());
        log.setCreateDay(DateKit.getDay());
        log.setCreateTime(new Date());
        log.setRemark("");
        // sys log
        String remark = LogKit.logRemark(log.getApiUrl());
        if (StrKit.notBlank(remark)) {
            log.setRemark(remark);
        }
        HttpServletRequest request = controller.getRequest();
        long startTime = (Long) request.getAttribute(KungfuConstant.START_TIME);
        inv.invoke();

        if (controller.getViewPath().contains("/swagger/")) {

            log.setApiResult("");
        }
        else {
            String jsonStr = ((JsonRender) inv.getController().getRender()).getJsonText();

            log.setApiResult(jsonStr);
        }

        long endTime = System.currentTimeMillis();
        log.setApiCost(endTime - startTime);

        System.out.println("cost " + log.getApiCost() + "ms");

        // 异步方式
        CompletableFuture.runAsync(() -> {
            try {
                // 异步调用日志保存
                //log.setAddress(getAddress(ip));
                log.save();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }
}
