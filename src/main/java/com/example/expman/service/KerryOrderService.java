package com.example.expman.service;

import com.example.expman.entity.kerry.KerryOrderDownEntity;
import com.example.expman.entity.kerry.KerryOrderUpEntity;
import com.example.expman.reptile.KerryOrderReptile;
import com.example.expman.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName KerryOrderService
 * @Description 嘉里Service
 * @Author 程方园
 * @Date 2019/11/8 15:58
 * @Version 1.0
 */
@Service
public class KerryOrderService {
    @Autowired
     KerryOrderReptile kerryOrderReptile;
    @Value("${excelPath}")
    String excelPath;

    public String getExcelResult(FileInputStream fileInputStream) throws IOException {
        //读取EXCEL
        List<KerryOrderUpEntity> kerryOrderUpEntityList = ExcelUtil.readExcel(new BufferedInputStream(fileInputStream),KerryOrderUpEntity.class);
        List<KerryOrderDownEntity> kerryOrderDownList = kerryOrderReptile.getKerryOrderDownList(kerryOrderUpEntityList);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String URIPath = "/kerryorder/download/krdown"+simpleDateFormat.format(new Date())+".xlsx";
        File file = new File(excelPath+URIPath);
        //如果文件不存在
        if(!file.getParentFile().exists()){
            //创建父级文件路径
            boolean mkdirs = file.getParentFile().mkdirs();
            //创建文件
            if (mkdirs){
                file.createNewFile();
            }
        }
        ExcelUtil.writeExcel(file,kerryOrderDownList);
        return "/excel"+URIPath;

    }
}
