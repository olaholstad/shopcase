package util;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemUtil {
    private static final Logger logger = LoggerFactory.getLogger(SystemUtil.class);

    public static String getEnv(String key, String defaultValue) {
        if (key == null) {
            return null;
        } else {
            String envValue = StringUtils.trimToNull(System.getenv(key));
            String value = envValue == null ? StringUtils.trimToNull(defaultValue) : envValue;
            if (logger.isDebugEnabled()) {
                logger.debug("Environment variable {}={}", key, value);
            }

            return value;
        }
    }

    public static Integer getEnv(String key, Integer defaultValue) {
        String envValue = getEnv(key, (String) null);
        Integer value = envValue == null ? defaultValue : Integer.decode(envValue);
        if (logger.isDebugEnabled()) {
            logger.debug("Environment variable {}={}", key, value);
        }

        return value;
    }

    public static Double getEnv(String key, Double defaultValue) {
        String envValue = getEnv(key, (String) null);
        Double value = envValue == null ? defaultValue : Double.parseDouble(envValue);
        if (logger.isDebugEnabled()) {
            logger.debug("Environment variable {}={}", key, value);
        }
        return value;
    }
}