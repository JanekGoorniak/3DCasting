package org.example;

public class PathParser
{
    public static String parse(String path)
    {
        String os = System.getProperty("os.name").toLowerCase();
        String separator = System.getProperty("file.separator");

        if (os.contains("win") || os.contains("dos") || os.contains("microsoft"))
        {
            return path.replace("/", separator);    // windows
        }
        else
        {
            return path.replace("\\", separator);   // mac/linux
        }
    }
}
