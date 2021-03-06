package com.ghaya.swagger.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class CloseableHttpClientToInterface {

    private static String tokenString = "";
    private static String AUTH_TOKEN_EXPIRED = "AUTH_TOKEN_EXPIRED";
    private static CloseableHttpClient httpClient = null;

    /**
     * 以get方式调用第三方接口
     * @param url
     * @return
     */
    public static String doGet(String url, String token){
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);

        try {
            if (tokenString != null && !tokenString.equals("")){
                tokenString = getToken();
            }
            //api_gateway_auth_token自定义header头，用于token验证使用
            get.addHeader("api_gateway_auth_token", tokenString);
            get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
            HttpResponse response = httpClient.execute(get);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                //返回json格式
                String res = EntityUtils.toString(response.getEntity());
                return res;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 以post方式调用第三方接口
     * @param url
     * @param json
     * @return
     */
    public static String doPost(String url, JSONObject json){

        try {
            if (httpClient == null){
                httpClient = HttpClientBuilder.create().build();
            }

            HttpPost post = new HttpPost(url);

            if (tokenString != null && !tokenString.equals("")){
                tokenString = getToken();
            }

            //api_gateway_auth_token自定义header头，用于token验证使用
            post.addHeader("api_gateway_auth_token", tokenString);
            post.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");

            String s1 = json.toString();
            StringEntity s = new StringEntity(s1);
            s.setContentEncoding("UTF-8");
            //发送json数据需要设置contentType
            s.setContentType("application/x-www-form-urlencoded");
            //设置请求参数
            post.setEntity(s);
            HttpResponse response = httpClient.execute(post);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                //返回json格式
                String res = EntityUtils.toString(response.getEntity());
                return res;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (httpClient != null){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 获取第三方接口的token
     */
    public static String getToken(){

        String token = "";

        JSONObject object = new JSONObject();
        object.put("appid", "appid");
        object.put("secretkey", "secretkey");

        try {
            if (httpClient == null){
                httpClient = HttpClientBuilder.create().build();
            }
            HttpPost post = new HttpPost("http://localhost/login");

            post.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");

            StringEntity s = new StringEntity(object.toString());
            s.setContentEncoding("UTF-8");
            //发送json数据需要设置contentType
            s.setContentType("application/x-www-form-urlencoded");
            //设置请求参数
            post.setEntity(s);
            HttpResponse response = httpClient.execute(post);

            //这里可以把返回的结果按照自定义的返回数据结果，把string转换成自定义类
            //ResultTokenBO result = JSONObject.parseObject(response, ResultTokenBO.class);

            //把response转为jsonObject
            JSONObject result = JSONObject.parseObject(String.valueOf(response));
            if (result.containsKey("token")){
                token = result.getString("token");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    /**
     * 测试
     */
    public static void test(String telephone){

        JSONObject object = new JSONObject();
        object.put("telephone", telephone);

        try {
            //首先获取token
            tokenString = getToken();
            String response = doPost("http://localhost/searchUrl", object);

            //如果返回的结果是list形式的，需要使用JSONObject.parseArray转换
            //List<Result> list = JSONObject.parseArray(response, Result.class);

            System.out.println(response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        test("12345678910");
    }

}
