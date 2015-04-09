package com.un1acker.archiver.unzip;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.zip.ZipException;

import static org.junit.Assert.*;
/**
 * un1acker
 * 09.04.2015
 */
public class UnzipObjectTest {
    public static final String WORK_FILE = "src" + File.separator + "test" +
            File.separator + "resource" + File.separator + "unzip" + File.separator;
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
        UnzipObject unzip = new UnzipObject(null);
    }

    @Test
    public void testConstructorFileNameEmpty() throws ZipException {
        exception.expect(ZipException.class);
        UnzipObject unzip = new UnzipObject("");
    }

    @Test
    public void testConstructorFileNameNonEndWithZip() throws ZipException {
        exception.expect(ZipException.class);
        UnzipObject unzip = new UnzipObject("test.zi");
    }

    @Test
    public void testExtractAllToNullFolder() throws IOException {
        UnzipObject unzip = new UnzipObject(WORK_FILE + "test1.zip");
        File extractFolder = new File(WORK_FILE + "test1");
        assertEquals(unzip.extractAll(null), true);
        assertEquals(extractFolder.exists(), true);

    }

    @Test
    public void testExtractAllToEmptyFolder() throws IOException {
        UnzipObject unzip = new UnzipObject(WORK_FILE + "test2.zip");
        File extractFolder = new File(WORK_FILE + "test2");
        assertEquals(unzip.extractAll(""), true);
        assertEquals(extractFolder.exists(), true);

    }

    @Test
    public void testExtractAllToMyFolder() throws IOException {
        UnzipObject unzip = new UnzipObject(WORK_FILE + "test3.zip");
        File extractFolder = new File(WORK_FILE + "myFolder");
        assertEquals(unzip.extractAll(WORK_FILE + "myFolder"), true);
        assertEquals(extractFolder.exists(), true);

    }

    @AfterClass
    public static void deleteUnzipFolder() throws IOException {
        FileUtils.deleteDirectory(new File(WORK_FILE + "test1"));
        FileUtils.deleteDirectory(new File(WORK_FILE + "test2"));
        FileUtils.deleteDirectory(new File(WORK_FILE + "myFolder"));
    }
}
