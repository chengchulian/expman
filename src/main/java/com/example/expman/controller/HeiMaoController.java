package com.example.expman.controller;


import com.example.expman.entity.ResponseResult;
import com.example.expman.service.HeiMaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName HeiMaoController
 * @Description 黑猫Controller
 * @Author 程方园
 * @Date 2019/11/2 11:31
 * @Version 1.0
 */
@RestController
@RequestMapping("heimao")
public class HeiMaoController {

    @Value("${excelPath}")
    private String excelPath;

    @Autowired
    private HeiMaoService heiMaoService;


    @PostMapping("/uploadExcel")
    public ResponseEntity<ResponseResult<String>> uploadExcel(@RequestParam("file") MultipartFile file){

        if (file.isEmpty()){
            return ResponseEntity.ok(new ResponseResult<>(1,"上传失败",""));
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        File tempFile = new File(excelPath+ "/heimao/upload/hmup" + simpleDateFormat.format(new Date())+".xlsx");
        if(!tempFile.getParentFile().exists()){
            tempFile.getParentFile().mkdirs();//创建父级文件路径
            try {
                tempFile.createNewFile();//创建文件
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            file.transferTo(tempFile);
            String excelHttpPath = heiMaoService.getExcelResult(new FileInputStream(tempFile));
            return ResponseEntity.ok(new ResponseResult<>(0,"生成成功",excelHttpPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(new ResponseResult<>(1,"生成失败",""));
    }
}
