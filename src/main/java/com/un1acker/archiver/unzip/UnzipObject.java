package com.un1acker.archiver.unzip;

import com.un1acker.archiver.util.ZipUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * un1acker
 * 04.04.2015
 */
public class UnzipObject {
    private File unzipFile;

    public UnzipObject(String unzipFileName) throws ZipException {
        if (!ZipUtil.isStringNotNullAndNotEmpty(unzipFileName) || !unzipFileName.endsWith(".zip")) {
            throw new ZipException("fileName does not satisfy requirements");
        }
        this.unzipFile = new File(ZipUtil.WORK_DIRECTORY, unzipFileName);
    }

    public String getZipFileComment() throws IOException {
        return new ZipFile(unzipFile).getComment();
    }

    public boolean extractAll(String folderName) throws IOException {
        File outputFolder = newOutputFolder(folderName);
        if (!outputFolder.exists()) {
            outputFolder.mkdir();
        }
        ZipInputStream zis = new ZipInputStream(new FileInputStream(unzipFile));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            String fileName = zipEntry.getName();
            File newFile = new File(outputFolder.getAbsolutePath(), fileName);
            System.out.print("file unzip : " + newFile.getAbsoluteFile() + "\n");
            new File(newFile.getParent()).mkdirs();
            FileOutputStream fos = new FileOutputStream(newFile);
            ZipUtil.copy(zis, fos);
            fos.flush();
            fos.close();
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
        return true;
    }

    private File newOutputFolder(String folderPath) {
        if (!ZipUtil.isStringNotNullAndNotEmpty(folderPath)) {
            String path = unzipFile.getAbsolutePath().substring(0, unzipFile.getAbsolutePath().length() - 3);
            return new File(path);
        }
        return new File(folderPath);
    }
}