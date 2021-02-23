package testng;

import com.zebrunner.agent.core.registrar.CurrentTestRun;
import com.zebrunner.agent.core.registrar.TestSessionRegistrar;
import com.zebrunner.agent.core.registrar.descriptor.SessionCloseDescriptor;
import com.zebrunner.agent.core.registrar.descriptor.SessionStartDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class TestConfigurationCollector {

    private static final Logger log = LoggerFactory.getLogger(TestConfigurationCollector.class);

    @BeforeTest
    public void setBuild() {
        Random random = new Random();
        String build = String.format("app-%d.%d.%d", random.nextInt(10), random.nextInt(10), random.nextInt(10));
        CurrentTestRun.setBuild(build);
    }

    @Test(testName = "Environment Properties")
    public void envProperties() throws InterruptedException {
        Map<String, String> envProperties = System.getenv();
        TreeSet<Object> keys = new TreeSet<>(envProperties.keySet());

        SessionStartDescriptor startDescriptor = getStartDescriptor("Chrome", "100", "iOS", "18.2", "Space Rocket");
        TestSessionRegistrar.getInstance().registerStart(startDescriptor);

        log.info("Sleeping for 5 seconds...");
        TimeUnit.SECONDS.sleep(5);

        log.info("All available Environment Properties");
        keys.forEach(key -> log.info("'{}' : '{}'", key, envProperties.get(key)));

        SessionCloseDescriptor closeDescriptor = new SessionCloseDescriptor(startDescriptor.getSessionId());
        TestSessionRegistrar.getInstance().registerClose(closeDescriptor);
    }

    @Test(testName = "System Properties")
    public void systemProperties() throws InterruptedException {
        Properties systemProperties = System.getProperties();
        TreeSet<Object> keys = new TreeSet<>(systemProperties.keySet());

        log.info("Sleeping for 5 seconds...");
        TimeUnit.SECONDS.sleep(5);

        log.info("All available System Properties");
        keys.forEach(key -> log.info("'{}' : '{}'", key, systemProperties.get(key)));
    }

    private SessionStartDescriptor getStartDescriptor(String browser, String browserVersion,
                                                      String platform, String platformVersion,
                                                      String device) {
        HashMap<String, Object> capabilities = new HashMap<>();
        capabilities.put("browserName", browser);
        capabilities.put("browserVersion", browserVersion);
        capabilities.put("platformName", platform);
        capabilities.put("platformVersion", platformVersion);
        capabilities.put("deviceModel", device);

        HashMap<String, Object> desiredCapabilities = new HashMap<>();

        return new SessionStartDescriptor(
                UUID.randomUUID().toString(),
                capabilities,
                desiredCapabilities
        );
    }

}
