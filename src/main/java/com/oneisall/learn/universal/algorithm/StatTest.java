package com.oneisall.learn.universal.algorithm;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Stat类定义如下所示。在不用Map的情况下，把集合List<Stat>的数据按照日期进行合并。
 * <p>
 * 举例：
 * List<Stat> list = new LinkedList<>();
 * list.add(new Stat("2020-04-22", 1, 1));
 * list.add(new Stat("2020-04-22", 2, 2));
 * list.add(new Stat("2020-04-23", 1, 1));
 * <p>
 * List<Stat> mergedList = mergeList(list);
 * mergedList中的元素为：
 * Stat("2020-04-22", 3, 3)
 * Stat("2020-04-23", 1, 1)
 * <p>
 * 提示：
 * 1）list已经按照日期排好序；
 * 2）可以随意创建和更改对象。
 * <p>
 * public class Stat {
 * public Stat(String date, int registerCount, int activeCount) {
 * super();
 * this.date = date;
 * this.registerCount = registerCount;
 * this.activeCount = activeCount;
 * }
 * <p>
 * private String date; // yyyy-MM-dd
 * private int registerCount;
 * private int activeCount;
 * public String getDate() {
 * return date;
 * }
 * public void setDate(String date) {
 * this.date = date;
 * }
 * public int getRegisterCount() {
 * return registerCount;
 * }
 * public void setRegisterCount(int registerCount) {
 * this.registerCount = registerCount;
 * }
 * public int getActiveCount() {
 * return activeCount;
 * }
 * public void setActiveCount(int activeCount) {
 * this.activeCount = activeCount;
 * }
 * }
 * <p>
 * List<Stat> list = getListFromDB();
 * <p>
 * <p>
 * <p>
 * 你的实现为：
 *
 * @author : oneisall
 * @version : v1 2020/6/3 20:30
 */
public class StatTest {
    static class Stat {
        public Stat(String date, int registerCount, int activeCount) {
            super();
            this.date = date;
            this.registerCount = registerCount;
            this.activeCount = activeCount;
        }

        private String date; // yyyy-MM-dd
        private int registerCount;
        private int activeCount;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getRegisterCount() {
            return registerCount;
        }

        public void setRegisterCount(int registerCount) {
            this.registerCount = registerCount;
        }

        public int getActiveCount() {
            return activeCount;
        }

        public void setActiveCount(int activeCount) {
            this.activeCount = activeCount;
        }
    }

    public static void main(String[] args) {
        List<Stat> list = new LinkedList<>();
        list.add(new Stat("2020-04-22", 2, 1));
        list.add(new Stat("2020-04-22", 2, 2));

        list.add(new Stat("2020-04-23", 3, 1));
        list.add(new Stat("2020-04-23", 3, 2));
        list.add(new Stat("2020-04-23", 3, 3));

        list.add(new Stat("2020-04-24", 4, 1));
        list.add(new Stat("2020-04-24", 4, 2));
        list.add(new Stat("2020-04-24", 4, 3));

        list.add(new Stat("2020-04-25", 5, 1));

        List<Stat> mergedList = mergeList(list);
        //mergedList中的元素为：
        //Stat("2020-04-22", 4, 3)
        //Stat("2020-04-23", 9, 6)
        //Stat("2020-04-24", 12, 6)
        //Stat("2020-04-25", 5, 1)
        System.out.println(mergedList);

    }

    private static List<Stat> mergeList(List<Stat> list) {
        LinkedList<Stat> stats = new LinkedList<>();
        Stat tmpStat = null;
        String preDate = null;
        for (Stat stat : list) {
            if (preDate == null) {
                // 第一次，第一个stat
                preDate = stat.getDate();
                tmpStat = new Stat(stat.getDate(), stat.getRegisterCount(), stat.getActiveCount());
                stats.add(tmpStat);
                continue;
            }
            // 第二次
            String curDate = stat.getDate();
            if (curDate.equals(preDate)) {
                // 相同拿上一次，已经入列表了
                tmpStat.setActiveCount(stat.getActiveCount() + tmpStat.getActiveCount());
                tmpStat.setRegisterCount(stat.getRegisterCount() + tmpStat.getRegisterCount());
            } else {
                // 不相同新创建一个入列表
                tmpStat = new Stat(stat.getDate(), stat.getRegisterCount(), stat.getActiveCount());
                stats.add(tmpStat);
            }
            // 最后每次都更新为最后一个
            preDate = curDate;
        }
        return stats;
    }
}
