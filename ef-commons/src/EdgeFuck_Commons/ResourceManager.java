package EdgeFuck_Commons;

import java.io.*;
import java.net.URL;
import java.nio.file.AccessDeniedException;
import java.util.Objects;

public class ResourceManager {

    public static boolean resourceExists(String resName) {
        return ResourceManager.class.getResource(resName) != null;
    }

    public static String getPath(String resName) {
        return Objects.requireNonNull(ResourceManager.class.getResource(resName)).getPath();
    }

    public static InputStream getResourceInputStream(String resName) {
        return ResourceManager.class.getResourceAsStream(resName);
    }

    public static String readString(String resourcePath) throws IOException {
        InputStream is = getResourceInputStream(resourcePath);
        StringBuilder str = new StringBuilder();
        int data;

        while ((data = is.read()) != -1)
            str.append((char) data);

        is.close();
        return str.toString();
    }

    public static char[] readChars(String resourcePath) throws IOException {
        InputStream is = getResourceInputStream(resourcePath);
        StringBuilder str = new StringBuilder();
        int data;

        while ((data = is.read()) != -1)
            str.append((char) data);

        is.close();
        return str.toString().toCharArray();
    }

    public static byte[] readBytes(String resourcePath) throws IOException {
        InputStream is = getResourceInputStream(resourcePath);
        StringBuilder str = new StringBuilder();
        int data;

        while ((data = is.read()) != -1)
            str.append((char) data);

        is.close();
        return str.toString().getBytes();
    }

    public static void copy(String resource, File outputFile) throws IOException {
        if (ResourceManager.class.getResource(resource) == null)
            throw new FileNotFoundException("Resource file \"" + resource + "\" not found");

        InputStream is = ResourceManager.class.getResourceAsStream(resource);

        if (is == null)
            throw new IOException("Could not open stream for resource file \"" + resource + "\"");

        if (outputFile.exists() && !outputFile.canWrite())
            throw new AccessDeniedException("Current user does not have access to \"" + outputFile.getAbsolutePath() + "\"");

        if (!outputFile.canWrite())
            throw new AccessDeniedException("Current user does not have access to \"" + outputFile.getAbsolutePath() + "\"");

        FileOutputStream fos = new FileOutputStream(outputFile);

        int data;
        byte[] buf = new byte[4096];
        while ((data = is.read(buf, 0, 4096)) != -1)
            fos.write(buf, 0, data);

        fos.close();
        is.close();
    }

    public static URL getResource(String resource) {
        if (ResourceManager.class.getResource(resource) == null)
            throw new RuntimeException("Resource file \"" + resource + "\" not found in the Java Package");

        return ResourceManager.class.getResource(resource);
    }

    public static class NoExceptions {

        public static String readString(String resourcePath) {
            try {
                return ResourceManager.readString(resourcePath);
            } catch (IOException e) {
                return "";
            }
        }

        public static byte[] readBytes(String resourcePath) {
            try {
                return ResourceManager.readBytes(resourcePath);
            } catch (IOException e) {
                return new byte[0];
            }
        }

        public static char[] readChars(String resourcePath) {
            try {
                return ResourceManager.readChars(resourcePath);
            } catch (IOException e) {
                return new char[0];
            }
        }

        public static void copy(String resourceFile, File outputFile) {
            try {
                ResourceManager.copy(resourceFile, outputFile);
            } catch (IOException ignore) {
            }
        }

    }

}
