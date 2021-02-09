package testng;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test(enabled = false)
public class TestLogback {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TestLogback.class);

    @Test
    public void test() {
        LOGGER.info("asdasdasdasdas");
        int number = 4;
        assertEquals(number % 2, 1);
    }

}
