package com.example.expman.entity.heimao;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName ExpNumEntity
 * @Description TODO
 * @Author 程方园
 * @Date 2019/11/2 15:19
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ExpNumEntity extends BaseRowModel{
    @ExcelProperty(value = "快递号",index=0)
    String expNum;
}
