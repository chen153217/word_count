package wordcountplus.main;

import wordcountplus.util.FileUtil;
import wordcountplus.util.WordFrequencyCountUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chenhansen on 2018/3/31.
 */
public class Main {
    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.print("please input a *.txt file!");
            return;
        }

        String inputFilePath = args[0];
        String resultFilePath = "result.txt";

        //构建正则表达式以匹配以.txt结尾的文件
        String regex = ".+\\.txt";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputFilePath);

        if (matcher.matches()) {
            //读取输入文件
            String[] contents = FileUtil.read(inputFilePath);

            //获取统计结果(已按词频从高到低排好序)
            String[] resultContents = WordFrequencyCountUtil.countWordFrequency(contents);

            //写入输出文件
            if (hasResults(resultContents)) {
                FileUtil.write(resultFilePath, resultContents, false);
            } else {
                FileUtil.write(resultFilePath, new String[]{"No Contents!"}, false);
            }
        } else
            System.out.println("it is not a *.txt file!");
        return;
    }

    private static boolean hasResults(String[] resultContents) {
        if (resultContents == null) {
            return false;
        }
        for (String contents : resultContents) {
            if (contents != null && !contents.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

}
