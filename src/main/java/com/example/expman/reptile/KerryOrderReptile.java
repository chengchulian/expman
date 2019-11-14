package com.example.expman.reptile;

import com.example.expman.entity.kerry.KerryOrderDownEntity;
import com.example.expman.entity.kerry.KerryOrderUpEntity;
import com.example.expman.utils.ReptileUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName KerryOrderReptile
 * @Description 嘉里订单爬虫
 * @Author 程方园
 * @Date 2019/11/8 11:04
 * @Version 1.0
 */
@Component
public class KerryOrderReptile {

    public List<KerryOrderDownEntity> getKerryOrderDownList (List<KerryOrderUpEntity> kerryOrderUpList) throws IOException {

        List<KerryOrderDownEntity> kerryOrderDownList = new ArrayList<>();
        //循环处理
        for (KerryOrderUpEntity kerryOrderUpEntity : kerryOrderUpList) {

            String url = "https://th.kerryexpress.com/en/track/?track="+kerryOrderUpEntity.getExpNum();
            //get请求获取数据
            try {
                String result = ReptileUtil.requesterGet(url,true);
//            System.out.println(result);
                //结果经过resultHandler处理并添加到kerryOrderDownList
                kerryOrderDownList.add(resultHandler(result));
            } catch (IOException e) {
                kerryOrderDownList.add(KerryOrderDownEntity.builder().expNum(kerryOrderUpEntity.getExpNum()).remake("生成失败").build());
            }
        }
        return kerryOrderDownList;
    }
    private KerryOrderDownEntity resultHandler(String result){
        Document document =Jsoup.parse(result);
        Element expNum = document.selectFirst("#track");
        Element date = document.selectFirst("#trackArea > div.sector-frame > div > div > div > div > div.col.colStatus > div.status.piority-success > div.date > div:nth-child(1)");
        Element startDate = null;
        if (date!=null){
            startDate = document.select("#trackArea > div.sector-frame > div > div > div > div > div.col.colStatus > div").last().selectFirst("div.date > div:nth-child(1)");
        }

        //TODO
        if (date==null){
            date = document.selectFirst("#trackArea > div > div > div > div > div > div.col.colStatus > div.status.piority-waiting > div.date > div:nth-child(1)");
            if (date!=null){
                startDate = document.select("#trackArea > div > div > div > div > div > div.col.colStatus > div").last().selectFirst("div.date > div:nth-child(1)");
            }
        }
        Element state = document.selectFirst("#trackArea > div.sector-frame > div > div > div > div > div.col.colStatus > div.status.piority-success > div.desc > div");
        if (state == null){
            state = document.selectFirst("#trackArea > div > div > div > div > div > div.col.colStatus > div.status.piority-waiting > div.desc > div.d1");
        }
//        System.out.println(expNum+":"+startDate);
        return KerryOrderDownEntity.builder()
                .expNum(null==expNum?"":expNum.val())
                .startDate(null==startDate?"":startDate.text())
                .date(null==date?"":date.text())
                .state(null==state?"":state.text())
                .build();
    }

}
