package com.example.expman.entity.heimao;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.*;

/**
 * @ClassName HeiMaoZipCodeEntity
 * @Description TODO
 * @Author 程方园
 * @Date 2019/11/6 10:59
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class HeiMaoZipCodeEntity extends BaseRowModel {
    @ExcelProperty(value = "地址",index=0)
    private String address;
    @ExcelProperty(value = "区码",index=1)
    private String code;
    @ExcelProperty(value = "备注")
    private String remake;
}
