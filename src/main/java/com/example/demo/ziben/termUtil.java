package com.example.demo.ziben;

import java.util.*;
import java.util.stream.Collectors;
import org.apache.commons.collections4.SetUtils;
public class termUtil {
    public static List<TermR> filter(String name, List<TermR> termr) {
        // 不可重复集合
        List<TermR> newTermR = new ArrayList<>();
        newTermR.clear();
        HashSet<String> hs = new HashSet<>();
        hs.clear();
        for(int i = 0; i < termr.size(); i++) {
            String nameSt = termr.get(i).getStartTerm().getName();
            hs.add(nameSt);
        }
        HashMap<String, Float> _map = new HashMap<>();
        _map.clear();

        for(String stname: hs) {
            _map.put(stname, cos(name, stname));
        }

//        System.out.println("-----------");
        Map<String, Float> sortMap = sortMap(_map);

        Set<String> keySet = sortMap.keySet();
        Iterator<String> iter = keySet.iterator();
        int ind = 0; // 取前三个
        List<String> _termSt = new ArrayList<>(); _termSt.clear();
        while (iter.hasNext()) {
            String key = iter.next();
            _termSt.add(key);
            ind += 1;
            if (ind >= 3) break;
        }
        /// 新
        for(TermR term: termr) {
            if(_termSt.contains(term.getStartTerm().getName())) {
                newTermR.add(term);
            }
        }
        return newTermR;
    }

    /**
     * 余弦相似性
     * @param a
     * @param b
     * @return
     */
    public static float cos(String a, String b) {
        if (a == null || b == null) {
            return 0F;
        }
        Set<Integer> aChar = a.chars().boxed().collect(Collectors.toSet());
        Set<Integer> bChar = b.chars().boxed().collect(Collectors.toSet());

        // 统计字频
        Map<Integer, Integer> aMap = new HashMap<>();
        Map<Integer, Integer> bMap = new HashMap<>();
        for (Integer a1 : aChar) {
            aMap.put(a1, aMap.getOrDefault(a1, 0) + 1);
        }
        for (Integer b1 : bChar) {
            bMap.put(b1, bMap.getOrDefault(b1, 0) + 1);
        }

        // 向量化
        Set<Integer> union = SetUtils.union(aChar, bChar);
        int[] aVec = new int[union.size()];
        int[] bVec = new int[union.size()];
        List<Integer> collect = new ArrayList<>(union);
        for (int i = 0; i < collect.size(); i++) {
            aVec[i] = aMap.getOrDefault(collect.get(i), 0);
            bVec[i] = bMap.getOrDefault(collect.get(i), 0);
        }

        // 分别计算三个参数
        int p1 = 0;
        for (int i = 0; i < aVec.length; i++) {
            p1 += (aVec[i] * bVec[i]);
        }

        float p2 = 0f;
        for (int i : aVec) {
            p2 += (i * i);
        }
        p2 = (float) Math.sqrt(p2);

        float p3 = 0f;
        for (int i : bVec) {
            p3 += (i * i);
        }
        p3 = (float) Math.sqrt(p3);

        return ((float) p1) / (p2 * p3);
    }

    public static Map<String, Float> sortMap(Map<String, Float> map) {
        //利用Map的entrySet方法，转化为list进行排序
        List<Map.Entry<String, Float>> entryList = new ArrayList<>(map.entrySet());
        //利用Collections的sort方法对list排序
        Collections.sort(entryList, new Comparator<Map.Entry<String, Float>>() {
            @Override
            public int compare(Map.Entry<String, Float> o1, Map.Entry<String, Float> o2) {
                //正序排列，倒序反过来
                int compare = (o1.getValue()).compareTo(o2.getValue());
                return -compare;
            }
        });
        //遍历排序好的list，一定要放进LinkedHashMap，因为只有LinkedHashMap是根据插入顺序进行存储
        LinkedHashMap<String, Float> linkedHashMap = new LinkedHashMap<String, Float>();
        for (Map.Entry<String,Float> e : entryList
        ) {
            linkedHashMap.put(e.getKey(),e.getValue());
        }
        return linkedHashMap;
    }

}
