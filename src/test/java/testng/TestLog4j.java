package testng;

import com.zebrunner.agent.core.annotation.JiraReference;
import com.zebrunner.agent.core.annotation.Maintainer;
import com.zebrunner.agent.core.annotation.Priority;
import com.zebrunner.agent.core.registrar.Artifact;
import com.zebrunner.agent.core.registrar.Screenshot;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

@Test(enabled = false)
@Maintainer("admin")
public class TestLog4j {

    private static final Logger LOGGER = Logger.getLogger(TestLog4j.class);

    private static final File[] screenshots = new File[]{
            new File(TestLog4j.class.getClassLoader().getResource("test.png").getFile()),
            new File(TestLog4j.class.getClassLoader().getResource("test2.png").getFile())
    };

    private static final File artifact = new File(
            TestLog4j.class.getClassLoader().getResource("logback.xml").getFile()
    );

    @Test(enabled = false)
    @Maintainer("skywalker")
    public void test() throws IOException {
        long startTime = System.currentTimeMillis();
        long duration = 10000;

        while ((System.currentTimeMillis() - startTime) < duration) {

            long sleepTimeout = randomLong(50, 5000);
            LOGGER.info("Will be sleep for " + sleepTimeout + " millis");
            sleep(sleepTimeout);

            int screenshotIndex = (int) randomLong(0, 1);
            LOGGER.info("Will be uploaded screenshot " + screenshotIndex);
            Screenshot.upload(Files.readAllBytes(screenshots[screenshotIndex].toPath()), Instant.now().toEpochMilli());
            Artifact.attachToTest("logback.xml", artifact);
        }
    }

    @Test(enabled = false)
    public void test2(String test) {
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(1, 25); i++) {
            LOGGER.info("Testing logs delivery at " + System.currentTimeMillis());
        }
    }

    @Priority(Priority.P1)
    @JiraReference("DEMO-0012")
    @Test(enabled = false)
//    @Test(invocationCount = 10, threadPoolSize = 2)
    public void testWiithMultipleInvocations() {
        LOGGER.info(Thread.currentThread().getName());
        LOGGER.warn("I am going to execute this one 10 times!!!");
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(1, 25); i++) {
            LOGGER.info("Testing logs delivery at " + System.currentTimeMillis());
        }
    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public long randomLong(long leftBound, long rightBound) {
        return leftBound + (long) (Math.random() * (rightBound - leftBound));
    }

}
