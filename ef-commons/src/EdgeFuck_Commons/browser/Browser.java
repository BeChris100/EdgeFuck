package EdgeFuck_Commons.browser;

import java.io.File;

public class Browser {

    protected String name;
    protected File installPath;

    public Browser(String name, File installPath) {
        this.name = name;
        this.installPath = installPath;
    }

    public Browser(BrowserCommonName commonName, File installPath) {
        switch (commonName) {
            case BRAVE -> this.name = "Brave";
            case OPERA -> this.name = "Opera";
            case FIREFOX -> this.name = "Firefox";
            case CHROMIUM -> this.name = "Chromium";
            case GOOGLE_CHROME -> this.name = "Google Chrome";
            default -> this.name = commonName.toString();
        }

        this.installPath = installPath;
    }

    public static File getDefaultInstallPathFor(BrowserCommonName commonName) {
        return new File("");
    }

    public String getName() {
        return name;
    }

    public File getInstallPath() {
        return installPath;
    }

    private boolean hasRegistryEntries() throws Exception {
        return false;
    }

    private boolean hasValidInstallation() throws Exception {
        return false;
    }

    public boolean isBrowserInstalled() throws Exception {
        return false;
    }

}
