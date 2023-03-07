package edgefuck.commons.browser.downloader;

import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

// Required Steps to install Chromium:
/*
1. Read the "https://storage.googleapis.com/chromium-browser-snapshots/Win_x64/LAST_CHANGE" file
2. Via the ID specified, download the mini_installer.exe, followed by "https://storage.googleapis.com/chromium-browser-snapshots/Win_x64/[ID]/mini_installer.exe"
 */
public class Chromium {

    private static final URL urlLastChange;

    static {
        try {
            urlLastChange = new URL("https://storage.googleapis.com/chromium-browser-snapshots/Win_x64/LAST_CHANGE");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private static long getLastChangeId() throws IOException {
        System.out.println("Obtaining the Download ID from the \"LAST_CHANGE\" file");
        URL url = new URL("https://storage.googleapis.com/chromium-browser-snapshots/Win_x64/LAST_CHANGE");
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        InputStream is = connection.getInputStream();
        StringBuilder str = new StringBuilder();
        int data;

        while ((data = is.read()) != -1)
            str.append((char) data);

        is.close();
        String id = str.toString();

        if (id.contains("\n") || id.contains("\r") || id.contains("\t") || id.contains(" ")) {
            if (id.contains("\n"))
                id = id.replaceAll("\n", "");

            if (id.contains("\r"))
                id = id.replaceAll("\r", "");

            if (id.contains("\t"))
                id = id.replaceAll("\t", "");

            if (id.contains(" "))
                id = id.replaceAll(" ", "");
        }

        if (id.isBlank())
            throw new IOException("No ID was found for the given \"mini_installer.exe\". Please download the installer manually from \"https://commondatastorage.googleapis.com/chromium-browser-snapshots/index.html?prefix=Win_x64/\".");

        return Long.parseLong(id);
    }

    public static void downloadInstaller(File outputFile) throws IOException {
        URL url = new URL("https://storage.googleapis.com/chromium-browser-snapshots/Win_x64/" + getLastChangeId() + "/mini_installer.exe");
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        System.out.println("Downloading ChromiumInstaller.exe");

        InputStream is = connection.getInputStream();
        FileOutputStream fos = new FileOutputStream(outputFile);

        byte[] buff = new byte[2048];
        int data;
        while ((data = is.read(buff, 0, 2048)) != -1)
            fos.write(buff, 0, data);

        fos.close();
        is.close();
    }

}
