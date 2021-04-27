package college.core.io.support;

/**
 * @author: xuxianbei
 * Date: 2021/4/23
 * Time: 16:24
 * Version:V1.0
 */
public class DescriptiveResource implements Resource {

    private final String description;

    public DescriptiveResource( String description) {
        this.description = (description != null ? description : "");
    }
}
