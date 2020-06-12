package haiying;

import haiying.service.physical.controller.HeightAndWeightController;

import haiying.util.CommUtil;
import haiying.util.GuidUtil;
import org.apache.log4j.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class Demo {
    private static Logger logger = Logger.getLogger(Demo.class);

    public static void main(String[] args) throws Exception {
//        List<Integer> PrList = new ArrayList<>();
//        PrList.add(7);PrList.add(6);PrList.add(5);PrList.add(4);PrList.add(3);PrList.add(2);PrList.add(1);
////        int rint = (int) Math.rint(PrList.size()/3);
////        for(int i=0;i<rint;i++){
////            PrList.remove(0);
////        }
////        int integer = 13 / 5;
////        int remainder = 13 % 5;
////        if (remainder>0){
////            for(int i=0;i<remainder;i++) {
////
////            }
////        }
//////        System.out.println(integer);
////        String str = "2019-03-13 13:54:00";
////        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        Date date = simpleDateFormat.parse(str);
////        long ts = date.getTime();
////        System.out.println(ts);
//
//        int yushu = PrList.size() - PrList.size() % 3;
//
//        PrList = PrList.subList(0, yushu);
//        List list = folderMethod2("D:\\项目\\新数据");
//        System.out.println(list);

//        Integer[] m = {1, 2, 3, 4, 5};
//        Integer[] n = {3, 4, 6};
//
//        System.out.println("----------并集------------");
//        Integer[] b = getB(m, n);
//        for (Integer i : b) {
//            System.out.println(i);
//        }
//
//        System.out.println("----------交集------------");
//        Integer[] j = getJ(m, n);
//        for (Integer i : j) {
//            System.out.println(i);
//        }
//
//        System.out.println("----------差集------------");
//        Integer[] c = getC(m, n);
//        for (Integer i : c) {
//            System.out.println(i);
//        }
//        String guid = CommUtil.getGuid();
//        System.out.println(guid);


//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new Date();
//        System.out.println(sdf.format(date));
//        long time = 600*1000;//60秒
//        Date afterDate = new Date(date.getTime() + time);//60秒后的时间
//        Date beforeDate = new Date(date.getTime() - time);
//        System.out.println(sdf.format(afterDate));

        int[] nums = new int[]{1,2,2,2,2,6};

        //int i = majorityElement(nums);
        int i1 = majorityElement1(nums);
        //System.out.println(i);
        System.out.println(i1);
    }
    public static int majorityElement1(int[] nums) {
        // TODO 自动生成的方法存根
        int length=nums.length;
        Arrays.sort(nums);
        int sle = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < length;) {
            int count = 1;
            for (int j = i + 1; j < length; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                } else {
                    break;//如果当前值和下一个值不相等，就跳出当前值的循环
                    //这是count重置为1，接着找下一个数。
                }
            }
//            if (count > length / 2) {//判断这个数的个数是否满足题目的要求。
//                return nums[i];
//            }
            if (count>=sle){
                sle = count;
                list.add(nums[i]);
                System.out.println("list++++"+list+"++sle++"+sle);
            }
            i += count;
        }

        return 000;

    }

    //众数
    public static int majorityElement(int[] nums) {
        int len = nums.length;
        int count = 0;
        int max = nums[0];
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i < len; i++){
            if(max == nums[i]){
                count++;
            }else{
                count--;
                if(count == 0){
                    max = nums[i];
                    count++;
                }
            }
        }
        return max;
    }

    /**
     *      * 求并集
     *      * 
     *      * @param m
     *      * @param n
     *      * @return
     *      
     */
    private static Integer[] getB(Integer[] m, Integer[] n) {
        // 将数组转换为set集合
        Set<Integer> set1 = new HashSet<Integer>(Arrays.asList(m));
        Set<Integer> set2 = new HashSet<Integer>(Arrays.asList(n));

        // 合并两个集合
        set1.addAll(set2);

        Integer[] arr = {};
        return set1.toArray(arr);
    }

    /**
     *      * 求交集
     *      * 
     *      * @param m
     *      * @param n
     *      * @return
     *      
     */
    private static Integer[] getJ(Integer[] m, Integer[] n) {
        List<Integer> rs = new ArrayList<Integer>();

        // 将较长的数组转换为set
        Set<Integer> set = new HashSet<Integer>(Arrays.asList(m.length > n.length ? m : n));

        // 遍历较短的数组，实现最少循环
        for (Integer i : m.length > n.length ? n : m) {
            if (set.contains(i)) {
                rs.add(i);
            }
        }

        Integer[] arr = {};
        return rs.toArray(arr);
    }

    /**
     *      * 求差集
     *      * 
     *      * @param m
     *      * @param n
     *      * @return
     *      
     */
    private static Integer[] getC(Integer[] m, Integer[] n) {
        // 将较长的数组转换为set
        Set<Integer> set = new HashSet<Integer>(Arrays.asList(m.length > n.length ? m : n));

        // 遍历较短的数组，实现最少循环
        for (Integer i : m.length > n.length ? n : m) {
            // 如果集合里有相同的就删掉，如果没有就将值添加到集合
            if (set.contains(i)) {
                set.remove(i);
            } else {
                set.add(i);
            }
        }

        Integer[] arr = {};
        return set.toArray(arr);
    }

    public static List folderMethod2(String path) {
        File file = new File(path);
        List<String> list = new ArrayList<>();
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null != files) {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        folderMethod2(file2.getAbsolutePath());
                    }
                    String temp[] = file2.getAbsolutePath().split("\\\\");
                    String fileNameNow = temp[temp.length - 1];
                    list.add(fileNameNow);
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        return list;
    }
}
