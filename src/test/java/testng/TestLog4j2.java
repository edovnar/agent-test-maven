package testng;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test(enabled = false)
public class TestLog4j2 {

    private static final Logger LOGGER = LogManager.getLogger(TestClassThree.class);

    @Test
    public void test() {
        LOGGER.info("asdasdasdasdas");
        int number = 4;
        assertEquals(number % 2, 1);
    }

}
