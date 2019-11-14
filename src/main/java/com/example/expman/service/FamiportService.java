package com.example.expman.service;

import com.example.expman.entity.famiport.FamiportOrderDownEntity;
import com.example.expman.entity.famiport.FamiportOrderUpEntity;
import com.example.expman.reptile.FamiportReptile;
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
 * @ClassName FamiportService
 * @Description TODO
 * @Author 程方园
 * @Date 2019/11/12 18:22
 * @Version 1.0
 */
@Service
public class FamiportService {
    @Autowired
    FamiportReptile famiportReptile;
    @Value("${excelPath}")
    String excelPath;

    public String getExcelResult(FileInputStream fileInputStream) throws IOException {
        //读取EXCEL
        List<FamiportOrderUpEntity> famiportOrderUpEntityList = ExcelUtil.readExcel(new BufferedInputStream(fileInputStream),FamiportOrderUpEntity.class);
        List<FamiportOrderDownEntity> famiportOrderDownEntityList = famiportReptile.getOrderDownList(famiportOrderUpEntityList);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String URIPath = "/famiorder/download/famidown"+simpleDateFormat.format(new Date())+".xlsx";
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

        ExcelUtil.writeExcel(file,famiportOrderDownEntityList);

        return "/excel"+URIPath;


    }

}
