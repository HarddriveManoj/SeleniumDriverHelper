package ch.racic.selenium.drivers;

import ch.racic.selenium.drivers.exceptions.ExecutableNotFoundException;
import org.apache.commons.exec.OS;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

/**
 * The type Phantom js driver helper.
 */
public class PhantomJSDriverHelper extends AbstractDriverHelper {
    private static File driverServer32, driverServer64;
    protected static final String exeNameLinux32 = "linux.32.v2.1.1.phantomjs";
    protected static final String exeNameLinux64 = "linux.64.v2.1.1.phantomjs";
    protected static final String exeNameOSX = "osx.v2.1.1.phantomjs";
    protected static final String exeNameWindows = "windows.v2.1.1.phantomjs.exe";

    /**
     * Get the 32-Bit executable for the PhantomJS driver server.
     *
     * @return executable file
     * @throws ExecutableNotFoundException the executable not found exception
     * @throws IOException                 the io exception
     */
    public static File executable() throws ExecutableNotFoundException, IOException {
        if (driverServer32 == null) {
            // lazy loading on first access, then reuse
            if (OS.isFamilyWindows()) driverServer32 = executable(exeNameWindows);
            else if (OS.isFamilyMac()) driverServer32 = executable(exeNameOSX);
            else if (OS.isFamilyUnix()) driverServer32 = executable(exeNameLinux32);
            else
                throw new ExecutableNotFoundException("There is no PhantomJS driver known for operating system "
                        + System.getProperty("os.name").toLowerCase(Locale.US));
        }
        return driverServer32;
    }

    /**
     * Get the 64-Bit executable for the PhantomJS driver server. The 64-bit version only exists for Linux, will return
     * the 32-bit version for all other OS identifiers.
     *
     * @return executable file
     * @throws ExecutableNotFoundException the executable not found exception
     * @throws IOException                 the io exception
     */
    public static File executable64() throws ExecutableNotFoundException, IOException {
        if (!OS.isFamilyUnix()) {
            return executable();
        } else {
            if (driverServer64 == null) {
                // lazy loading on first access, then reuse
                driverServer64 = executable(exeNameLinux64);
            }
            return driverServer64;
        }
    }

}
