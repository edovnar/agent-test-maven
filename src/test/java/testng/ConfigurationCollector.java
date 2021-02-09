package testng;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Properties;

public class ConfigurationCollector {

    private static final Logger log = LoggerFactory.getLogger(ConfigurationCollector.class);

    @Test(testName = "Environment Properties")
    public void envProperties() {
        Map<String, String> envProperties = System.getenv();
        log.info("All available Environment Properties");
        envProperties.forEach(
                (key, value) -> log.info("'{}' : '{}'", key, value)
        );
    }

    @Test(testName = "System Properties")
    public void systemProperties() {
        Properties envProperties = System.getProperties();
        log.info("All available System Properties");
        envProperties.forEach(
                (key, value) -> log.info("'{}' : '{}'", key, value)
        );
    }

}
