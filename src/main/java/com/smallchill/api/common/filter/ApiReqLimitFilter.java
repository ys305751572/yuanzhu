package com.smallchill.api.common.filter;


import com.smallchill.core.constant.ConstCache;
import com.smallchill.core.toolbox.kit.CacheKit;
import com.smallchill.core.toolbox.kit.NetKit;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * API请求限制
 * Created by yesong on 2016/9/27.
 */
public class ApiReqLimitFilter implements Filter,ConstCache {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String ip = NetKit.getRealIp((HttpServletRequest) servletRequest);

    }

    @Override
    public void destroy() {

    }
}
