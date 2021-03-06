package Util;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class HttpHelper {
    //private static final String APP_KEY = "jiahao.zhang";
    //private static final String SECRET_KEY = "Haohaode521";

    /**
     * 构造带authToken和baSource的header
     * */
    /*public static Map<String, String> getHeader(String authToken, String baSource){
        Map<String, String> header;
        header = new HashMap<>();
        header.put("authToken", authToken);
        header.put("BaSource", baSource);
        return header;
    }*/

    /**
     * 构造Basic Auth认证头信息
     *
     * @return
     */
    /*public static String getHeader() {
        String auth = APP_KEY + ":" + SECRET_KEY;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        return authHeader;
    }*/

    public static String doGet(String url) throws Exception {
        Map<String, String> header = null;
        return doGet(url, header);
    }

    /**
     * @param url
     * @return JSONObject
     * @throws Exception
     * @desc ：1.发起GET请求
     */
    public static String doGet(String url, Map<String, String> header) throws Exception {

        //1.生成一个请求
        HttpGet httpGet = new HttpGet(url);
        //2.配置请求的属性
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();//2000
        httpGet.setConfig(requestConfig);

        //httpGet.addHeader("Authorization", getHeader());

        if (header!=null){
            header.entrySet().stream().forEach(entry -> httpGet.addHeader(entry.getKey(), entry.getValue()));
        }

        //3.发起请求，获取响应信息
        //3.1 创建httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            //3.2 发起请求，获取响应信息
            response = httpClient.execute(httpGet, new BasicHttpContext());
            //如果返回结果的code不等于200，说明出错了
            //System.out.println(response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() != 200) {
//                log.info("request url failed, http code=" + response.getStatusLine().getStatusCode() + ", url=" + url);
                return null;
            }
            //4.解析请求结果
            HttpEntity entity = response.getEntity();      //reponse返回的数据在entity中
            if (entity != null) {
                String resultStr = EntityUtils.toString(entity, "utf-8");  //将数据转化为string格式
                //JSONObject result = JSON.parseObject(resultStr);    //将String转换为 JSONObject
                //System.out.println("http请求的返回结果");
                //System.out.println(resultStr);
                return resultStr;
                /*if(result.getInteger("errcode")==null) {
                    return result;
                }else if (0 == result.getInteger("errcode")) {
                    return result;
                }else {
                    System.out.println("request url=" + url + ",return value=");
                    System.out.println(resultStr);
                    int errCode = result.getInteger("errcode");
                    String errMsg = result.getString("errmsg");
                    throw new Exception("error code:"+errCode+", error message:"+errMsg);
                }*/
            }
        } catch (IOException e) {
            System.out.println("request url=" + url + ", exception, msg=" + e.getMessage());
            e.printStackTrace();
        } finally {
            if (response != null) try {
                response.close();                     //释放资源

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }


    /**
     * 2.发起POST请求
     *
     * @param url  请求url
     * @param data 请求参数（json）
     * @return
     * @throws Exception JSONObject
     * @desc ：
     */
    public static String doPost(String url, Object data) throws Exception {
        Map<String, String> header = null;
        return doPost(url, data, header);
    }

    /**
     * 2.发起POST请求
     *
     * @param url  请求url
     * @param data 请求参数（json）
     * @return
     * @throws Exception JSONObject
     * @desc ：
     */
    public static String doPost(String url, Object data, Map<String, String> header) throws Exception {
        //1.生成一个请求
        HttpPost httpPost = new HttpPost(url);

        //2.配置请求属性
        //2.1 设置请求超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(100000).setConnectTimeout(100000).build();
        httpPost.setConfig(requestConfig);
        //2.2 设置数据传输格式-json
        httpPost.addHeader("Content-Type", "application/json");
        if (header!=null){
//            header.entrySet().stream().forEach(entry -> httpPost.addHeader(entry.getKey(), entry.getValue()));
        }
        //2.3 设置请求实体，封装了请求参数
        StringEntity requestEntity = new StringEntity(JSON.toJSONString(data), "utf-8");
        httpPost.setEntity(requestEntity);

        //3.发起请求，获取响应信息
        //3.1 创建httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        try {
            //3.3 发起请求，获取响应
            response = httpClient.execute(httpPost, new BasicHttpContext());

            if (response.getStatusLine().getStatusCode() != 200) {

                System.out.println("request url failed, http code=" + response.getStatusLine().getStatusCode() + ", url=" + url);
                return null;
            }

            //获取响应内容
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String resultStr = EntityUtils.toString(entity, "utf-8");
                //log.info("POST请求结果：" + resultStr);

                return resultStr;
                /*//解析响应内容
                JSONObject result = JSON.parseObject(resultStr);

                if(result.getInteger("errcode")==null) {
                    return result;
                }else if (0 == result.getInteger("errcode")) {
                    return result;
                }else {
                    System.out.println("request url=" + url + ",return value=");
                    System.out.println(resultStr);
                    int errCode = result.getInteger("errcode");
                    String errMsg = result.getString("errmsg");
                    throw new Exception("error code:"+errCode+", error message:"+errMsg);
                }*/
            }
        } catch (IOException e) {
            System.out.println("request url=" + url + ", exception, msg=" + e.getMessage());
            e.printStackTrace();
        } finally {
            if (response != null) try {
                response.close();              //释放资源

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

}
