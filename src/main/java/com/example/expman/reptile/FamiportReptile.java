package com.example.expman.reptile;

import com.example.expman.entity.famiport.FamiportOrderDownEntity;
import com.example.expman.entity.famiport.FamiportOrderUpEntity;
import com.example.expman.entity.famiport.FamiportResponseExpInfo;
import com.example.expman.entity.famiport.FamiportResponseResult;
import com.example.expman.utils.ReptileUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName FamiportReptile
 * @Description 全家爬虫
 * @Author 程方园
 * @Date 2019/11/12 11:33
 * @Version 1.0
 */
@Component
public class FamiportReptile {


    /**
     * @Description 生成Excel列表实体
     * @author 程方园
     * @date 2019/11/12 11:48
     * @params [famiportOrderUpEntityList] 
     * @return java.util.List<com.example.expman.entity.famiport.FamiportOrderDownEntity>
     */
    public List<FamiportOrderDownEntity> getOrderDownList(List<FamiportOrderUpEntity> famiportOrderUpEntityList) throws IOException {

        List<FamiportOrderDownEntity> famiportOrderDownEntityList = new ArrayList<>();
        //循环处理
        for (FamiportOrderUpEntity famiportOrderUpEntity : famiportOrderUpEntityList) {
            String url ="https://ecfme.famiport.com.tw/fmedcfpwebv2/index.aspx/GetOrderDetail";
            String contentType = "application/json";
            String body = "{\"EC_ORDER_NO\":\""+famiportOrderUpEntity.getExpNum() +"\",\"RCV_USER_NAME\":null,\"ORDER_NO\":\"\"}";

            FamiportOrderDownEntity famiportOrderDownEntity = new FamiportOrderDownEntity();
            try {
                String result = ReptileUtil.requesterPost(url,contentType,body);
                famiportOrderDownEntity = resultHandler(result);
            } catch (Exception e) {
                famiportOrderDownEntity.setRemake("生成失败");
            }
            famiportOrderDownEntity.setExpNum(famiportOrderUpEntity.getExpNum());

            famiportOrderDownEntityList.add(famiportOrderDownEntity);

        }

        return famiportOrderDownEntityList;
    }


    /**
     * @Description 单条处理
     * @author 程方园
     * @date 2019/11/13 10:27
     * @params [result]
     * @return com.example.expman.entity.famiport.FamiportOrderDownEntity
     */
    private FamiportOrderDownEntity resultHandler(String result) throws JsonProcessingException {

        FamiportOrderDownEntity famiportOrderDownEntity = new FamiportOrderDownEntity();


        //对结果处理
        result = result.substring(6, result.length() - 2).replace("\\","").toLowerCase();

        ObjectMapper objectMapper = new ObjectMapper();
        //序列化成FamiportResponseResult
        FamiportResponseResult famiportResponseResult = objectMapper.readValue(result, FamiportResponseResult.class);
        //如果结果0Error
        if (famiportResponseResult.getErrorcode().equals("000")){
            //获取数据
            List list = famiportResponseResult.getList();
            String famiportResponseExpInfoJson = objectMapper.writeValueAsString(list.get(0));
            FamiportResponseExpInfo famiportResponseExpInfo = objectMapper.readValue(famiportResponseExpInfoJson,FamiportResponseExpInfo.class);

            famiportOrderDownEntity.setOrderNum(famiportResponseExpInfo.getEc_order_no());
            famiportOrderDownEntity.setDate(famiportResponseExpInfo.getOrder_date_rtn());
            famiportOrderDownEntity.setState(famiportResponseExpInfo.getOrdermessage());
        }


        return famiportOrderDownEntity;
    }
    
    
}
