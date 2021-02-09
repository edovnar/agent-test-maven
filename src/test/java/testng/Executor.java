package testng;

import com.zebrunner.agent.testng.listener.TestRunListener;
import org.testng.ITestNGListener;
import org.testng.TestNG;

public class Executor {

    public static void main(String[] args) {
        ITestNGListener listener = new TestRunListener();

        TestNG testng = new TestNG();
        testng.setTestClasses(new Class[] {
                ConfigurationCollector.class
        });

//        testng.setXmlSuites(XmlSuite);

        testng.addListener(listener);

        testng.run();
    }

}
