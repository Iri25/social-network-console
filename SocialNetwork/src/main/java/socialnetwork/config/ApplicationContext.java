package socialnetwork.config;

import java.util.Properties;

public class ApplicationContext {
    private static final Properties PROPERTIES = Config.getProperties();

    /**
     * 
     * @return PROPERTIES
     */
    public static Properties getPROPERTIES() {
        return PROPERTIES;
    }
}
