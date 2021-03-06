package college.boot;

/**
 * @author: xuxianbei
 * Date: 2021/4/19
 * Time: 15:39
 * Version:V1.0
 */
public enum WebApplicationType {

    /**
     * The application should not run as a web application and should not start an
     * embedded web server.
     */
    NONE,

    /**
     * The application should run as a servlet-based web application and should start an
     * embedded servlet web server.
     */
    SERVLET,

    /**
     * The application should run as a reactive web application and should start an
     * embedded reactive web server.
     */
    REACTIVE;
}
