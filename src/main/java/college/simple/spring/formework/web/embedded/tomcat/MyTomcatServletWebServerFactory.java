package college.simple.spring.formework.web.embedded.tomcat;

import college.simple.spring.formework.web.server.MyWebServer;
import lombok.Data;
import org.apache.catalina.Host;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.boot.web.servlet.ServletContextInitializer;

import java.io.File;
import java.io.IOException;

/**
 * @author: xuxianbei
 * Date: 2020/4/14
 * Time: 20:58
 * Version:V1.0
 */
@Data
public class MyTomcatServletWebServerFactory {

    private int port = 8080;

    public static final String DEFAULT_PROTOCOL = "org.apache.coyote.http11.Http11NioProtocol";

    private String protocol = DEFAULT_PROTOCOL;

    public MyWebServer getWebServer() {
        Tomcat tomcat = new Tomcat();
        File baseDir = createTempDir("tomcat");
        tomcat.setBaseDir(baseDir.getAbsolutePath());
        Connector connector = new Connector(this.protocol);
        tomcat.getService().addConnector(connector);
        customizeConnector(connector);
        tomcat.setConnector(connector);
        tomcat.getHost().setAutoDeploy(false);
        prepareContext(tomcat.getHost(), null);
//        tomcat.setPort(getPort());
//        tomcat.addServlet("/college", "demomvc",
//                "college.simple.spring.formework.web.servlet.MyDispatcherServlet");
        return getTomcatWebServer(tomcat);
    }

    private MyWebServer getTomcatWebServer(Tomcat tomcat) {
        return new MyTomcatWebServer(tomcat, getPort() >= 0);
    }

    private void prepareContext(Host host, ServletContextInitializer[] initializers) {
        MyTomcatEmbeddedContext context = new MyTomcatEmbeddedContext();
        context.setUseRelativeRedirects(false);
        addDefaultServlet(context);
        host.addChild(context);
    }

    private void addDefaultServlet(MyTomcatEmbeddedContext context) {
        Wrapper defaultServlet = context.createWrapper();
        defaultServlet.setName("default");
        defaultServlet.setServletClass("org.apache.catalina.servlets.DefaultServlet");
        defaultServlet.addInitParameter("debug", "0");
        defaultServlet.addInitParameter("listings", "false");
        defaultServlet.setLoadOnStartup(1);
        // Otherwise the default location of a Spring DispatcherServlet cannot be set
        defaultServlet.setOverridable(true);
        context.addChild(defaultServlet);
        context.addServletMappingDecoded("/", "default");
    }

    private void customizeConnector(Connector connector) {
        int port = (getPort() >= 0) ? getPort() : 0;
        connector.setPort(port);
        connector.setURIEncoding("UTF-8");
        // Don't bind to the socket prematurely if ApplicationContext is slow to start
        //tomcat启动时候，未启动完毕，如果有请求，直接返回，不等待
        connector.setProperty("bindOnInit", "false");
        //优化项，例如设置压缩，设置最大线程，设置头部大小 巴拉巴拉
    }


    /**
     * Return the absolute temp dir for given web server.
     * @param prefix server name
     * @return the temp dir for given server.
     */
    protected final File createTempDir(String prefix) {
        try {
            File tempDir = File.createTempFile(prefix + ".", "." + getPort());
            tempDir.delete();
            tempDir.mkdir();
            tempDir.deleteOnExit();
            return tempDir;
        }
        catch (IOException ex) {
            throw new WebServerException(
                    "Unable to create tempDir. java.io.tmpdir is set to "
                            + System.getProperty("java.io.tmpdir"),
                    ex);
        }
    }

}
