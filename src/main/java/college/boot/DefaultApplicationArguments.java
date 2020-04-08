package college.boot;

/**
 * @author: xuxianbei
 * Date: 2020/4/7
 * Time: 16:59
 * Version:V1.0
 */
public class DefaultApplicationArguments implements ApplicationArguments {

    private final String[] args;

    public DefaultApplicationArguments(String[] args) {
        this.args = args;
    }
}
