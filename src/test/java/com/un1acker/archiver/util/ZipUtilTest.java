package com.un1acker.archiver.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipException;

import static org.junit.Assert.assertEquals;

/**
 * un1acker
 * 04.04.2015
 */
public class ZipUtilTest {
    public static final String WORK_DIRECTORY = System.getProperty("user.dir");
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testStringNullOrEmpty() {
        assertEquals(ZipUtil.isStringNotNullAndNotEmpty(""), false);
        assertEquals(ZipUtil.isStringNotNullAndNotEmpty(null), false);
    }

    @Test
    public void testString() {
        assertEquals(ZipUtil.isStringNotNullAndNotEmpty("hello"), true);
    }

    @Test
    public void testIsFileExistsPathNull() throws ZipException {
        exception.expect(ZipException.class);
        exception.expectMessage("path is null or empty");
        ZipUtil.isFileExists((String) null);
    }

    @Test
    public void testIsFileExistsPathEmpty() throws ZipException {
        exception.expect(ZipException.class);
        exception.expectMessage("path is null or empty");
        ZipUtil.isFileExists("");
    }

    @Test
    public void testIsFileExistsFileNull() throws ZipException {
        exception.expect(ZipException.class);
        exception.expectMessage("Input file is null");
        ZipUtil.isFileExists((File) null);
    }

    @Test
    public void testIsFileExistStringPath() throws ZipException {
        String path = "src";
        assertEquals(ZipUtil.isFileExists(path), true);
    }

    @Test
    public void testIsFileExist() throws ZipException {
        File file = new File(WORK_DIRECTORY, "src");
        assertEquals(ZipUtil.isFileExists(file), true);
    }

    @Test
    public void testListFilesExistAll() throws ZipException {
        List<String> list = new ArrayList<>();
        list.add("src");
        list.add(".gitignore");
        list.add("pom.xml");
        assertEquals(ZipUtil.isFilesExists(list), true);
    }

    @Test
    public void testListFilesNotExistAll() throws ZipException {
        List<String> list = new ArrayList<>();
        list.add("src");
        list.add(".gitigno");
        list.add("pom.xml");
        assertEquals(ZipUtil.isFilesExists(list), false);
    }

    @Test
    public void testIsFilesExistsFileNull() throws ZipException {
        assertEquals(ZipUtil.isFilesExists(null), false);
    }

    @Test
    public void testCopy() throws IOException {
        byte[] data = "123,456,789".getBytes();
        InputStream in = new ByteArrayInputStream(data);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ZipUtil.copy(in, out);
        assertEquals("123,456,789", new String(out.toByteArray()));
    }


}
