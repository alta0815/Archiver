package com.un1acker.archiver;

import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.StringArrayOptionHandler;

import java.util.ArrayList;

/**
 * un1acker
 * 02.04.2015
 */
public class Settings {
    @Option(name = "-zip", usage = "set name for zipFile", forbids = {"-unzip", "-path"})
    private static String zipFileName;

    @Option(name = "-add", usage = "add comment to archiver(exist or non exist)", depends = {"-zip", "-files"})
    private static String addComment;

    @Option(name = "-files", usage = "add files to zip", handler = StringArrayOptionHandler.class, depends = "-zip")
    private static ArrayList<String> filesToZip;

    @Option(name = "-unzip", usage = "set name for unzipFile", forbids = {"-zip", "-add", "-files"})
    private static String unzipFileName;

    @Option(name = "-path", usage = "set path folder to unzip(default is folder with name \"zipFileName\" in WORK_DIRECTORY)", depends = "-unzip")
    private static String pathToUnzip;

    @Option(name = "-read", usage = "read comment")
    private static boolean readComment;

    public Settings() {

    }

    public static String getZipFileName() {
        return zipFileName;
    }

    public static ArrayList<String> getFilesToZip() {
        return filesToZip;
    }

    public static String getPathToUnzip() {
        return pathToUnzip;
    }

    public static boolean isReadComment() {
        return readComment;
    }

    public static String getAddComment() {
        return addComment;
    }

    public static String getUnzipFileName() {
        return unzipFileName;
    }
}
