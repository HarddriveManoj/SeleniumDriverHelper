package ch.racic.selenium.drivers;

import ch.racic.selenium.drivers.exceptions.ExecutableNotFoundException;
import org.apache.commons.exec.OS;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class PhantomJSDriverHelper extends AbstractDriverHelper {
    private static File driverServer32, driverServer64, driverServerV2;
    private static final String exeNameLinux32 = "linux.32.v1.9.8.phantomjs";
    private static final String exeNameLinux64 = "linux.64.v1.9.8.phantomjs";
    private static final String exeNameOSX = "osx.v1.9.8.phantomjs";
    private static final String exeNameWindows = "phantomjs.v1.9.8.exe";
    private static final String exeNameOSX2 = "osx.v2.0.phantomjs";
    private static final String exeNameWindows2 = "phantomjs.v2.0.exe";

    /**
     * Get the 32-Bit executable for the PhantomJS driver server.
     *
     * @return executable file
     * @throws ExecutableNotFoundException
     * @throws IOException
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
     * @throws ExecutableNotFoundException
     * @throws IOException
     */
    public static File executable64() throws ExecutableNotFoundException, IOException {
        if (!OS.isFamilyUnix()) return executable();
        if (driverServer64 == null) {
            // lazy loading on first access, then reuse
            driverServer64 = executable(exeNameLinux64);
        }
        return driverServer64;
    }

    /**
     * Get the 32-Bit executable for the PhantomJS driver server v.2.0.
     *
     * @return executable file
     * @throws ExecutableNotFoundException
     * @throws IOException
     */
    public static File executableV2() throws ExecutableNotFoundException, IOException {
        if (driverServerV2 == null) {
            // lazy loading on first access, then reuse
            if (OS.isFamilyWindows()) driverServerV2 = executable(exeNameWindows2);
            else if (OS.isFamilyMac()) driverServerV2 = executable(exeNameOSX2);
            else
                throw new ExecutableNotFoundException("There is no PhantomJS version 2.0 driver known for operating system "
                        + System.getProperty("os.name").toLowerCase(Locale.US));
        }
        return driverServerV2;
    }

}
