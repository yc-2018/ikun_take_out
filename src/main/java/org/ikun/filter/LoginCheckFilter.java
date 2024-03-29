//仰晨study 创建时间2023/6/7 1:27 星期三
package org.ikun.filter;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.ikun.common.BaseContext;
import org.ikun.common.R;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查用户是否登录
 */
@Slf4j
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    /**
     * 路径匹配器，支持通配符
     */
    public static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        log.info("拦截到请求:{},他的URL是{}",request.getRequestURI(),request.getRequestURL());
        /*
        * 1、获取本次请求的URI
        * 2、判断本次请求是否需要处理
        * 3、如果不需要处理，则直接放行
        * 4、判断登录状态，如果已登录，则直接放行
        * 5、如果未登录则返回未登录结果
        * */

        //1--------------------------获取本次请求的URI
        String requestURI = request.getRequestURI();
        //定义不需要处理的请求
        String[] urls = {
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**",
                "/user/sendMsg",    //发送验证码
                "/user/login",      //客户端登录
                "/doc.html",
                "/webjars/**",
                "/swagger-resources",
                "/v2/api-docs"
        };

        //2----------------------------判断本次请求是否需要处理
        boolean check = check(urls, requestURI);

        //3---------------------------如果不需要处理，则直接放行
        if (check) {
            log.info("本次请求{}不需要拦截", requestURI);
            //放行
            filterChain.doFilter(request,response);
            return;
        }

        //4---------------------------判断登录状态，如果已登录，则直接放行
        if (request.getSession().getAttribute("employee")!=null) {
            Long empId = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);
            log.info("用户已登录,id为{}", empId);
            //放行
            filterChain.doFilter(request,response);
            return;
        }
        //4-2---------------------------判断登录状态，如果已登录，则直接放行
        if (request.getSession().getAttribute("user")!=null) {
            Long userId = (Long) request.getSession().getAttribute("user");
            BaseContext.setCurrentId(userId);
            log.info("用户已登录,id为{}", userId);
            //放行
            filterChain.doFilter(request,response);
            return;
        }

        //5------------------------------如果未登录则返回未登录结果。通过输出流方式向客户端页面响应数据
        log.info("用户未登录");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
    }

    /**
     * 路径匹配，检查本次请求是否放行
     * @param requestURI 前端请求的URI
     * @param urls 定义好的放行数组
     * @return 放行？
     */
    private boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = ANT_PATH_MATCHER.match(url, requestURI);
            if (match) return true;
        }
        return false;
    }
}
