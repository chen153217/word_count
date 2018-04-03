package wordcountplus.util;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by chenhansen on 2018/3/31.
 */
public class FileUtil {
    /**
     * 读取文件并按行输出
     *
     * @param filePath
     * @return
     * @author l00428364
     * @since 2017-12-8
     */
    public static String[] read(final String filePath) {
        File file = new File(filePath);
        // 当文件不存在或者不可读时
        if ((!isFileExists(file)) || (!file.canRead())) {
            System.out.println("file [" + filePath + "] is not exist or cannot read!!!");
            return null;
        }

        List<String> lines = new LinkedList<String>();
        BufferedReader br = null;
        FileReader fb = null;
        try {
            fb = new FileReader(file);
            br = new BufferedReader(fb);

            String str = null;
            int index = 0;
            while ((str = br.readLine()) != null) {
                lines.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(br);
            closeQuietly(fb);
        }

        return lines.toArray(new String[lines.size()]);
    }

    /**
     * 写文件
     *
     * @param filePath 输出文件路径
     * @param contents 要写入的内容
     * @param append   是否追加
     * @return
     * @author l00428364
     * @since 2017-12-8
     */
    public static int write(final String filePath, final String[] contents, final boolean append) {
        File file = new File(filePath);
        if (contents == null) {
            System.out.println("file [" + filePath + "] invalid!!!");
            return 0;
        }

        // 当文件存在但不可写时
        if (isFileExists(file) && (!file.canWrite())) {
            return 0;
        }

        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            if (!isFileExists(file)) {
                file.createNewFile();
            }

            fw = new FileWriter(file, append);
            bw = new BufferedWriter(fw);

            //记录以写入的行数
            int num=0;
            for (String content : contents) {
                if (content == null) {
                    continue;
                }
                bw.write(content);
                bw.newLine();
                num++;

                //仅写入单词词频从高到低的前100个
                if(num>=100)
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        } finally {
            closeQuietly(bw);
            closeQuietly(fw);
        }

        return 1;
    }

    private static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
        }
    }

    private static boolean isFileExists(final File file) {
        if (file.exists() && file.isFile()) {
            return true;
        }

        return false;
    }

}
