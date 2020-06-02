package haiying;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
public class HttpTest {
    String uri = "http://127.0.0.1:8080/simpleweb";

    /**
     * Get方法
     */
    @Test
    public void test1() {
        try {
            CloseableHttpClient client = null;
            CloseableHttpResponse response = null;
            try {
                ///test1?code=001&name=测试
                HttpGet httpGet = new HttpGet( "http://localhost:8082/motherInfo/page?tenantId=1&phoneNumber=1");

                client = HttpClients.createDefault();
                response = client.execute(httpGet);
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity);
                System.out.println(result);
            } finally {
                if (response != null) {
                    response.close();
                }
                if (client != null) {
                    client.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Post发送form表单数据
     */
    @Test
    public void test2() {
        try {
            CloseableHttpClient client = null;
            CloseableHttpResponse response = null;
            try {
                // 创建一个提交数据的容器
                List<BasicNameValuePair> parames = new ArrayList<>();
                parames.add(new BasicNameValuePair("code", "001"));
                parames.add(new BasicNameValuePair("name", "测试"));

                HttpPost httpPost = new HttpPost(uri + "/test1");
                httpPost.setEntity(new UrlEncodedFormEntity(parames, "UTF-8"));

                client = HttpClients.createDefault();
                response = client.execute(httpPost);
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity);
                System.out.println(result);
            } finally {
                if (response != null) {
                    response.close();
                }
                if (client != null) {
                    client.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Post发送json数据
     */
    @Test
    public void test3() {
        try {
            CloseableHttpClient client = null;
            CloseableHttpResponse response = null;
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> data = new HashMap<String, Object>();
                data.put("code", "001");
                data.put("name", "测试");

                JSONObject jsonParam = new JSONObject();
                jsonParam.put("wxOpenId","aaa");
                jsonParam.put("tenantId","byUser.getTenantId()");
                jsonParam.put("id","dasd232");
                jsonParam.put("name","byUser.getNickName()");
                jsonParam.put("occupation","");
                jsonParam.put("phoneNumber","");
                jsonParam.put("wxUnionId","");
                jsonParam.put("age","17");

                HttpPost httpPost = new HttpPost( "http://localhost:8082/motherInfo/submit");
                httpPost.setHeader(HTTP.CONTENT_TYPE, "application/json");
                httpPost.setEntity(new StringEntity(objectMapper.writeValueAsString(jsonParam),
                        ContentType.create("text/json", "UTF-8")));

                client = HttpClients.createDefault();
                response = client.execute(httpPost);
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity);
                System.out.println(result);
            } finally {
                if (response != null) {
                    response.close();
                }
                if (client != null) {
                    client.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

