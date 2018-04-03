package wordcountplus.test;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

/**
 * Created by chenhansen on 2018/3/31.
 */
public class TreeMapTest extends TreeMap{

    public static void main(String[] args){
        Map<String,Integer> map=new TreeMap<String,Integer>();
        map.put("abc",1);
        map.put("cb",2);
        map.put("aab",2);
        map.put("aaa",1);
        map.put("cd",3);
        List<Map.Entry<String,Integer>> list=new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
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
        for(Map.Entry<String,Integer> entry:list){
            System.out.println("key: "+ entry.getKey()+", value:"+entry.getValue());
        }
    }
}
