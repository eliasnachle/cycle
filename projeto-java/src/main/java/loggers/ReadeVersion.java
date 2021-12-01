package loggers;

import java.io.InputStream;
import java.util.Properties;

public class ReadeVersion {
    public synchronized String getVersion() {
    String version = null;

    // try to load from maven properties first
    try {
        Properties p = new Properties();
        InputStream is = getClass().getResourceAsStream("/oshi.architecture.properties");
        if (is != null) {
            p.load(is);
            version = p.getProperty("version", "");
        }
    } catch (Exception e) {
        // ignore
    }

    // fallback to using Java API
    if (version == null) {
        Package aPackage = getClass().getPackage();
        if (aPackage != null) {
            version = aPackage.getImplementationVersion();
            if (version == null) {
                version = aPackage.getSpecificationVersion();
            }
        }
    }

    if (version == null) {
        // we could not compute the version so use a blank
        version = "";
    }

    return version;
} 
}
