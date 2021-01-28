package cn.lihuan.com.utils.http;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @Classname lihuan
 * @Description 调用远程接口
 * @Date 2021/1/28 18:11
 * @Created by Dell
 */


public class HttPost {

    /**
     * JSON串调用
     * @param url 远程地址
     * @param data JSON串
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static void demo(String url, String data) throws ClientProtocolException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建POST请求对象
        HttpPost httpPost = new HttpPost(url);
        //添加请求头信息
        httpPost.addHeader("Content-Type", "application/json");
        //设置请求参数
        StringEntity stringEntity = new StringEntity(data, "UTF-8");
        stringEntity.setContentEncoding("UTF-8");
        stringEntity.setContentType("application/json");
        httpPost.setEntity(stringEntity);//设置请求主体

        HttpResponse httpResponse = httpClient.execute(httpPost);//发送请求
        HttpEntity httpEntity = httpResponse.getEntity();//获取请求返回体
        String backResult = EntityUtils.toString(httpEntity, "UTF-8");//请求返回结果
        System.out.print("收到的返回数据》》》》》》》"+backResult);
        //释放资源
        if (httpResponse != null) {
            try {
                EntityUtils.consume(httpResponse.getEntity());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *  JSON串调用
     * @param path 远程接口地址
     * @param obj  发送的JSON数据
     * @return
     * @throws Exception
     */
    public static String callingInterface(String path,Object obj) throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(path);

        System.out.println("JSON.toJSONString(obj) = " + JSON.toJSONString(obj));

        httpPost.setEntity(new StringEntity(JSON.toJSONString(obj),
                ContentType.create("application/json", "UTF-8")));
        //执行
        CloseableHttpResponse execute = httpClient.execute(httpPost);
        HttpEntity entity = execute.getEntity();
        String str = EntityUtils.toString(entity);
        httpPost.clone();
        httpClient.close();
        System.out.println(str);
        return str;
    }

    /**
     * 调用接口
     * @param filePath 文件本地位置
     * @param jsonObject 传送的JSON数据
     * @param riu 远程接口
     */
    public static void ocrDiscern(String jsonObject, String filePath, String riu) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("multipart/form-data");
        headers.setContentType(type);
        //设置请求体，注意是LinkedMultiValueMap
        FileSystemResource fileSystemResource = new FileSystemResource(filePath);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        //设置发送文件和其它参数
        form.add("multipartFile", fileSystemResource);
        form.add("jsonObject", jsonObject);
        form.add("AppId", "2002100");
        org.springframework.http.HttpEntity<MultiValueMap<String, Object>> files = new org.springframework.http.HttpEntity<>(form, headers);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject(riu, files, String.class);
        System.out.println(result);
    }


    /**
     *
     * @param filePath  文件地址
     * @param uploadURL //接收文件的服务器地址
     * @throws Exception
     */
    public static void yuanchengdiaoyong(String filePath,String uploadURL) throws Exception{

        String fileName = "aaa"; //文件名
        InputStream is = new FileInputStream(new File(filePath));
        //创建HttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(uploadURL);

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();  //上传文件
        /*这是绑定上传文件接口*/
        builder.addBinaryBody("file", is, ContentType.DEFAULT_BINARY, fileName);
        //还可以设置其他参数
        builder.addTextBody("jsonObject", "jsonObject");
        HttpEntity entity = builder.build();
        httpPost.setEntity(entity);
        //执行提交
        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity responseEntity = response.getEntity();
        if (responseEntity != null) {
            //将响应的内容转换成字符串
            String result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
            System.out.println(result);
        }
    }
}
