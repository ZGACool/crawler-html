package com.crawler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
/**
 * @author misterWei
 * @create 2018年09月26号:22点42分
 * @mailbox mynameisweiyan@gmail.com
 */
public class HttpHtmlBaiduAndParment {
    public static void main(String[] args) {
        HttpGet httpGet = null;
        URIBuilder uriBuilder = null;
        CloseableHttpResponse execute =null;
        CloseableHttpClient client =null;
        try {
            client = HttpClients.createDefault();
            uriBuilder = new URIBuilder("https://www.baidu.com/s").setParameter("wd","java");
            httpGet = new HttpGet(uriBuilder.build());
            execute = client.execute(httpGet);

            if (execute.getStatusLine().getStatusCode()==200){
                System.out.println(EntityUtils.toString(execute.getEntity()));
            }else{
                System.out.println("响应失败!");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (execute!=null){
                try {
                    execute.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
