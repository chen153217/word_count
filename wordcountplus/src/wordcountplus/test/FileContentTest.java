package wordcountplus.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by chenhansen on 2018/3/31.
 */
public class FileContentTest {
    public static void main(String[] args)throws IOException{
        BufferedReader bf=new BufferedReader(new FileReader("src\\file\\a.c"));
        String result="";
        String str="";
        while((str=bf.readLine())!=null){
            result+=str+"\n";
        }
        System.out.println(result);
    }
}
