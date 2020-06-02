package haiying;

import haiying.service.physical.controller.HeightAndWeightController;

import org.apache.log4j.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        List list = folderMethod2("D:\\项目\\新数据");
        System.out.println(list);

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
                    String temp[]= file2.getAbsolutePath().split("\\\\");
                    String fileNameNow=temp[temp.length-1];
                    list.add(fileNameNow);
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        return list;
    }
}
