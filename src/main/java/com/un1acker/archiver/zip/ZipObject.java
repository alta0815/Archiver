package com.un1acker.archiver.zip;

import com.un1acker.archiver.util.ZipUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.*;


/**
 * un1acker
 * 04.04.2015
 */
public class ZipObject {
    private File zipFile;

    public ZipObject(String fileName) throws ZipException {
        if (!ZipUtil.isStringNotNullAndNotEmpty(fileName) || !fileName.endsWith(".zip")) {
            throw new ZipException("fileName does not satisfy requirements");
        }
        this.zipFile = new File(ZipUtil.WORK_DIRECTORY, fileName);
    }

    public String getZipFileComment() throws IOException {
        return new ZipFile(zipFile).getComment();
    }

    public boolean addFilesToZipWithComment(List<String> filesToZip, String commentToZip) throws IOException {
        if (!ZipUtil.isFilesExists(filesToZip)) {
            throw new ZipException("filesToZip does not all exist");
        }
        if (zipFile.exists()) {
            return addFilesToExistingZip(filesToZip, commentToZip);
        }
        System.out.println("Output to zip : " + zipFile + "\n");
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(this.zipFile));
        addFiles(filesToZip, zos);
        zos.setComment(commentToZip);
        zos.flush();
        zos.close();
        return true;
    }

    private boolean addFilesToExistingZip(List<String> filesToZip, String commentToZip)
            throws IOException {
        File tmpZip = File.createTempFile(zipFile.getName(), null);
        tmpZip.delete();
        if (!zipFile.renameTo(tmpZip)) {
            throw new ZipException("Could not rename the file " + zipFile.getAbsolutePath() +
                    " to " + tmpZip.getAbsolutePath());
        }
        ZipInputStream zis = new ZipInputStream(new FileInputStream(tmpZip));
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile));

        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            boolean notInFilesToZip = true;
            for (String fileName : generateFilePathList(filesToZip)) {
                if (fileName.equals(zipEntry.getName())) {
                    notInFilesToZip = false;
                    break;
                }
            }
            if (notInFilesToZip) {
                zos.putNextEntry(new ZipEntry(zipEntry.getName()));
                ZipUtil.copy(zis, zos);
                zos.closeEntry();
            }
            zipEntry = zis.getNextEntry();
        }
        zis.close();
        addFiles(filesToZip, zos);
        zos.setComment(commentToZip);
        zos.flush();
        zos.close();
        tmpZip.delete();
        return true;
    }

    private void addFiles(List<String> filesToZip, ZipOutputStream out) throws IOException {
        for (String file : generateFilePathList(filesToZip)) {
            System.out.print("File added : " + file + "\n");
            FileInputStream fis = new FileInputStream(new File(ZipUtil.WORK_DIRECTORY, file));
            out.putNextEntry(new ZipEntry(file));
            ZipUtil.copy(fis, out);
            out.closeEntry();
            fis.close();
        }
    }

    private List<String> generateFilePathList(List<String> filesPathList) {
        List<String> allFilesPathList = new ArrayList<>();
        for (String file : filesPathList) {
            generateFilePathList(allFilesPathList, new File(ZipUtil.WORK_DIRECTORY, file));
        }
        return allFilesPathList;
    }

    private void generateFilePathList(List<String> listFiles, File file) {
        if (file.isFile()) {
            listFiles.add(generateZipEntry(file.getAbsoluteFile().toString()));
        }
        if (file.isDirectory()) {
            String[] subFiles = file.list();
            for (String fileName : subFiles) {
                generateFilePathList(listFiles, new File(file, fileName));
            }
        }
    }

    private String generateZipEntry(String file) {
        return file.substring(ZipUtil.WORK_DIRECTORY.length() + 1, file.length());
    }
}