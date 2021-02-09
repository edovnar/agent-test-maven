package testng;

import com.zebrunner.agent.core.annotation.JiraReference;
import com.zebrunner.agent.core.annotation.Maintainer;
import com.zebrunner.agent.core.annotation.Priority;
import com.zebrunner.agent.core.annotation.QTestReference;
import com.zebrunner.agent.core.annotation.TestLabel;
import com.zebrunner.agent.core.annotation.TestRailReference;
import com.zebrunner.agent.core.annotation.XRayReference;
import com.zebrunner.agent.core.registrar.Artifact;
//import com.zebrunner.agent.core.registrar.CurrentTest;
import com.zebrunner.agent.core.registrar.CurrentTest;
import com.zebrunner.agent.core.registrar.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.io.File;
import java.io.InputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.testng.Assert.assertEquals;

@Test(enabled = false)
@Priority(Priority.P1)
@TestRailReference("test-rail-4444")
@TestLabel(name = "group", value = "regression")
@TestLabel(name = "app", value = "reporting-service")
public class TestClassOne extends AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(TestClassOne.class);

    private static final InputStream DOC = TestLog4j.class.getClassLoader().getResourceAsStream("file.har");
    private static final File[] IMAGES = new File[]{
            new File(TestLog4j.class.getClassLoader().getResource("test.png").getFile()),
            new File(TestLog4j.class.getClassLoader().getResource("test2.png").getFile()),
    };

    private int number;

    public void setNumber(int number) {
        this.number = number;
    }

    @Factory
    public Object[] factoryMethod() {
        TestClassOne testClassOne = new TestClassOne();
        testClassOne.setNumber(2);
        TestClassOne testClassOne2 = new TestClassOne();
        testClassOne2.setNumber(2);
        return new Object[]{testClassOne};
    }

    @BeforeMethod
    public void setup1() {
        log.info("setup 1");
    }

    @BeforeMethod
    public void setup2() {
        log.info("setup 2");
    }

    @BeforeMethod
    public void setup3() {
        log.info("setup 3");
    }

    @Priority(Priority.P2)
    @Maintainer("ldapuser")
    @JiraReference({"ZEB-1613", "ZEB-1638", "ZEB-1641"})
    @QTestReference("q-test-2222")
    @Test(testName = "Test Zero One")
    public void test01() throws InterruptedException {
        log.info("Executing test01");
//        Thread.sleep(60 * 1000);
        assertEquals(number % 2, 0);
    }

    @Priority(Priority.P3)
    @XRayReference("x-ray-3333")
    @TestLabel(name = "app", value = {"iam-service:v1.0", "iam-service:v1.1"})
    @Test(dependsOnMethods = "test01")
    public void test02() {
        log.info("Executing test02");
        Label.attachToTest("Chrome", "85.0");
        assertEquals(number % 2, 0);
        Artifact.attachToTest("file.har", DOC);
        Artifact.attachReferenceToTest("zebrunner.com", "https://www.zebrunner.com/");
    }

    @Test(enabled = false)
    @TestRailReference("test-rail-5555")
    public void test03() {
        if (LocalDate.now().getDayOfWeek() == DayOfWeek.MONDAY) {
            CurrentTest.revertRegistration();
        }
    }

    @XRayReference("x-ray")
    @JiraReference("ZBR-1231")
    @Test(dependsOnMethods = "test02")
    public void test04() {
        log.info("Executing test04");
        Label.attachToTestRun("Display", "1440x880", "1920x1080", "3860x2160", "8600x210", "1600x2330");
        Artifact.attachToTest("screen01.png", IMAGES[0]);
        Artifact.attachToTestRun("screen02.png", IMAGES[1]);
//        CurrentTest.revertRegistration();
        Artifact.attachReferenceToTestRun("google.by", "https://www.google.by");
        Artifact.attachReferenceToTestRun("Baby Yoda", "https://miro.medium.com/max/1200/1*mk1-6aYaf_Bes1E3Imhc0A.jpeg");
        Artifact.attachReferenceToTestRun("Набор китайского чая «Драконы», 6 сортов, N14", "https://www.teashop.by/product/chajnyj-nabor-chernyj-drakon-6/");
        Artifact.attachReferenceToTestRun("Караник: надо составить список тех, кто не трудоустроен. Это для безопасности", "https://people.onliner.by/2020/12/09/nado-sostavit-spisok-tex-kto-ne-trudoustroen");
        Artifact.attachReferenceToTestRun("https://www.onliner.by/", "https://www.onliner.by/");
        Artifact.attachReferenceToTestRun("Погода в Минске на неделю", "https://www.gismeteo.by/weather-minsk-4248/weekly/");
        Artifact.attachReferenceToTestRun("https://www.youtube.com/", "https://www.youtube.com/");
    }


    @DataProvider(name = "numbers1")
    public static Object[][] numbers1() {
        log.info("Executing numbers1 data provider");
        return new Object[][]{
                {1, true},
                {2, false},
                {3, true}
        };
    }

    @Priority(Priority.P2)
    @Maintainer("ldapuser")
    @JiraReference({"ZEB-1613", "ZEB-1638", "ZEB-1641"})
    @QTestReference("q-test-2222")
    @Test(testName = "Test Zero One")
    public void test00() {
        log.info("Executing test01");
        assertEquals(number % 2, 0);
    }

    @Maintainer("dmishin")
    @JiraReference("ZEB-1677")
    @Test(dataProvider = "numbers1", retryAnalyzer = RetryAnalyzer.class, testName = "Numbers test")
    @TestRailReference("test-rail-6666")
    @TestLabel(name = "group", value = "smoke")
    public void testTEst(Integer number, boolean expected) {
        log.info("Executing testTEst");
        AssertJUnit.assertEquals(expected, number % 2 == 0);
    }

}
