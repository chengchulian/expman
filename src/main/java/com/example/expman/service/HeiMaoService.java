package com.example.expman.service;

import com.example.expman.entity.heimao.ExpEntity;
import com.example.expman.entity.heimao.ExpNumEntity;
import com.example.expman.reptile.HeiMaoReptile;
import com.example.expman.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName HeiMaoService
 * @Description 黑猫服务类
 * @Author 程方园
 * @Date 2019/11/1 13:40
 * @Version 1.0
 */
@Service
public class HeiMaoService {

    @Value("${excelPath}")
    String excelPath;
    @Autowired
    HeiMaoReptile heiMaoReptile;

    public String getExcelResult(InputStream fileInputStream) throws IOException {

        //获取实体类
        List<ExpNumEntity> expNumEntities = ExcelUtil.readExcel(new BufferedInputStream(fileInputStream),ExpNumEntity.class);

        //转换listString
        List<String> expNums = new ArrayList<String>();
        for (ExpNumEntity expNumEntity : expNumEntities) {
            expNums.add(expNumEntity.getExpNum());
        }

        List<ExpEntity> expEntityList = heiMaoReptile.getExpList(expNums);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String URIPath = "/heimao/download/hmdown"+simpleDateFormat.format(new Date())+".xlsx";
        File file = new File(excelPath+URIPath);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();//创建父级文件路径
            file.createNewFile();//创建文件
        }
        ExcelUtil.writeExcel(file,expEntityList);

        return "/excel"+URIPath;
    }
}
