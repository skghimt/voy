package com.wondervoy.controller.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by ckzhang on 15-5-24.
 */
public class SetCharacterEncodingFilter implements Filter {
    protected String encoding = "UTF-8";

    protected FilterConfig filterConfig = null;

    protected boolean ignore = true;

    public void destroy() {
        this.encoding = null;
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        // 有条件地选择设置字符编码使用
        if (ignore || (request.getCharacterEncoding() == null)) {
            String encoding = this.encoding;
            if (encoding != null)
                request.setCharacterEncoding(encoding);
        }

        chain.doFilter(request, response);

    }

    public void init(FilterConfig filterConfig) throws ServletException {

        this.filterConfig = filterConfig;
        this.encoding = filterConfig.getInitParameter("encoding");
        String value = filterConfig.getInitParameter("ignore");
        if (value == null)
            this.ignore = true;
        else if (value.equalsIgnoreCase("true"))
            this.ignore = true;
        else if (value.equalsIgnoreCase("yes"))
            this.ignore = true;
        else
            this.ignore = false;

    }
}
