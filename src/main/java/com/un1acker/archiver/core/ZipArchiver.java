package com.un1acker.archiver.core;

import com.un1acker.archiver.Settings;
import com.un1acker.archiver.unzip.UnzipObject;
import com.un1acker.archiver.zip.ZipObject;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.io.IOException;

/**
 * un1acker
 * 04.04.2015
 */
public class ZipArchiver {
    private static Settings settings = new Settings();
    private static CmdLineParser parser = new CmdLineParser(settings);

    public static void init(String[] args) {
        ZipArchiver zipArchiver = new ZipArchiver();
        try {
            parser.parseArgument(args);
            zipArchiver.runArchiver();
        } catch (CmdLineException e) {
            System.out.println(e.getMessage());
            parser.printUsage(System.out);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ZipArchiver() {

    }

    public void runArchiver() throws CmdLineException, IOException {
        if (Settings.getZipFileName() != null) {
            ZipObject zip = new ZipObject(Settings.getZipFileName());
            if (Settings.getFilesToZip() != null) {
                zip.addFilesToZipWithComment(Settings.getFilesToZip(), Settings.getAddComment());
            }
            if (Settings.isReadComment()) System.out.println("Zip comment is : " + zip.getZipFileComment() + "\n");
        } else if (Settings.getUnzipFileName() != null) {
            UnzipObject unzip = new UnzipObject(Settings.getUnzipFileName());
            unzip.extractAll(Settings.getPathToUnzip());
            if (Settings.isReadComment()) System.out.println("Zip comment is : " + unzip.getZipFileComment() + "\n");
        }
    }
}
