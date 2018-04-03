package wordcountplus.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by chenhansen on 2018/4/3.
 */
public class FileUtilTest {
    @Test
    public void testRead() {
        assertEquals(null,new FileUtil().read("abc"));
    }
}