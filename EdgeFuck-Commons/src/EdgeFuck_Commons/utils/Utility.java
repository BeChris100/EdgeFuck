package EdgeFuck_Commons.utils;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utility {

    @Deprecated
    public static File getRuntimeImage() {
        try {
            return new File(Utility.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        } catch (URISyntaxException e) {
            return null;
        }
    }

    public static File getRuntimeDir() {
        try {
            File image = new File(Utility.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            return new File(image.getAbsolutePath().substring(0, Utility.getLastPathSeparator(image.getAbsolutePath(), false)));
        } catch (URISyntaxException e) {
            return null;
        }
    }

    public static String getLineSeparator(String contents) {
        char[] chars = contents.toCharArray();

        long r = 0;
        long n = 0;

        for (char c : chars) {
            if (c == '\r')
                r++;

            if (c == '\n')
                n++;
        }

        if (r == n)
            return "\r\n";
        else if (r >= 1 && n == 0)
            return "\r";
        else if (n >= 1 && r == 0)
            return "\n";
        else
            return System.lineSeparator();
    }

    public static int getLastPathSeparator(String path, boolean toEnd) {
        if (path == null)
            return 0;

        if (path.isEmpty())
            return 0;

        if (!(path.contains("\\") || path.contains("/")))
            return 0;

        int lastSep;
        if (path.contains("\\") && path.contains("/")) {
            path = path.replaceAll("\\\\", "/");
            lastSep = path.lastIndexOf('/');
        } else if (path.contains("\\"))
            lastSep = path.lastIndexOf('\\');
        else
            lastSep = path.lastIndexOf('/');

        lastSep++;

        if (toEnd) {
            String sub = path.substring(lastSep);
            if (!sub.isEmpty())
                lastSep = path.length();
        }

        return lastSep;
    }

    public static String fromList(List<String> list, String spliterator) {
        if (list == null)
            return "";

        if (list.size() == 0)
            return "";

        StringBuilder str = new StringBuilder();

        for (String item : list)
            str.append(item).append(spliterator);

        return str.substring(0, str.toString().length() - spliterator.length());
    }

    public static long getRandomLong() {
        return new Random().nextLong();
    }

    public static int getRandomInteger(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    public static List<String> removeDuplicates(List<String> list) {
        List<String> result = new ArrayList<>();

        for (String item : list) {
            if (result.contains(item))
                continue;

            result.add(item);
        }

        return result;
    }

    public static String fromArray(String[] array, String split) {
        if (array == null)
            return "";

        if (array.length == 0)
            return "";

        StringBuilder str = new StringBuilder();

        for (String item : array)
            str.append(item).append(split);

        return str.substring(0, str.toString().length() - split.length());
    }
}
