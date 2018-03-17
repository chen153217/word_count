

import java.io.*;

/**
 * Created by chenhansen on 2018/3/16.
 */
public class WordTest {
    private static BufferedReader reader;
    private static BufferedWriter writer;
    public static int wordCount(BufferedReader reader)throws IOException{
        int num=0;
        String str;
        while ((str=reader.readLine())!=null){
            for(String s:str.split(" ")){
                for(String s1:s.split(",")) {
                    if(s1!=""&&s1.length()>0){
                        num++;
                    }

                }
            }
        }
    return num;
    }
    public static int lineCount(BufferedReader reader)throws IOException {
        int num=0;
        while(reader.readLine()!=null){
            num++;
        }
        return num;
    }
    public static int charCount(BufferedReader reader)throws IOException{
        int num=0;
        String str;
        while ((str=reader.readLine())!=null){
           num+=str.length();
        }
        return num;
    }
    public static void writeResult(BufferedWriter _writer,String result)throws IOException{
        _writer.write(result);
        _writer.flush();
    }
    public static void copyFile(String to)throws IOException{
        reader=new BufferedReader(new FileReader("result.txt"));
        String result="";
        String str;
        while((str=reader.readLine())!=null)
            result=result+str+"\n";
        try {
            writer=new BufferedWriter(new FileWriter(to));
        }catch (FileNotFoundException e){
            e.printStackTrace();//暂不做处理
        }

        writeResult(writer,result);
    }

    public static void main(String[] args)throws IOException{
        //String exepath=System.getProperty("exe.path");//获取exe文件当前目录
        int line_count=0;//记录行数
        int word_count=0;//记录单词数
        int char_count=0;//记录字符数

        String result="";
        if(args.length==0){
            System.out.println("please enter a filename");
        }else{
            if(!args[0].equals("-o")){//如果不是"-o"，则清除文件打开
                writer=new BufferedWriter(new FileWriter("result.txt"));
            }
            int length=args.length;//获取输入字符的长度
            String filename=args[length-1];
            for(int i=0;i<length-1;i++){
                String arg=args[i];
                switch (arg){
                    case "-c":
                        reader=new BufferedReader(new FileReader(filename));
                        char_count=charCount(reader);
                        result=filename+","+"字符数: "+char_count+"\n";
                        writeResult(writer,result);
                        break;
                    case "-w":
                        reader=new BufferedReader(new FileReader(filename));
                        word_count=wordCount(reader);
                        result=filename+","+"单词数: "+word_count+"\n";
                        writeResult(writer,result);
                        break;
                    case "-l":
                        reader=new BufferedReader(new FileReader(filename));
                        line_count=lineCount(reader);
                        result=filename+","+"行数: "+line_count+"\n";
                        writeResult(writer,result);
                        break;
                    case "-o"://如果
                        copyFile(args[length-1]);
                        break;
                    default:
                        result="输入格式有误，请重新输入！";
                        writer=new BufferedWriter(new FileWriter("result.txt"));
                        writeResult(writer,result);
                }
            }


        }

    writer.close();
    }
}
