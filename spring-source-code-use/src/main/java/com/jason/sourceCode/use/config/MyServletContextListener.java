package com.jason.sourceCode.use.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 自定义一个 ServletContextListener ，
 *
 * @author WangChenHol
 * @date 2022-1-12 15:32
 **/
public class MyServletContextListener implements ServletContextListener {
    private ServletContext context = null;

    // 该方法在ServletContext容器启动的时候被调用
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        this.context = sce.getServletContext();
        context.setAttribute("myTestData", "测试的ServletContext数据");
    }

    // 关闭的时候调用
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
