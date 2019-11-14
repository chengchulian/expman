package com.example.expman.entity.kerry;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.*;

/**
 * @ClassName KerryOrderDownEntity
 * @Description 嘉里返回实体类
 * @Author 程方园
 * @Date 2019/11/8 11:09
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KerryOrderDownEntity extends BaseRowModel {

    @ExcelProperty(index = 0,value = "快递号")
    private String expNum;
    @ExcelProperty(index = 1,value = "发货时间")
    private String startDate;
    @ExcelProperty(index = 2,value = "日期")
    private String date;
    @ExcelProperty(index = 3,value = "状态")
    private String state;
    @ExcelProperty(value = "备注")
    private String remake;

}
