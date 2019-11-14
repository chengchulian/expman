package com.example.expman.entity.kerry;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.*;

/**
 * @ClassName KerryOrderUpEntity
 * @Description 嘉里上传实体类
 * @Author 程方园
 * @Date 2019/11/8 11:09
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KerryOrderUpEntity extends BaseRowModel {
    @ExcelProperty(value = "快递号",index=0)
    private String expNum;
}
