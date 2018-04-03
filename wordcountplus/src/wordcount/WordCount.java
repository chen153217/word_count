package wordcount;

import java.io.*;
import java.util.*;

/**
 * Created by chenhansen on 2018/3/15.
 */

public class WordCount {
    private static BufferedReader reader;
    private static BufferedWriter writer;

    public static int charCount(BufferedReader reader) throws IOException {
        int num = 0;
        String str;
        while ((str = reader.readLine()) != null) {
            num += str.length();
        }
        return num;
    }

    public static int wordCount(BufferedReader reader, List<String> stoplists) throws IOException {
        int num = 0;
        String str;
        if (stoplists.size() == 0) {
            while ((str = reader.readLine()) != null) {
                for (String s : str.split(" ")) {
                    for (String s1 : s.split(",")) {
                        if (s1 != "" && s1.length() > 0) {
                            num++;
                        }
                    }
                }
            }
        } else {
            while ((str = reader.readLine()) != null) {
                for (String s : str.split(" ")) {
                    for (String s1 : s.split(",")) {
                        if (s1 != "" && s1.length() > 0 && !stoplists.contains(s1)) {
                            num++;
                        }
                    }
                }
            }
        }
        return num;
    }

    public static int lineCount(BufferedReader reader) throws IOException {
        int num = 0;
        while (reader.readLine() != null) {
            num++;
        }
        return num;
    }

    public static int[] otherCount(BufferedReader reader) throws IOException {
/*      代码行：本行包括多于一个字符的代码。
        空行：本行全部是空格或格式控制字符，如果包括代码，则只有不超过一个可显示的字符，例如“{”。
        注释行：本行不是代码行，并且本行包括注释。一个有趣的例子是有些程序员会在单字符后面加注释：*/
        int[] others = new int[3];
        String str;
        while ((str = reader.readLine()) != null) {
            str = str.replaceAll(" ", "");//每读取一行，先去掉空格

            //首先判断是否为空行
            if (str.length() <= 1)//本行字符数不多于1，说明是空行
                others[1]++;
                //接下来考虑多行注释与代码行
            else if (str.indexOf("/*") >= 0) {//如果包含“/*”，说明开始多行注释，否则继续判定是代码行还是单行注释
                if (str.indexOf("*/") > 0) {//说明多行注释只注释一行
                    if (str.indexOf("*/") == str.length() - 2) {//说明是注释行
                        others[2]++;
                    } else {
                        others[0]++;
                    }
                } else {
                    if (str.indexOf("/*") == 0)//说明该行没有代码
                        others[2]++;
                    else if (str.indexOf("/*") == 1)//少于一个字符，为空行
                        others[1]++;
                    else
                        others[0]++;//说明在代码行后面开启多行注释
                    while ((str = reader.readLine()) != null) {//说明该行属于注释行
                        str = str.replaceAll(" ", "");
                        if (str.indexOf("*/") < 0)
                            others[2]++;
                        else {
                            if (str.indexOf("*/") == (str.length() - 2))//说明该行没有代码
                                others[2]++;
                            else
                                others[0]++;//说明注释后面后面还有代码
                            break;
                        }
                    }
                }
            }//不是多行注释，看是否为单行注释
            else if (str.indexOf("//") == 0 || str.indexOf("//") == 1) {
                others[2]++;
            } else {
                others[0]++;
            }
        }
        return others;
    }

    public static List<String> getStopList(BufferedReader buff) throws IOException {
        List<String> lists = new ArrayList<String>();
        String str;
        while ((str = buff.readLine()) != null) {
            lists.addAll(Arrays.asList(str.split(" ")));
        }
        return lists;
    }

    public static String getResult(String exepath, String filename, List<String> inputs, List<String> stoplists) throws IOException {
        int line_count = 0;//记录行数
        int word_count = 0;//记录单词数
        int char_count = 0;//记录字符数
        int[] other_count = new int[3];//代码行 / 空行 / 注释行的数目

        String result = "";//保存统计结果
        Map<Integer, String> per_result = new TreeMap<Integer, String>();//保存每一条的统计结果

        String absPath = exepath + filename;
        int length = inputs.size();//获取输入字符的长度
        //String filename=inputs.get(length-1);//需要统计的文件名
        for (int i = 0; i < length - 1; i++) {//循环遍历输入的参数，并将操作的结果放在result中。
            String arg = inputs.get(i);
            reader = new BufferedReader(new FileReader(absPath));
            switch (arg) {
                case "-c"://读取字符数
                    char_count = charCount(reader);
                    result = filename + "," + "字符数: " + char_count + "\r\n";
                    per_result.put(1, result);
                    break;
                case "-w"://读取单词数
                    word_count = wordCount(reader, stoplists);
                    result = filename + "," + "单词数: " + word_count + "\r\n";
                    per_result.put(2, result);
                    break;
                case "-l"://读取行数
                    line_count = lineCount(reader);
                    result = filename + "," + "行数: " + line_count + "\r\n";
                    per_result.put(3, result);
                    break;
                case "-a"://读取代码行 / 空行 / 注释行的数目
                    other_count = otherCount(reader);
                    result = filename + "," + "代码行/空行/注释行: " + other_count[0] + "/" + other_count[1] +
                            "/" + other_count[2] + "/" + "\r\n";
                    per_result.put(4, result);
                    break;
                default://如果不是以上四种，则忽略
                    break;
            }
        }
        result = "";
        for (Map.Entry<Integer, String> entry : per_result.entrySet())
            result += entry.getValue();
        return result;
    }

    public static List<String> searchFile(String absPath, String lastPath) {//递归遍历当前目录以及子目录下符合要求的文件
        String regex = lastPath.replace("?", "[0-9a-z]");
        regex = regex.replace("*", "[0-9a-z]{0,}"); //正则匹配
        List<String> matchFile = new ArrayList<String>();
        File file = new File(absPath);
        File[] files = file.listFiles();//获取当前路径下所有文件
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                List<String> matchs = searchFile(files[i].getAbsolutePath(), lastPath);
                matchFile.addAll(matchs);
            } else if (files[i].isFile()) {
                String filename = files[i].getName();
                if (filename.matches(regex))
                    matchFile.add(files[i].getAbsolutePath());
            }
        }
        return matchFile;
    }

    public static void main(String[] args) throws IOException {
        String exepath=System.getProperty("exe.path");//获取wc.exe文件当前目录
        //String exepath = "C:\\Users\\chenhansen\\Desktop\\sdk1\\word_count\\src\\";//获取wc.exe文件当前目录
        String resultFile = exepath + "wordcount/result.txt";//统计结果放在result.txt文件中
        String result = "";//保存统计结果
        List<String> stop_words = new ArrayList<String>();//存放stop.txt中 的单词
        List<String> inputs = new ArrayList<String>();//存放控制台的输入结果


        //first:如果程序没有输入，则直接退出程序
        if (args.length == 0) {
            return;
        } else {
            for (String arg : args) {//将控制台输入放到链表中，方便后面的输入检测
                inputs.add(arg);
            }
            if (inputs.contains("-e")) {//如果输入包含”-e“，则记录停用单词
                int index = inputs.indexOf("-e");
                String stopFile = inputs.get(index + 1);
                BufferedReader buffer = null;
                try {
                    buffer = new BufferedReader(new FileReader(exepath + stopFile));
                    stop_words = getStopList(buffer);
                } catch (FileNotFoundException e) {
                    System.out.println("stopFile Not Fount");
                    return;
                } finally {
                    if (buffer != null)
                        buffer.close();
                }
                inputs.remove("-e");
                inputs.remove(stopFile);
            }
            if (inputs.contains("wordcount/-o")) {//如果输入包含”-o”，则writer指向指定输出文件名
                int index = inputs.indexOf("wordcount/-o");
                resultFile = inputs.get(index + 1);
                inputs.remove("wordcount/-o");
                inputs.remove(resultFile);
                resultFile = exepath + resultFile;
                writer = new BufferedWriter(new FileWriter(resultFile));
            } else {//否则writer指向默认文件
                writer = new BufferedWriter(new FileWriter(resultFile));
            }
            if (inputs.contains("-s")) {//如果输入包含“-s",则递归处理目录下符合条件的文件
                inputs.remove("-s");
                int length = inputs.size();
                String filename = inputs.get(length - 1);
                List<String> filelist = new ArrayList<String>();
                //判断输入文件为绝对路径还是相对路径，如果filename中包含字符"/"，则说明是绝对路径
                if (filename.indexOf("\\") > 0) {
                    int index = filename.lastIndexOf("\\");
                    String absPath = filename.substring(0, index + 1);
                    String lastPath = filename.substring(index + 1);
                    filelist = searchFile(absPath, lastPath);
                } else {
                    filelist = searchFile(exepath, filename);
                }
                for (String s : filelist) {
                    int index = s.lastIndexOf("\\");
                    String absPath = s.substring(0, index + 1);
                    String lastPath = s.substring(index + 1);
                    result += getResult(absPath, lastPath, inputs, stop_words);
                }
                writer.write(result);
            } else {
                int length = inputs.size();
                String filename = inputs.get(length - 1);//读取文件名
                result = getResult(exepath, filename, inputs, stop_words);
                writer.write(result);
            }
        }
        if (writer != null)
            writer.close();
    }
}
