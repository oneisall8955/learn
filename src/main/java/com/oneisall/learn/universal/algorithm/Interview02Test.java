package com.oneisall.learn.universal.algorithm;


import java.util.HashMap;
import java.util.Map;

/**
 * 面试题
 *
 * @author : oneisall
 * @version : v1 2020/5/18 10:53
 */
public class Interview02Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        /*
        int[] ints = {6, 4, -3, 5, -2, -1, 0, 1, -9};
        solution.posLeftNegRight(ints);

        // dump
        for (int v : ints) {
            System.out.print(v+" ");
        }
        */
        // { ‘A’: 1, ‘B.A’: 2, ‘B.B’: 3, ‘CC.D.E’: 4, ‘CC.D.F’: 5}
        Map<String, Object> map = new HashMap<>();
        map.put("A", 1);
        map.put("B.A", 2);
        map.put("B.B", 3);
        map.put("CC.D.E", 4);
        map.put("CC.D.F", 5);
        map = solution.flatten2nested(map);
        solution.dumpMap(map, "");
    }
}

class Solution {
    // {6, 4, -3, 5, -2, -1, 0, 1, -9}
    public void posLeftNegRight(int[] ints) {
        int index = -1;                   //定义指针
        for (int i = 0; i < ints.length; i++) {

            //找到最前面的负数
            if (ints[i] < 0 && index == -1) {
                // 第一次进入这个if index=i=2，ints[2]=-3
                index = i;
            }
            //找到了紧跟其后的第一个正数
            if (ints[i] >= 0 && index != -1) {
                // 第一次进入这个if，i=4,index=2,交换ints[2]=-3和ints[4]=4的值，
                //交换值
                int temp = ints[index];
                ints[index] = ints[i];
                ints[i] = temp;

                //由于index前面的负数已经与正数交换过，所以将i重置到负数的位置
                // 由于交换了 ints[index=2]=-3和ints[i=4]=4，所以游标回到index正数位置，用于继续查找下一个负数
                i = index; // 这里不能i+1，因为结束本次循环，最后自动i++了
                //将指针重置后，重复循环操作
                index = -1;
            }
        }
    }

    public Map<String, Object> flatten2nested(Map<String, Object> map) {
        Map<String, Object> fixMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            String[] split = key.split("\\.");
            if (split.length == 1) {
                fixMap.put(key, value);
            } else {
                // 存在.分割
                Map<String, Object> targetMap = buildTargetMap(fixMap, split);
                String targetKey = split[split.length - 1];
                targetMap.put(targetKey, value);
            }
        }
        return fixMap;
    }

    @SuppressWarnings("all")
    private Map<String, Object> buildTargetMap(Map<String, Object> fixMap, String[] split) {
        Map<String, Object> targetMap = null;
        Map<String, Object> tmpMap = fixMap;
        // A.B.C
        // {A:{B:{}}}
        for (int i = 0; i < split.length - 1; i++) {
            String key = split[i];
            targetMap = (Map<String, Object>) tmpMap.get(key);
            if (targetMap == null) {
                targetMap = new HashMap<>();
                tmpMap.put(key, targetMap);
            }
            tmpMap = targetMap;
        }

        return targetMap;
    }

    public void dumpMap(Map<String, Object> map, String formatPreFix) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, Object> subMap = (Map<String, Object>) value;
                System.out.println(formatPreFix + " " + key + ":{");
                dumpMap(subMap, formatPreFix + "-");
                System.out.println(formatPreFix + " }");
            } else {
                System.out.println(formatPreFix + " " + key + ":" + value);
            }
        }
    }
}
