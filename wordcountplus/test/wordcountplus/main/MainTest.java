package wordcountplus.main;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Created by chenhansen on 2018/4/3.
 */
public class MainTest {
    PrintStream console = null; // 声明（为null）：输出流 (字符设备) console
    ByteArrayOutputStream bytes = null; // 声明（为null）：bytes 用于缓存console 重定向过来的字符流
    String excepted = null;                             // 期待的结果
    Main main;

    @Before
    public void setUp() throws Exception {
        main=new Main();
        bytes = new ByteArrayOutputStream(); // 分配空间
        console = System.out; // 获取System.out 输出流的句柄
        System.setOut(new PrintStream(bytes)); // 将原本输出到控制台Console的字符流 重定向 到

        FileInputStream out = new FileInputStream("src/file/out.txt");
        this.excepted = IOUtils.toString(out, "UTF-8"); // 调用 Jar 包中的 toString() 函数, 得到期待的结果
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(console);
    }

    @Test
    public void testMain(){
        String[] args={};
        main.main(args);
        assertEquals(excepted,bytes.toString());
    }

}