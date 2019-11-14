package com.example.expman.reptile;

import com.example.expman.entity.heimao.ExpEntity;
import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName HeiMaoReptile
 * @Description 黑猫爬虫
 * @Author 程方园
 * @Date 2019/11/1 15:28
 * @Version 1.0
 */
@Component
public class HeiMaoReptile {

    @Value("${requestBodyHead}")
    private String requestBodyHead;
    @Value("${requestBodyArg}")
    private String requestBodyArg;
    @Value("${referer}")
    private String referer;
    @Value("${cache-control}")
    private String cacheControl;
    @Value("${accept}")
    private String accept;
    @Value("${accept-language}")
    private String acceptLanguage;
    @Value("${content-type}")
    private String contentType;
    @Value("${upgrade-insecure-requests}")
    private String upgradeInsecureRequests;
    @Value("${user-agent}")
    private String userAgent;
    @Value("${accept-encoding}")
    private String acceptEncoding;
    @Value("${host}")
    private String host;
    @Value("${content-length}")
    private String contentLength;
    @Value("${connection}")
    private String connection;
    @Value("${cookie}")
    private String cookie;


    /**
     * @Description 获取ExpList
     * @author 程方园
     * @date 2019/11/5 15:29
     * @params [expNums] 
     * @return java.util.List<com.example.expman.entity.ExpEntity>
     */
    public List<ExpEntity> getExpList(List<String> expNums) throws IOException {

        //请求次数
        int numTime = expNums.size()%10==0?expNums.size()/10:expNums.size()/10+1;

        List<ExpEntity> expEntities = new ArrayList<>();
        //循环请求
        for (int i = 0; i < numTime; i++) {
            int index = i*10;

            List<String> expNumList = new ArrayList<>();
            //拼接请求内容
            StringBuilder content = new StringBuilder(requestBodyHead);
            for (int j = 0; j<10;j++){
                content.append("&")
                        .append(requestBodyArg)
                        .append(j+1)
                        .append("=")
                        .append(expNums.size()>=index+j+1?expNums.get(index+j):"");
                expNumList.add(expNums.size()>=index+j+1?expNums.get(index+j):"");
            }

            try {
                String result = requester(content.toString());
//            System.out.println(result);
                //处理请求结果
                expEntities.addAll(resultHandler(result,expNumList));
            } catch (Exception e) {
                List<ExpEntity> expEntityList = new ArrayList<>();
                for (String s : expNumList) {
                    expEntityList.add(ExpEntity.builder().num(s).remake("生成失败").build());
                }
                expEntities.addAll(expEntityList);
            }
        }
        return expEntities;


    }

    /**
     * @Description 请求者
     * @author 程方园
     * @date 2019/11/5 15:29
     * @params [content] 
     * @return java.lang.String
     */
    private String requester(String content) throws IOException {
        OkHttpClient client = new OkHttpClient();
        //表单类型
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, content);

        //请求头设置
        Request request = new Request.Builder()
                .url("https://www.t-cat.com.tw/Inquire/trace.aspx")
                .post(body)
                .addHeader("referer", referer)
                .addHeader("cache-control", cacheControl)
                .addHeader("accept", accept)
                .addHeader("accept-language", acceptLanguage)
                .addHeader("content-type", contentType)
                .addHeader("upgrade-insecure-requests", upgradeInsecureRequests)
                .addHeader("user-agent", userAgent)
                .addHeader("accept-encoding", acceptEncoding)
                .addHeader("host", host)
                .addHeader("content-length", contentLength)
                .addHeader("connection", connection)
                .addHeader("cookie", cookie)
                .build();
        request.isHttps();
        Response response = client.newCall(request).execute();

        //获取结果
//        Document result = Jsoup.parse(response.body().string());
        return response.body().string();
    }

    /**
     * @Description 单次请求封装参数
     * @author 程方园
     * @date 2019/11/5 15:30
     * @params [result] 
     * @return java.util.List<com.example.expman.entity.ExpEntity>
     */
    private List<ExpEntity> resultHandler(String result,List<String> expNumList){
        List<ExpEntity> onceExpEntityList = new ArrayList<>();

        Document document = Jsoup.parse(result);
        Elements tables = document.getElementsByClass("tablelist");
        if (tables!=null){
            Elements trs = tables.get(0).getElementsByTag("tr");
            for (int i = 1;i<trs.size();i++){
                Elements elements = trs.get(i).select("td span");

                onceExpEntityList.add(new ExpEntity(elements.get(0).text(),elements.get(1).text(),elements.get(2).text(),elements.get(3).text(),""));
            }
        }else {
            for (String expNum : expNumList) {
                onceExpEntityList.add(ExpEntity.builder().num(expNum).remake("生成失败").build());
            }
        }
        return onceExpEntityList;
    }


}
