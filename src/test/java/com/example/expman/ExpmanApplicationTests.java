package com.example.expman;

import com.example.expman.entity.heimao.ExpEntity;
import com.example.expman.entity.heimao.HeiMaoAddressEntity;
import com.example.expman.reptile.HeiMaoReptile;
import com.example.expman.reptile.HeiMaoZipCodeReptile;
import com.example.expman.utils.ExcelUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ExpmanApplicationTests {

    @Autowired
    HeiMaoReptile heiMaoReptile;
    @Autowired
    HeiMaoZipCodeReptile heiMaoZipCodeReptile;
//    @Test
    void contextLoads() throws IOException {
        List<String> strings = new ArrayList<>();
        List<ExpEntity> expEntities;
        strings.add("620264294022");
        strings.add("620264294022");
        strings.add("620264294022");
        strings.add("620264294022");
        strings.add("620264294022");
        strings.add("620264294022");
        strings.add("620264294022");
        strings.add("620264294022");
        strings.add("620264294022");
        strings.add("620264294022");
        strings.add("620264294022");
        strings.add("620264294022");
        strings.add("620264294022");
        strings.add("620264294022");
        strings.add("620264294022");
        strings.add("620264294022");
        strings.add("620264294022");
        strings.add("620264294022");
        strings.add("620264294022");
        strings.add("620264294022");
        strings.add("620264294022");
        strings.add("620264294022");
        strings.add("620264294022");
        strings.add("620264294022");
        strings.add("620264294022");
        strings.add("620264294022");
//        System.out.println(strings.get(0));
        expEntities=heiMaoReptile.getExpList(strings) ;
        System.out.println(expEntities);
    }


    @Test
    void zipCodeContextLoad() throws IOException {
        List<HeiMaoAddressEntity> heiMaoAddressEntityList = new ArrayList<>();
        HeiMaoAddressEntity heiMaoAddressEntity = new HeiMaoAddressEntity("高雄市","仁武區","臺灣高雄市仁武區名光街60號5樓");
        heiMaoAddressEntityList.add(heiMaoAddressEntity);
        System.out.println(heiMaoZipCodeReptile.getHeiMaoZipCodeList(heiMaoAddressEntityList));

    }
//    @Test
    void readExcel() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\程初恋\\Desktop\\区码.xlsx");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        List<HeiMaoAddressEntity> heiMaoAddressEntityList= ExcelUtil.readExcel(bufferedInputStream,HeiMaoAddressEntity.class);
        System.out.println(heiMaoAddressEntityList);
    }
    void famiPortJsonToObjTest(){

    }
}
