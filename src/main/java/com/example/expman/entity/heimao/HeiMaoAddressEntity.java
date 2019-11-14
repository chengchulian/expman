package com.example.expman.entity.heimao;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.*;



/**
 * @ClassName HeiMaoAddress
 * @Description TODO
 * @Author 程方园
 * @Date 2019/11/6 11:03
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeiMaoAddressEntity extends BaseRowModel {
    @ExcelProperty (index = 0,value = "一级地址")
    private String oneClassAddress;

    @ExcelProperty (index = 1,value = "二级地址")
    private String twoClassAddress;


    @ExcelProperty(index = 2,value = "完整地址")
    private String intactAddress;


}
