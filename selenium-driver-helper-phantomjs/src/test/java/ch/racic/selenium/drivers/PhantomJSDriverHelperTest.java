package ch.racic.selenium.drivers;

import junitx.framework.FileAssert;
import org.apache.commons.exec.OS;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;

/**
 * PhantomJSDriverHelper Tester.
 *
 * @author rac
 * @version 1.0
 * @since <pre>Apr 18, 2016</pre>
 */
public class PhantomJSDriverHelperTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        resetPhantomJSDriverHelperInit();
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Method: executable()
     */
    @Test
    public void testExecutableWindows() throws Exception {
        URL pathUrl = Thread.currentThread().getContextClassLoader().getResource(AbstractDriverHelper.resourcesFolder + "/" + PhantomJSDriverHelper.exeNameWindows);
        File originalFile = new File(pathUrl.getFile());
        // Fake OS for apache commons exec
        setFinalStatic(OS.class.getDeclaredField("OS_NAME"), "windows");
        // Compare with file from resources
        FileAssert.assertEquals("Temp file is same", originalFile, PhantomJSDriverHelper.executable());
    }

    /**
     * Method: executable()
     */
    @Test
    public void testExecutableOSX() throws Exception {
        URL pathUrl = Thread.currentThread().getContextClassLoader().getResource(AbstractDriverHelper.resourcesFolder + "/" + PhantomJSDriverHelper.exeNameOSX);
        File originalFile = new File(pathUrl.getFile());
        // Fake OS for apache commons exec
        setFinalStatic(OS.class.getDeclaredField("OS_NAME"), "mac");
        // Compare with file from resources
        FileAssert.assertEquals("Temp file is same", originalFile, PhantomJSDriverHelper.executable());
    }

    /**
     * Method: executable()
     */
    @Test
    public void testExecutableLinux() throws Exception {
        URL pathUrl = Thread.currentThread().getContextClassLoader().getResource(AbstractDriverHelper.resourcesFolder + "/" + PhantomJSDriverHelper.exeNameLinux32);
        File originalFile = new File(pathUrl.getFile());
        // Fake OS for apache commons exec
        setFinalStatic(OS.class.getDeclaredField("OS_NAME"), "unix");
        // Compare with file from resources
        FileAssert.assertEquals("Temp file is same", originalFile, PhantomJSDriverHelper.executable());
    }

    /**
     * Method: executable64()
     */
    @Test
    public void testExecutable64Linux() throws Exception {
        URL pathUrl = Thread.currentThread().getContextClassLoader().getResource(AbstractDriverHelper.resourcesFolder + "/" + PhantomJSDriverHelper.exeNameLinux32);
        File originalFile = new File(pathUrl.getFile());
        // Fake OS for apache commons exec
        setFinalStatic(OS.class.getDeclaredField("OS_NAME"), "unix");
        // Compare with file from resources
        FileAssert.assertEquals("Temp file is same", originalFile, PhantomJSDriverHelper.executable());
    }

    /**
     * Method: executable64()
     */
    @Test
    public void testExecutable64Windows() throws Exception {
        URL pathUrl = Thread.currentThread().getContextClassLoader().getResource(AbstractDriverHelper.resourcesFolder + "/" + PhantomJSDriverHelper.exeNameWindows);
        File originalFile = new File(pathUrl.getFile());
        // Fake OS for apache commons exec
        setFinalStatic(OS.class.getDeclaredField("OS_NAME"), "windows");
        // Compare with file from resources
        FileAssert.assertEquals("Temp file is same", originalFile, PhantomJSDriverHelper.executable());
    }

    /**
     * Method: executable64()
     */
    @Test
    public void testExecutable64OSX() throws Exception {
        URL pathUrl = Thread.currentThread().getContextClassLoader().getResource(AbstractDriverHelper.resourcesFolder + "/" + PhantomJSDriverHelper.exeNameOSX);
        File originalFile = new File(pathUrl.getFile());
        // Fake OS for apache commons exec
        setFinalStatic(OS.class.getDeclaredField("OS_NAME"), "mac");
        // Compare with file from resources
        FileAssert.assertEquals("Temp file is same", originalFile, PhantomJSDriverHelper.executable());
    }

    /**
     * Helper method to mock/fake the OS in apache commons exec. Taken from http://stackoverflow.com/a/23200308/475949
     *
     * @param field
     * @param newValue
     * @throws Exception
     */
    static synchronized void setFinalStatic(Field field, Object newValue) throws Exception {
        field.setAccessible(true);
        // remove final modifier from field
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, newValue);
    }

    static synchronized void resetPhantomJSDriverHelperInit() throws Exception {
        Field field32 = PhantomJSDriverHelper.class.getDeclaredField("driverServer32");
        Field field64 = PhantomJSDriverHelper.class.getDeclaredField("driverServer64");
        field32.setAccessible(true);
        field64.setAccessible(true);
        field32.set(null, null);
        field64.set(null, null);
    }

} 
