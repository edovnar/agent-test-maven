package testng;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Properties;
import java.util.TreeSet;

public class TestConfigurationCollector {

    private static final Logger log = LoggerFactory.getLogger(TestConfigurationCollector.class);

    @Test(testName = "Environment Properties")
    public void envProperties() {
        Map<String, String> envProperties = System.getenv();
        TreeSet<Object> keys = new TreeSet<>(envProperties.keySet());

        log.info("All available Environment Properties");
        keys.forEach(key -> log.info("'{}' : '{}'", key, envProperties.get(key)));
    }

    @Test(testName = "System Properties")
    public void systemProperties() {
        Properties systemProperties = System.getProperties();
        TreeSet<Object> keys = new TreeSet<>(systemProperties.keySet());

        log.info("All available System Properties");
        keys.forEach(key -> log.info("'{}' : '{}'", key, systemProperties.get(key)));
    }

}
