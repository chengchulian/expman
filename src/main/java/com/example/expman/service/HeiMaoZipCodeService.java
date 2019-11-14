package com.example.expman.service;

import com.example.expman.entity.heimao.HeiMaoAddressEntity;
import com.example.expman.entity.heimao.HeiMaoZipCodeEntity;
import com.example.expman.reptile.HeiMaoZipCodeReptile;
import com.example.expman.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName HeiMaoZipCodeService
 * @Description 黑猫区码查询Service
 * @Author 程方园
 * @Date 2019/11/6 17:41
 * @Version 1.0
 */
@Service
public class HeiMaoZipCodeService {

    @Value("${excelPath}")
    String excelPath;
    @Autowired
    HeiMaoZipCodeReptile heiMaoZipCodeReptile;

    public String getExcelResult(FileInputStream fileInputStream) throws IOException {
        //读取EXCEL
        List<HeiMaoAddressEntity> heiMaoAddressEntityList=ExcelUtil.readExcel(new BufferedInputStream(fileInputStream),HeiMaoAddressEntity.class);

        List<HeiMaoZipCodeEntity> heiMaoZipCodeList = heiMaoZipCodeReptile.getHeiMaoZipCodeList(heiMaoAddressEntityList);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String URIPath = "/heimaozipcode/download/hmcodedown"+simpleDateFormat.format(new Date())+".xlsx";
        File file = new File(excelPath+URIPath);

        //如果文件不存在
        if(!file.getParentFile().exists()){
            //创建父级文件路径
            file.getParentFile().mkdirs();
            //创建文件
            file.createNewFile();
        }
        ExcelUtil.writeExcel(file,heiMaoZipCodeList);
        return "/excel"+URIPath;
    }


}
