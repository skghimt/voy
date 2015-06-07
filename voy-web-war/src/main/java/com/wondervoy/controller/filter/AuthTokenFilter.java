package com.wondervoy.controller.filter;

import cn.wondervoy.facade.IUserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ckzhang on 14-11-6.
 */
@Service("AuthTokenFilter")
public class AuthTokenFilter implements Filter {

    @Autowired
    private IUserFacade userFacade;

    private String authUrl = null;

    private List<String> needUrls = null;

    private String encode="UTF-8";

    /**
     * Called by the web container to indicate to a filter that it is
     * being placed into service.
     * <p/>
     * <p>The servlet container calls the init
     * method exactly once after instantiating the filter. The init
     * method must complete successfully before the filter is asked to do any
     * filtering work.
     * <p/>
     * <p>The web container cannot place the filter into service if the init
     * method either
     * <ol>
     * <li>Throws a ServletException
     * <li>Does not return within a time period defined by the web container
     * </ol>
     *
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        this.authUrl = filterConfig.getInitParameter("notAuthUrl");
        if (needUrls == null){
            needUrls = new ArrayList<String>();
            String[] urls = authUrl.split(",");
            for (String item : urls){
                needUrls.add(item);
            }
        }

    }

    /**
     * The <code>doFilter</code> method of the Filter is called by the
     * container each time a request/response pair is passed through the
     * chain due to a client request for a resource at the end of the chain.
     * The FilterChain passed in to this method allows the Filter to pass
     * on the request and response to the next entity in the chain.
     * <p/>
     * <p>A typical implementation of this method would follow the following
     * pattern:
     * <ol>
     * <li>Examine the request
     * <li>Optionally wrap the request object with a custom implementation to
     * filter content or headers for input filtering
     * <li>Optionally wrap the response object with a custom implementation to
     * filter content or headers for output filtering
     * <li>
     * <ul>
     * <li><strong>Either</strong> invoke the next entity in the chain
     * using the FilterChain object
     * (<code>chain.doFilter()</code>),
     * <li><strong>or</strong> not pass on the request/response pair to
     * the next entity in the filter chain to
     * block the request processing
     * </ul>
     * <li>Directly set headers on the response after invocation of the
     * next entity in the filter chain.
     * </ol>
     *
     * @param request
     * @param response
     * @param chain
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        req.getAuthType();


        String url = req.getServletPath();

        if(needToken(url))
        {

            try {

                String voySession = ((HttpServletRequest) request).getHeader("voySession");


                if (voySession != null) {
                    long userId = userFacade.getUserIdBySession(voySession);
                    if (userId == 0){
                        ((HttpServletResponse) response).setStatus(401);
                        return ;
                    }
                } else {
                    ((HttpServletResponse) response).setStatus(401);
                    return ;
                }



            }catch (Exception e){

                return;
            }

        }


        chain.doFilter(request, response);
        return;

    }

    /**
     * Called by the web container to indicate to a filter that it is being
     * taken out of service.
     * <p/>
     * <p>This method is only called once all threads within the filter's
     * doFilter method have exited or after a timeout period has passed.
     * After the web container calls this method, it will not call the
     * doFilter method again on this instance of the filter.
     * <p/>
     * <p>This method gives the filter an opportunity to clean up any
     * resources that are being held (for example, memory, file handles,
     * threads) and make sure that any persistent state is synchronized
     * with the filter's current state in memory.
     */
    @Override
    public void destroy() {
    }


    private boolean needToken(String url){

        if (needUrls.contains(url)){
            return true;
        }

        return false;
    }
}
