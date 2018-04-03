package wordcountplus.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chenhansen on 2018/3/31.
 */
public class ParttenTest {
    public static void testID_Card(){
        // getBirth("130681198712092019");
        String[] strs = { "130681198712092019", "13068119871209201x",
                "13068119871209201", "123456789012345", "12345678901234x",
                "1234567890123" };
        String regex="\\d{14}\\w|\\d{17}\\w";
        Pattern p=Pattern.compile(regex);
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<strs.length;i++){
            Matcher matcher=p.matcher(strs[i]);
            sb.append("身份证号：");
            sb.append(strs[i]);
            sb.append("   匹配：");
            sb.append(matcher.matches());
            System.out.println(sb.toString());
            if(matcher.matches()){
                getBirth(strs[i]);
            }
            sb.delete(0,sb.length());
        }
    }
    public static void getBirth(String str){
        Pattern p=Pattern.compile("\\d{6}(\\d{4})(\\d{2})(\\d{2})(.*)");
        Matcher matcher=p.matcher(str);
        if(matcher.matches())
            System.out.println(str+"    中的出生年月分解为： "+"年" + matcher.group(1) + "   月："
                    + matcher.group(2) + "  日：" + matcher.group(3));
    }
    public static void main(String[] args){
//        String regex="(a[0-9])*";
//        Pattern pattern=Pattern.compile(regex);
//        String str="a1a2";
//        Matcher matcher=pattern.matcher(str);
//        if(matcher.matches()) {
//            System.out.println(matcher.group(0)+"  "+matcher.groupCount());
//        }
        String regex="[a-zA-Z]+(-[a-zA-Z]+)*";//"^([a-zA-Z]+(-[a-zA-Z])+)";
        Pattern pattern=Pattern.compile(regex);
        String str="package wordcountplus.util;\n" +
                "\n" +
                "import java.io.*;\n" +
                "import java.util.LinkedList;\n" +
                "import java.util.List;\n" +
                "\n" +
                "/**\n" +
                " * Created by chenhansen on 2018/3/31.\n" +
                " */\n" +
                "public class FileUtil{\n" +
                "a-bb-c = 1;\n" +
                "}\n" +
                "a-bb\n" +
                "a-";
        //String[] strs=str.split(regex);
        Matcher matcher=pattern.matcher(str);
        while (matcher.find()){
            //System.out.println(matcher.start()+" "+matcher.end());
            System.out.println(str.substring(matcher.start(),matcher.end()));
        }
    }
}
