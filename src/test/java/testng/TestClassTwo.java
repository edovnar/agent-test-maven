package testng;

import com.zebrunner.agent.core.annotation.Maintainer;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

@Test(enabled = false)
@Maintainer("owner1")
public class TestClassTwo {

    @DataProvider(name = "numbers1")
    public static Object[][] numbers1() {
        return new Object[][]{{8, false}, {2, true}, {4, true}};
    }

    @DataProvider(name = "numbers2")
    public static Object[][] numbers2() {
        return new Object[][]{{2, false}, {2, true}, {4, true}, {8, true}};
    }

    @Test(enabled = false)
//    @Test(dataProvider = "numbers1", groups = {"testtest"})
    public void testWithDataProvider01(Integer number, boolean expected) {
        assertEquals(expected, number % 2 == 0);
    }

    @Test(enabled = false)
//    @Test(dataProvider = "numbers2")
    public void testWithDataProvider02(Integer number, boolean expected) {
        assertEquals(expected, number % 2 == 0);
    }

    @Test(enabled = false)
//    @Test(dataProvider = "numbers2", successPercentage = 50, dependsOnGroups = {"testtest"})
    public void testWithDataProvider03(Integer number, boolean expected) {
        assertEquals(expected, number % 2 == 0);
    }

}
