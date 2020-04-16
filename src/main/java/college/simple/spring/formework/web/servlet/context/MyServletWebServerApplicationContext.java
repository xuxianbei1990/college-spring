package college.simple.spring.formework.web.servlet.context;

import college.simple.spring.formework.context.MyApplicationContext;
import college.simple.spring.formework.web.embedded.tomcat.MyTomcatServletWebServerFactory;
import college.simple.spring.formework.web.server.MyWebServer;
import org.springframework.context.ApplicationContextException;

/**
 * @author: xuxianbei
 * Date: 2020/4/14
 * Time: 20:42
 * Version:V1.0
 */
public class MyServletWebServerApplicationContext extends MyApplicationContext {

    private volatile MyWebServer webServer;

    public MyServletWebServerApplicationContext(String... configLoactions){
        super(configLoactions);
    }

    @Override
    protected void onRefresh() {
        super.onRefresh();
        try {
            createWebServer();
        } catch (Throwable ex) {
            throw new ApplicationContextException("Unable to start web server", ex);
        }
    }

    private void createWebServer() {
        MyWebServer webServer = this.webServer;
        if (webServer == null) {
            MyTomcatServletWebServerFactory factory = new MyTomcatServletWebServerFactory();
            this.webServer = factory.getWebServer();
        }

    }
}
