package testng;

import com.zebrunner.agent.core.annotation.Maintainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Maintainer("owner2")
public class TestClassThree {

    private static final Logger LOGGER = LogManager.getLogger(TestClassThree.class);

    @Test(testName = "Test Zero One", groups = "com.zebrunner.testng.TestClassThree.test00001")
    @Maintainer("owner3")
    public void test01() {
        int number = 4;
        assertEquals(number % 2, 0);
    }

    @Test
    public void test02() {
        LOGGER.info("asdasdasdasdas");
        int number = 4;
        assertEquals(number % 2, 1);
    }

    @Test(invocationCount = 2)
    public void test03() {
        int number = 4;
        assertEquals(number % 2, 1);
    }

    @Test(invocationCount = 2, dependsOnMethods = "test05")
    public void test04() {
        int number = 4;
        assertEquals(number % 2, 1);
    }

    @Parameters({"var"})
    @Test
    public void test05(String var) {
        int number = 4;
        assertEquals(number % 2, 1);
    }

    @DataProvider(name = "dp")
    public Object[][] dp() {
        return new String[][]{{"val11", "val22"}, {"val33", "val44"}};
    }

    @Parameters({"par1", "par2"})
    @Test(dataProvider = "dp")
    public void test05(String name, String name1) {
        int number = 4;
        System.out.println(name + " " + name1);
        assertEquals(number % 2, 0);
    }

}
