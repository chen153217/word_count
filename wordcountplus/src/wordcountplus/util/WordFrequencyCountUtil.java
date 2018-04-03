package wordcountplus.util;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chenhansen on 2018/3/31.
 */
public class WordFrequencyCountUtil {
    public static String[] countWordFrequency(String[] resultContents){
        Map<String,Integer> resultMap=new TreeMap<String,Integer>();

        for(String content:resultContents){

            //按照规则，找出每行中，形如abc(-ab)*这样的单词
            String regex="[a-zA-Z]+(-[a-zA-Z]+)*";
            Pattern pattern=Pattern.compile(regex);
            Matcher matcher=pattern.matcher(content);

            while (matcher.find()){
                input(content.substring(matcher.start(),matcher.end()),resultMap);
            }
        }

        //对resultMap按照中的内容按照指定的规则进行排序
        List<Map.Entry<String,Integer>> list=new ArrayList<Map.Entry<String,Integer>>(resultMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(o1.getValue()<o2.getValue())
                    return 1;
                if(o1.getValue()>o2.getValue())
                    return -1;
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        //将排好序的结果存入results中
        String[] results=new String[list.size()];
        int index=0;
        for(Map.Entry<String,Integer> entry:list){
            results[index++]=entry.getKey()+"  "+entry.getValue();
        }
        return results;
    }

    private static void input(String s,Map<String,Integer> map){
        s=s.toLowerCase();
        if(map.containsKey(s)){
            map.put(s,map.get(s)+1);
        }else
            map.put(s,1);
    }

}
