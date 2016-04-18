package ch.racic.selenium.drivers;

import ch.racic.selenium.drivers.exceptions.ExecutableNotFoundException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * The type Abstract driver helper.
 */
public abstract class AbstractDriverHelper {
    /**
     * The constant resourcesFolder.
     */
    public static final String resourcesFolder = "ch.racic.selenium.drivers.resources";

    /**
     * Return the Executable file for this driver.
     *
     * @param fileName the file name
     * @return the file
     * @throws ExecutableNotFoundException the executable not found exception
     * @throws IOException                 the io exception
     */
    protected static synchronized File executable(final String fileName) throws ExecutableNotFoundException, IOException {
        // Get URL from resources
        URL pathUrl = Thread.currentThread().getContextClassLoader().getResource(resourcesFolder + "/" + fileName);
        if (pathUrl == null) {
            throw new ExecutableNotFoundException("Could not load the executable from resources: "
                    + resourcesFolder + "/" + fileName);
        }
        // Copy to temp folder setting the deleteOnExit and executable flags
        File ret = File.createTempFile("Selenium", fileName);
        ret.setExecutable(true);
        ret.deleteOnExit();
        FileUtils.copyURLToFile(pathUrl, ret);
        return ret;
    }
}
