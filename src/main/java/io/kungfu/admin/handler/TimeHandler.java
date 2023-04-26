package io.kungfu.admin.handler;

import com.jfinal.handler.Handler;
import org.kungfu.core.KungfuConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TimeHandler extends Handler {
    
    @Override
    public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
        request.setAttribute(KungfuConstant.START_TIME, System.currentTimeMillis());
        next.handle(target, request, response, isHandled);
    }
}
