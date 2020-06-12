//package haiying.service.archives.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.amazonaws.services.s3.model.CannedAccessControlList;
//import com.amazonaws.services.s3.model.ObjectMetadata;
//import com.amazonaws.services.s3.model.PutObjectRequest;
//import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
//import com.sun.org.apache.xml.internal.security.utils.Base64;
//import haiying.service.archives.service.RecordService;
//import io.micronaut.http.HttpResponse;
//import io.micronaut.http.HttpStatus;
//import io.micronaut.http.MediaType;
//import io.micronaut.http.MutableHttpResponse;
//import io.micronaut.http.annotation.*;
//import io.micronaut.http.multipart.CompletedFileUpload;
//import io.micronaut.http.multipart.FileUpload;
//import io.micronaut.http.multipart.StreamingFileUpload;
//import io.micronaut.validation.Validated;
//import io.reactivex.Single;
//import org.reactivestreams.Publisher;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//import sun.misc.BASE64Decoder;
//
//import java.io.*;
//import java.net.URI;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//@Validated
//@Controller("/file")
//public class FileController {
//
//    private final RecordService recordService;
//
//    public FileController(RecordService recordService) {
//        this.recordService = recordService;
////        this.s3Client = s3Client;
////        this.bucketName = bucketName;
//    }
//
//    @Post("/uploadFile")
//    public String uploadFile(JSONObject json) throws Exception {
//        String fileString = json.getString("file");
////        MultipartFile file = MultipartFilefileString;
////        String result = recordService.editMovieInfo(file,"D:\\file");
//        return "C:\\Users\\hlifeyf1910008\\Pictures\\u=2570312843,2076156102&fm=26&gp=0.jpg";
//    }
//
//
//    @Put("/uploadFiles")
//    public String uploadFiles(@RequestParam("file") MultipartFile[] file) throws Exception {
//        System.out.println("aa");
//        String result = recordService.editMovieInfos(file,"D:\\file");
//        return "myfiles";
//    }
//
//    @Post("/uploadFiless")
//    public String uploadFiless() throws Exception {
//        String comPath = this.getClass().getResource("/").getPath() + "type" + "/";
//        String filePath = comPath + "photoName";
//        String s = saveImg("");
//        return s;
//    }
//
//
////    @PostMapping("/uploadFilesss")
////    public String uploadFilesss() throws Exception {
////        //String result = recordService.editMovieInfo(file,"D:\\file");
////        return "result";
////    }
//
//    @Post("/filesUpload")
//    public String filesUpload(@RequestParam("file") MultipartFile[] files) throws Exception {
//        List<String> list = new ArrayList<String>();
//        if (files != null && files.length > 0) {
//            for (int i = 0; i < files.length; i++) {
//                MultipartFile file = files[i];
//                // 保存文件
//                list = saveFile(file, list);
//            }
//        }
//        //写着测试，删了就可以
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println("集合里面的数据" + list.get(i));
//        }
//        String s = list.get(0);
//        return s;//跳转的页面
//    }
//
//    private List<String> saveFile(MultipartFile file, List<String> list) {
//        // 判断文件是否为空
//        if (!file.isEmpty()) {
//            try {
//                // 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中
//                // )
//                String filePath = "D:\\file"
//                        + "upload/" + file.getOriginalFilename();
//                list.add(file.getOriginalFilename());
//                File saveDir = new File(filePath);
//                if (!saveDir.getParentFile().exists())
//                    saveDir.getParentFile().mkdirs();
//
//                // 转存文件
//                file.transferTo(saveDir);
//                return list;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return list;
//    }
////    public static String IMAGE_KEY = "applogo.png";
////    public static final String REDIRECT_PATH = "/";
////    @Consumes(MediaType.MULTIPART_FORM_DATA) // <5>
////    @Post("/upload") // <6>
////    public String upload(CompletedFileUpload file) { // <7>
////        if ((file.getFilename() == null || file.getFilename().equals(""))) {
////            MutableHttpResponse<Object> objectMutableHttpResponse = HttpResponse.seeOther(URI.create(REDIRECT_PATH));
////            return "HttpResponse.seeOther(URI.create(REDIRECT_PATH))";
////        }
////        upload(IMAGE_KEY, file);
////        return "111"; // <8>
////    }
////
////    @Post(value = "streaming", consumes = MediaType.MULTIPART_FORM_DATA)
////    public Single<HttpResponse<String>> streaming(StreamingFileUpload file) throws IOException {
////        final File tempFile = new File("D:\\file\\" + file.getFilename());
////        Publisher<Boolean> uploadPublisher = file.transferTo(tempFile);
////        return Single.fromPublisher(uploadPublisher)
////                .map(success -> {
////                    System.out.println("Uploaded " + tempFile);
////                    tempFile.delete();
////                    if (success) {
////                        return HttpResponse.ok("Uploaded");
////                    } else {
////                        return HttpResponse.<String>status(HttpStatus.CONFLICT)
////                                .body("Upload Failed");
////                    }
////                });
////    }
////
////    @Post(value = "completed", consumes = MediaType.MULTIPART_FORM_DATA)
////    public HttpResponse<String> completed(CompletedFileUpload file) {
////        System.out.println("Uploaded" + file.getFilename());
////        return HttpResponse.ok("Uploaded");
////    }
////    private final AmazonS3 s3Client;
////    private final String bucketName;
////    public void upload(String key, CompletedFileUpload file) {
////        try {
////            InputStream inputStream = file.getInputStream();
////            PutObjectRequest request = new PutObjectRequest(bucketName,
////                    key,
////                    inputStream,
////                    createObjectMetadata(file)).withCannedAcl(CannedAccessControlList.PublicRead); // <5>
////            s3Client.putObject(request);
////            inputStream.close();
////        } catch (IOException e) {
////
////        }
////    }
////    private ObjectMetadata createObjectMetadata(FileUpload file) {
////        ObjectMetadata objectMetadata = new ObjectMetadata();
////        file.getContentType().ifPresent(contentType -> objectMetadata.setContentType(contentType.getName()));
////        if (file.getSize() != 0) {
////            objectMetadata.setContentLength(file.getSize());
////        }
////        return objectMetadata;
////    }
//
//
//
//
//
//    // 图片路劲层级分隔符
//    private static String separator = "/";
//
//
//    public String saveImg(String baseImg) throws Base64DecodingException {
//        //定义一个正则表达式的筛选规则，为了获取图片的类型
//        String rgex = "data:image/(.*?);base64";
//        String type = getSubUtilSimple(baseImg, rgex);
//        //去除base64图片的前缀
//        baseImg = baseImg.replaceFirst("data:(.+?);base64,", "");
//        byte[] b;
//        byte[] bs;
//        OutputStream os = null;
//        String fileName = "";
//        String nowDate = "";
//        // 格式化并获取当前日期（用来命名）
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        nowDate = format.format(new Date());
//        //把图片转换成二进制
//        b = Base64.decode(baseImg.replaceAll(" ", "+"));
//        //生成路径
//        String path = "D:/file/"  + separator + "img" + separator + nowDate + separator;
//        //随机生成图片的名字，同时根据类型结尾
//        fileName = UUID.randomUUID().toString() + "." + type;
//        File file = new File(path);
//        if (!file.exists() && !file.isDirectory()) {
//            file.mkdirs();
//        }
//        File imageFile = new File(path + "/" + fileName);
//        BASE64Decoder d = new BASE64Decoder();
//        // 保存
//        try {
//            bs = d.decodeBuffer(Base64.encode(b));
//            os = new FileOutputStream(imageFile);
//            os.write(bs);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//
//        }finally {
//            if (os != null) {
//                try {
//                    os.close();
//                } catch (IOException e) {
//                    e.getLocalizedMessage();
//                }
//            }
//        }
//
//        return "img" + separator + nowDate + separator + fileName;
//    }
//
//
//    public static String getSubUtilSimple(String soap,String rgex){
//        Pattern pattern = Pattern.compile(rgex);
//        Matcher m = pattern.matcher(soap);
//        while(m.find()){
//            return m.group(1);
//        }
//        return "";
//    }
//
//
//
//
//}
