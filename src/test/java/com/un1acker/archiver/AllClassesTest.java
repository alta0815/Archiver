package com.un1acker.archiver;

import com.un1acker.archiver.unzip.UnzipObjectTest;
import com.un1acker.archiver.util.ZipUtilTest;
import com.un1acker.archiver.zip.ZipObjectTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * un1acker
 * 09.04.2015
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ZipUtilTest.class,
        ZipObjectTest.class,
        UnzipObjectTest.class
})
public class AllClassesTest {

}
