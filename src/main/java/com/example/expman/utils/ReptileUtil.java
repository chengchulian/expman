package com.example.expman.utils;

import okhttp3.*;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ReptileUtil
 * @Description 爬虫工具类
 * @Author 程方园
 * @Date 2019/11/8 11:26
 * @Version 1.0
 */
public class ReptileUtil {

    public static String requesterGet(String url,Boolean isHttps) throws IOException {


        OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(50000, TimeUnit.MILLISECONDS)
                .readTimeout(50000, TimeUnit.MILLISECONDS)
                .build();
        Request request = new Request
                .Builder()
                .url(url)
                .get()
                .build();
        if (null != isHttps && isHttps){
            request.isHttps();
        }

        Response response = client.newCall(request).execute();

        return Objects.requireNonNull(response.body()).string();
    }

    public static String requesterPost(String url, String contentType, String body) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(50000, TimeUnit.MILLISECONDS)
                .readTimeout(50000, TimeUnit.MILLISECONDS)
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(mediaType,body);
        Request request =  new Request
                .Builder()
                .url(url)
                .post(requestBody)
                .addHeader("content-type",contentType)
                .build();
        Response response = client.newCall(request).execute();

        return  Objects.requireNonNull(response.body()).string();
    }
}
