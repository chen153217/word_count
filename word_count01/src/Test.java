import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by chenhansen on 2018/3/17.
 */
public class Test {
    public static void main(String[] args)throws IOException{

//        Map<Integer,String> map=new TreeMap<Integer,String>();
//        map.put(3,"three");
//        map.put(2,"two");
//        map.put(5,"five");
//        for(Map.Entry<Integer,String> m:map.entrySet()){
//            System.out.println(m.getKey()+" :"+m.getValue());
//        }
//        List<String> list=new ArrayList<String>();
//        list.add("hello");
//        System.out.println(list.contains("hello"));
//        BufferedReader buff=new BufferedReader(new FileReader("src\\a.txt"));
//        String str;
//        while((str=buff.readLine())!=null){
//            System.out.println(str+":"+str.length());
//        }
//        ArrayList<Integer> list=new ArrayList<Integer>();
//        list.add(1);
//        list.add(2);
//        System.out.println(list.size());
//        list.remove(1);
//        System.out.println(list.size());

//        if (args.length == 0) {
//            return;
//        } else {
//            System.out.println(args.length);
//            for (String s : args) {
//                System.out.println(s);
//            }
//        }


//        System.out.println(System.getProperty("exe.path"));
//        File file=new File("C:\\Users\\chenhansen\\Desktop\\word_count\\");
//        String[] files=file.list();
//        for(String s:files){
//            System.out.println(s);
//        }
        BufferedReader buff=new BufferedReader(new FileReader("src\\Test.class"));
        String str;
        while((str=buff.readLine())!=null){
            System.out.println(str);
        }
    }
}
