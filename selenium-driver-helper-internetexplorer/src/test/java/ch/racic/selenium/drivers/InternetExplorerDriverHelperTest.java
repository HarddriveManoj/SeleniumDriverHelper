package ch.racic.selenium.drivers;

import junitx.framework.FileAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;

/**
 * InternetExplorerDriverHelper Tester.
 *
 * @author rac
 * @version 1.0
 * @since <pre>Apr 18, 2016</pre>
 */
public class InternetExplorerDriverHelperTest {

    @Before
    public void setUp() throws Exception {
        resetDriverHelperInit();
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Method: executable()
     */
    @Test
    public void testExecutable() throws Exception {
        URL pathUrl = Thread.currentThread().getContextClassLoader().getResource(AbstractDriverHelper.resourcesFolder + "/" + InternetExplorerDriverHelper.exeName32);
        File originalFile = new File(pathUrl.getFile());
        // Compare with file from resources
        FileAssert.assertEquals("Temp file is same", originalFile, InternetExplorerDriverHelper.executable());
    }

    /**
     * Method: executable64()
     */
    @Test
    public void testExecutable64() throws Exception {
        URL pathUrl = Thread.currentThread().getContextClassLoader().getResource(AbstractDriverHelper.resourcesFolder + "/" + InternetExplorerDriverHelper.exeName64);
        File originalFile = new File(pathUrl.getFile());
        // Compare with file from resources
        FileAssert.assertEquals("Temp file is same", originalFile, InternetExplorerDriverHelper.executable64());
    }

    static synchronized void resetDriverHelperInit() throws Exception {
        Field field32 = InternetExplorerDriverHelper.class.getDeclaredField("driverServer32");
        Field field64 = InternetExplorerDriverHelper.class.getDeclaredField("driverServer64");
        field32.setAccessible(true);
        field64.setAccessible(true);
        field32.set(null, null);
        field64.set(null, null);
    }


} 
