package ch.racic.selenium.drivers;

import ch.racic.selenium.drivers.exceptions.ExecutableNotFoundException;

import java.io.File;
import java.io.IOException;

public class InternetExplorerDriverHelper extends AbstractDriverHelper {
    private static File driverServer32, driverServer64;
    protected static final String exeName32 = "IEDriverServer32.exe";
    protected static final String exeName64 = "IEDriverServer64.exe";

    /**
     * Get the 32-Bit executable for the Internet Explorer driver server. Currently it is advised to use the 32-bit
     * version if there are speed issues especially in typing text.
     *
     * @return executable file
     * @throws ExecutableNotFoundException
     * @throws IOException
     */
    public static File executable() throws ExecutableNotFoundException, IOException {
        if (driverServer32 == null) {
            // lazy loading on first access, then reuse
            driverServer32 = executable(exeName32);
        }
        return driverServer32;
    }

    /**
     * Get the 64-Bit executable for the Internet Explorer driver server. Currently it is advised to use the 32-bit
     * version if there are speed issues especially in typing text.
     *
     * @return executable file
     * @throws ExecutableNotFoundException
     * @throws IOException
     */
    public static File executable64() throws ExecutableNotFoundException, IOException {
        if (driverServer64 == null) {
            // lazy loading on first access, then reuse
            driverServer64 = executable(exeName64);
        }
        return driverServer64;
    }

}
