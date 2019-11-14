package com.example.expman.controller;

import com.example.expman.entity.ResponseResult;
import com.example.expman.service.FamiportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName FamiportController
 * @Description TODO
 * @Author 程方园
 * @Date 2019/11/12 18:30
 * @Version 1.0
 */
@RestController
@RequestMapping("FamiportOrder")
public class FamiportController {
    @Value("${excelPath}")
    private String excelPath;
    @Autowired
    private FamiportService famiportService;
    @PostMapping("/uploadExcel")
    public ResponseEntity<ResponseResult<String>> uploadExcel(@RequestParam("file") MultipartFile file) throws IOException {

        if (file.isEmpty()){
            return ResponseEntity.ok(new ResponseResult<>(1,"上传失败",""));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        File tempFile = new File(excelPath+ "/famiorder/upload/famiup" + simpleDateFormat.format(new Date())+".xlsx");
        //如果文件不存在
        if (!tempFile.getParentFile().exists()){
            //创建父级文件路径
            tempFile.getParentFile().mkdirs();
            //创建文件
            tempFile.createNewFile();
        }
        //写入文件
        file.transferTo(tempFile);
        String excelHttpPath = famiportService.getExcelResult(new FileInputStream(tempFile));
        return ResponseEntity.ok(new ResponseResult<String>(0,"生成成功",excelHttpPath));
    }
}
