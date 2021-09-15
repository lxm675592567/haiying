package haiying;

import com.alibaba.fastjson.JSONObject;
import haiying.service.physical.controller.HeightAndWeightController;

import haiying.util.CommUtil;
import haiying.util.GuidUtil;
import org.apache.log4j.*;

import java.io.File;
import java.text.DecimalFormat;
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

//        int[] nums = new int[]{1,2,2,2,2,6};
////
////        //int i = majorityElement(nums);
////        int i1 = majorityElement1(nums);
////        //System.out.println(i);
////        System.out.println(i1);

//        JSONObject json = new JSONObject();
//        json.put("aa",1);
//        json.put("bb",2);
//
////        json.put("aa",3);
////        json.put("bb",4);
//        System.out.println(json);
//        ArrayList<> list = new ArrayList<>();
//        list.add("aa");
//        list.add("bb");
//        list.add("cc");
//        list.add("aa");
//        same(list);
//        List<String> list = new ArrayList<>();
//        list.add("a");
//        list.add("a");
//        list.add("a");
//        list.add("b");
//        list.add("b");
//        list.add("c");
//        list.add("d");
//        list.add("d");
//
//        Map<String,Integer> map = new HashMap<>();
//        for(String str:list){
//            Integer i = 1; //定义一个计数器，用来记录重复数据的个数
//            if(map.get(str) != null){
//                i=map.get(str)+1;
//            }
//            map.put(str,i);
//        }
//        System.out.println("重复数据的个数："+map.toString());
//
//
//        System.out.print("重复的数据为：");
//        for(String s:map.keySet()){
//            if(map.get(s) > 1){
//                System.out.print(s+" ");
//            }
//        }
//        double a=5.53;
//        DecimalFormat df = new DecimalFormat("#0.0");
//        String format = df.format((double) 7 / (double) 60);
////        System.out.println(format);
//
//        double ceil = Math.floor((double)119 / (double)60);
//        int round = Math.round(346 * 10);
//        double smsjSleep =round/10.0;
//        System.out.println(round);
    /*    double height = Double.valueOf(10)*0.01;  double weight = Double.valueOf(0);
        DecimalFormat df = new DecimalFormat("#0.0");
        String bmi = df.format(weight/(height*height));
        if (height<=0){
            bmi = "0.0";
        }
        System.out.println(bmi);*/

        /*List<String> names = Arrays.asList("1", "2", "3", "4");*/
        /*for (String name : names) {
            System.out.println(name);
        }
        System.out.println("-------------");*/
       /* Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });*/


      /*  for (String name : names) {
            System.out.println(name);
        }*/


        /*map_test();*/
        //list_test();

      /*  int[] arr= {1,7,4,3,2,8,23,12};
        int z = 0;
        for (int j = 0; j < arr.length; j++) {
            for (int i = 0; i < arr.length-1; i++) {
                int k = arr[i];
                int s = arr[i+1];
                z = arr[i];
                if (s<k){
                    arr[i] = s;
                    arr[i+1] = z;
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            int i1 = arr[i];
            System.out.println(i1);
        }*/

       /* String[] atp = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer","Roger Federer",
                "Andy Murray","Tomas Berdych",
                "Juan Martin Del Potro"};
        List<String> list = Arrays.asList(atp);
        list.forEach((aaa)-> System.out.println(aaa));*/

  /*     new Thread(()-> System.out.println("aaa")).start();
        System.out.println("222");*/

        MyThread myThread=new MyThread();
        myThread.start();
        while(true)
        {
            System.out.println("Main方法在运行");
        }
      /*  Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };*/

    }


    static class MyThread extends Thread{
        public void run(){
            while(true){
                System.out.println("MyThread类的run()方法在运行");
            }
        }
    }


    //列表的使用方法
    public static void  list_test(){
        ArrayList<Object> array = new ArrayList<Object>();

        array.add("八戒你瘦了！");
        array.add(1);
        Object d =  false ? "yes" : "no";
        //添加，
        array.add(d);
        array.add(3,"sji");

        //修改
        array.set(0,"中国人民好幸福");
        System.out.println(array);

        //删除
        array.remove(3);

        // 遍历数组，与python的列表一样。
        for (Object o : array) {
            System.out.println(o);
        }
    }

    //集合map 与python字典类似
    public static void map_test(){

        Map dict = new HashMap();
        Map test = new HashMap();

        //判断字典是否为空，也就是js对象的意思。
        String str = dict.isEmpty() ? "为空" : "不为空";
        System.out.println("字典是否为空："+str);

        //添加数据的方法
        dict.put("key1", "八戒你瘦了");
        dict.put("字典",123);

        test.put(123,"90分");
        //使用putAll方法进行自动嵌套字典存放
        dict.put("字典",test);

        System.out.println(dict);
        System.out.println(test);

        //删除字典
//        dict.remove("key1");
//        System.out.println(dict);

        //修改
        dict.put("key1",456);
        System.out.println(dict);

        //获取key 与values
        System.out.println(dict.keySet());
        System.out.println(dict.values());

        Set key = dict.keySet();
        System.out.println(key);

        for (Object o:key){
            System.out.println(dict.get(o));
        }
    }



    public static void same(List<Integer> list) {
        Map<Integer, String> map = new HashMap<Integer, String>();
        for (int i = 0; i < list.size(); i++) {
            int key = list.get(i);
            String old = map.get(key);
            map.put(key, old + "," + (i + 1));
        }
        Iterator<Integer> it = map.keySet().iterator();
        while (it.hasNext()) {
            int key = it.next();
            String value = map.get(key);
            if (value.indexOf(",") != -1) {
                System.out.println(key + " 重复,行： " + value);
            }
        }
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
