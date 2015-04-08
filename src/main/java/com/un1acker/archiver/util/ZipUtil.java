package com.un1acker.archiver.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipException;

/**
 * un1acker
 * 04.04.2015
 */
public class ZipUtil {
    private static final byte[] BUFFER = new byte[4096 * 1024];
    public static final String WORK_DIRECTORY = System.getProperty("user.dir");

    public static boolean isStringNotNullAndNotEmpty(String str) {
        return !(str == null || str.trim().isEmpty());
    }

    public static boolean isFileExists(String path) throws ZipException {
        if (!isStringNotNullAndNotEmpty(path)) {
            throw new ZipException("path is null or empty");
        }
        File file = new File(WORK_DIRECTORY, path);
        return isFileExists(file);
    }

    public static boolean isFileExists(File file) throws ZipException {
        if (file == null) {
            throw new ZipException("Input file is null");
        }
        return file.exists();
    }

    public static boolean isFilesExists(List<String> filesPathList) throws ZipException {
        if(filesPathList == null) return false;
        for (String filePath : filesPathList) {
            if (!isFileExists(filePath)) {
                return false;
            }
        }
        return true;
    }

    public static void copy(InputStream in, OutputStream out) throws IOException {
        int len;
        while ((len = in.read(BUFFER)) != -1) {
            out.write(BUFFER, 0, len);
        }
    }
}