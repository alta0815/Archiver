package com.un1acker.archiver;

import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.StringArrayOptionHandler;

import java.util.ArrayList;

/**
 * un1acker
 * 02.04.2015
 */
public class Settings {
    @Option(name = "-o", usage = "name for zipFile", forbids = {"-i", "-p"})
    private String zipFile;

    @Option(name = "-f", handler = StringArrayOptionHandler.class, depends = "-o")
    private ArrayList<String> files;

    @Option(name = "-i", usage = "name for unzipFile", forbids = {"-o", "-f"})
    private String unzipFile;

    @Option(name = "-p", usage = "set path folder to unzip", depends = "-i")
    private String path;

    @Option(name = "-a", usage = "add comment to archiver", depends = {"-o", "-i"})
    private String addComment;

    @Option(name = "-r", usage = "read comment", forbids = {"-o", "-a"})
    private boolean readComment;
}

