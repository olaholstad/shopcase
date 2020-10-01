package util;

public class ConfigurationImpl implements Configuration {

    private static Configuration instance;

    private ConfigurationImpl() {
        super();
    }

    public static Configuration getInstance() {
        if (instance == null) {
            instance = new ConfigurationImpl();
        }

        return instance;
    }

    @Override
    public int getPort() {
       return SystemUtil.getEnv("SERVICE_PORT", 9000);
    }
}