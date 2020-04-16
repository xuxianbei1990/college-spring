package college.simple.spring.formework.web.embedded.tomcat;

import college.simple.spring.formework.web.server.MyWebServer;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.util.Assert;

/**
 * @author: xuxianbei
 * Date: 2020/4/15
 * Time: 16:00
 * Version:V1.0
 */
public class MyTomcatWebServer implements MyWebServer {
    private final Tomcat tomcat;

    private final boolean autoStart;

    private final Object monitor = new Object();

    public MyTomcatWebServer(Tomcat tomcat, boolean autoStart) {
        Assert.notNull(tomcat, "Tomcat Server must not be null");
        this.tomcat = tomcat;
        this.autoStart = autoStart;
        initialize();
    }

    private void initialize() throws WebServerException {
        synchronized (this.monitor) {
            // Start the server to trigger initialization listeners
            try {

                this.tomcat.start();
            } catch (LifecycleException e) {
                e.printStackTrace();
            }
        }
    }
}
