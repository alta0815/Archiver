package com.un1acker.archiver.zip;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipException;

import static org.junit.Assert.assertEquals;

/**
 * un1acker
 * 08.04.2015
 */
public class ZipObjectTest {
    public static final String TEST_STRING = "File added : src\\test\\resource\\test\\test3.txt\n" +
            "File added : src\\test\\resource\\test.txt\n" +
            "File added : src\\test\\resource\\test.zip\n" +
            "File added : src\\test\\resource\\test1.txt\n";
    private ByteArrayOutputStream outContent;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUpStream() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStream() {
        System.setOut(null);
    }

    @Test
    public void testConstructorFileNameNull() throws ZipException {
        exception.expect(ZipException.class);
        ZipObject zip = new ZipObject(null);
    }

    @Test
    public void testConstructorFileNameEmpty() throws ZipException {
        exception.expect(ZipException.class);
        ZipObject zip = new ZipObject("");
    }

    @Test
    public void testConstructorFileNameNonEndWithZip() throws ZipException {
        exception.expect(ZipException.class);
        ZipObject zip = new ZipObject("test.zi");
    }

    @Test
    public void testAddComment() throws IOException {
        List<String> files = new ArrayList<>();
        files.add("src" + File.separator + "test" + File.separator + "resource");
        ZipObject zip = new ZipObject("test.zip");
        zip.addFilesToZipWithComment(files, "test");
        assertEquals("test", zip.getZipFileComment());
    }

    @Test
    public void testAddFiles() throws IOException {
        List<String> files = new ArrayList<>();
        files.add("src" + File.separator + "test" + File.separator + "resource");
        ZipObject zip = new ZipObject("test.zip");
        zip.addFilesToZipWithComment(files, "test");
        assertEquals(TEST_STRING, new String(outContent.toByteArray()));
    }

    @Test
    public void testAddFilesNull() throws IOException {
        exception.expect(ZipException.class);
        ZipObject zip = new ZipObject("test.zip");
        zip.addFilesToZipWithComment(null, "test");
    }

    @Test
    public void testAddFilesNotExist() throws IOException {
        exception.expect(ZipException.class);
        List<String> files = new ArrayList<>();
        files.add("src" + File.separator + "test" + File.separator + "res");
        ZipObject zip = new ZipObject("test.zip");
        zip.addFilesToZipWithComment(files, "test");
    }




}
