package ch.racic.selenium.drivers.test;

import ch.racic.selenium.drivers.PhantomJSDriverHelper;
import ch.racic.selenium.drivers.exceptions.ExecutableNotFoundException;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by rac on 25.03.15.
 */
public class FileExtractTest {
    @Test
    public void testWithPhantom() throws ExecutableNotFoundException, IOException {
        File testDriverFile = PhantomJSDriverHelper.executable();
        Assert.assertTrue("File exists at all", testDriverFile.exists());
        Assert.assertTrue("File has executable bit set", testDriverFile.canExecute());
    }
}
