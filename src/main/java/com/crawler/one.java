package com.crawler;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @author misterWei
 * @create 2018年09月26号:21点13分
 * @mailbox mynameisweiyan@gmail.com
 */
public class one {
    public static void main(String[] args)throws Exception {
        //创建http客户端对象
        CloseableHttpClient aDefault = HttpClients.createDefault();
        //创建请求对象                   //输入要爬区的网站信息;
        HttpGet httpGet = new HttpGet("https://blog.csdn.net/mister_Wei/article/details/82838581");

        CloseableHttpResponse response = aDefault.execute(httpGet);
        //如果状态码为200代表响应成功,就可以进行爬取
        if (response.getStatusLine().getStatusCode()==200){
            HttpEntity entity = response.getEntity();
            //爬取的都是最基本的html  并没有什么实际价值.
            System.out.println(EntityUtils.toString(entity,"utf-8"));
        }else{
            System.err.println("未响应");
        }
        //Ok 成功爬取内容
    }
}
