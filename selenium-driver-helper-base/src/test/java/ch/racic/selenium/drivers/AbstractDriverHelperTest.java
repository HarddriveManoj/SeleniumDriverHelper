package ch.racic.selenium.drivers;

import ch.racic.selenium.drivers.exceptions.ExecutableNotFoundException;
import junitx.framework.FileAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * AbstractDriverHelper Tester.
 *
 * @author rac
 * @version 1.0
 * @since <pre>Apr 18, 2016</pre>
 */
public class AbstractDriverHelperTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    class DummyDriverHelper extends AbstractDriverHelper {
        public File getFile() throws ExecutableNotFoundException, IOException {
            return executable(dummyFileName);
        }

        public File getMissingFile() throws ExecutableNotFoundException, IOException {
            return executable("blabla.bla");
        }
    }

    DummyDriverHelper dummy;
    static String dummyFileName = "dummy.file";

    @Before
    public void setUp() throws Exception {
        dummy = new DummyDriverHelper();
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Method: executable(String fileName)
     */
    @Test
    public void testExecutable() throws Exception {
        // Retrieve File pointer to test file
        File dummyFile = dummy.getFile();
        URL pathUrl = Thread.currentThread().getContextClassLoader().getResource(AbstractDriverHelper.resourcesFolder + "/" + dummyFileName);
        File originalFile = new File(pathUrl.getFile());
        // Compare with file from resources
        FileAssert.assertEquals("Temp file is same", originalFile, dummyFile);
    }

    /**
     * Method: executable(String fileName)
     * Trying to load a resource that is not existing.
     */
    @Test
    public void testExecutableMissing() throws Exception {
        thrown.expect(ExecutableNotFoundException.class);
        // Retrieve File pointer to missing file, this should throw the correct exception
        File dummyFile = dummy.getMissingFile();
    }


} 
