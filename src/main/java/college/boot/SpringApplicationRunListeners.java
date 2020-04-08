package college.boot;

import college.context.ConfigurableApplicationContext;
import org.springframework.boot.SpringApplicationRunListener;

import java.util.List;

/**
 * @author: xuxianbei
 * Date: 2020/4/7
 * Time: 16:49
 * Version:V1.0
 */
public class SpringApplicationRunListeners {

    private List<SpringApplicationRunListener> listeners;

    void starting() {
        for (SpringApplicationRunListener listener : this.listeners) {
            listener.starting();
        }
    }

    public void contextPrepared(ConfigurableApplicationContext context) {

    }
}
